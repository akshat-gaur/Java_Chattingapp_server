import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class HandleConnection extends Thread {
    int position = -1;
    Socket s;
    server SERVER;

    StoreNode tsn;
    node inn;

    HandleConnection(Socket s, server SERVER) {
        this.s = s;
        this.SERVER = SERVER;
    }
    public static void send(String receiver_id, container c,server SERVER){
        int l=Integer.parseInt(receiver_id);
        node sn=SERVER.BtMain.search(l);
        StoreNode op;
        System.out.println("in send msg handle connection");
        if (sn!=null){
            for (int i = 0; i < sn.data.size(); i++) {
                op = sn.data.get(i);
                op.Send(c);

            }

        }

    }




    public void run() {
        System.out.println("connection thread");
            int check=0;
            try {
                ObjectInputStream ois;
                container ReceivedPackage;
                while (true) {
                    ois = new ObjectInputStream(this.s.getInputStream());
                    System.out.println("running");
                    ReceivedPackage = (container) ois.readObject();
                    System.out.println(ReceivedPackage.sender_id);
                    if (ReceivedPackage.action == 'a') {
                        long st=System.nanoTime();
                        System.out.println("in a");
                        this.tsn = new StoreNode(this.s, ReceivedPackage.VerArray);
                        this.position = this.SERVER.MainAddressStore.AppendStore(tsn);
                        inn = new node(Integer.parseInt(ReceivedPackage.sender_id));
                        inn.data.add(tsn);
                        this.SERVER.BtMain.insert(inn);
                        long bt=System.nanoTime();
                        System.out.println(bt-st);
                        break;
                    }
                }
            }
            catch (java.net.SocketException ex){
                check=1;
                System.out.println(ex);
            }
            catch (Exception ex){
                check=1;
                System.out.println(ex);
                ex.printStackTrace();
            }

            try {
                container newReceivedPackage;
                node sn;
                ObjectInputStream newois;
                StoreNode op;
                int l;
                while(check==0) {
                    newois = new ObjectInputStream(this.s.getInputStream());
                    System.out.println("hi");
                    newReceivedPackage = (container) newois.readObject();
                    if (newReceivedPackage.action == 's') {
                        System.out.println("in s");
                        String hf = newReceivedPackage.receiver_id;
                        l = Integer.parseInt(hf);
                        sn = this.SERVER.BtMain.search(l);
                        if (sn != null) {
                            for (int i = 0; i < sn.data.size(); i++) {
                                op = sn.data.get(i);
                                op.Send(newReceivedPackage);

                            }
                        }

                    }

                    else if (newReceivedPackage.action=='g'){
                        this.SERVER.MainDatabase.SendGroupMesseges(this.SERVER,newReceivedPackage);
                        System.out.println("in g");
                    }
                    else if(newReceivedPackage.action=='d'){
                        this.SERVER.MainDatabase.AddGroupMember(newReceivedPackage);
                        System.out.println("in d");

                    } else if (newReceivedPackage.action=='c') {
                        System.out.println("in c");
                        this.SERVER.MainDatabase.CreateGroup(newReceivedPackage);


                    } else if (newReceivedPackage.action=='m') {
                        System.out.println("in m");
                        this.SERVER.MainDatabase.addMember(newReceivedPackage);

                    } else if (newReceivedPackage.action=='h') {
                        System.out.println("in h");
                        this.SERVER.MainDatabase.addMember((newReceivedPackage));


                    }


                }

            }

            catch(java.net.SocketException se){
                System.out.println("in the catch");
                this.SERVER.MainAddressStore.StoreArray.remove(this.position);
                this.SERVER.BtMain.delete(this.inn,this.tsn);
                System.out.println(this.SERVER.BtMain.search(435456));
            }


            catch (Exception ex){
                this.SERVER.MainAddressStore.StoreArray.remove(this.position);
                this.SERVER.BtMain.delete(this.inn,this.tsn);
                System.out.println("in normal exception");
                System.out.println(ex);
            }





    }


}

