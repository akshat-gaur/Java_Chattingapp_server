import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

class StoreNode{
    int Position;
    Socket ss;
    String [] VerArray;

    StoreNode( Socket ss,String [] VerArray){
        this.ss=ss;
        this.VerArray=VerArray;

    }

    public void Send(container c){
        if (c.verification_id==this.VerArray[c.position]){
            try{
                ObjectOutputStream oos=new ObjectOutputStream(this.ss.getOutputStream());
                oos.writeObject(c);
            }

            catch(Exception ex){
                System.out.println(ex);
            }
        }
        else{
            System.out.println("wrong credentials");
        }
    }



    }




class Store{
    ArrayList<StoreNode> StoreArray=new ArrayList<>();
    synchronized int AppendStore(StoreNode ns){
        this.StoreArray.add(ns);
        ns.Position=StoreArray.size()-1;
        return StoreArray.size()-1;
    }

}
