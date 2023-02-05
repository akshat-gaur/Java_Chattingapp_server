import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class RecievedPackage{
    container c;
    StoreNode s;

    RecievedPackage(container c,StoreNode s){
        this.c=c;
        this.s=s;
    }


}

class SendingThread extends Thread {
    public void run(binary_tree_server_con curr) {
        Socket s = curr.bs;
        ObjectOutputStream oos;
        while (true) {
            try {
                int size = curr.SendContainer.size();
                if (size >= 1) {
                    oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(curr.SendContainer.remove());
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}

    class ReceivingThread extends Thread{
        public void run(binary_tree_server_con curr){
            Socket s=curr.bs;
            ObjectInputStream ois;
            RecievedPackage rs;
            while (true){
                try{
                    ois=new ObjectInputStream(s.getInputStream());
                    rs=(RecievedPackage) ois.readObject();
                    curr.AppendRecievedContainer(rs);
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
        }

    }


    class SendMessg extends Thread{
    public void run(binary_tree_server_con curr){
        RecievedPackage rp=curr.RecievedContainer.remove();
        StoreNode sn=rp.s;
        sn.Send(rp.c);
    }

    }




public class binary_tree_server_con {
    server SERVER;
    Socket bs;
    Queue<container> SendContainer = new LinkedList<>();

    Queue<RecievedPackage> RecievedContainer=new LinkedList<>();


    binary_tree_server_con(String TreeServerIP,int port,server SERVER){
        this.SERVER=SERVER;
        try {
            this.bs = new Socket(TreeServerIP, port);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    synchronized void AppendSendContainer(container ns){
        this.SendContainer.add(ns);
    }

    synchronized void AppendRecievedContainer(RecievedPackage ns){
        this.RecievedContainer.add(ns);
    }







}



