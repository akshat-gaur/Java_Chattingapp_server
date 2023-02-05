import com.sun.source.tree.BinaryTree;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

public class server {
    int port;
    Store MainAddressStore;
    binary_tree BtMain;

    DataBase MainDatabase;
    server(int port){
        this.port=port;
    }

    public void Start(){
        try {
            ServerSocket ss = new ServerSocket(this.port);
            System.out.println("server has started");
            this.BtMain=new binary_tree();
            this.MainAddressStore=new Store();
            String url="jdbc:postgresql://localhost:5432/chat";
            String user="postgres";
            String password="akshat";
            this.MainDatabase=new DataBase(url,user,password);
            HandleConnection h;
            while(true) {
                Socket s = ss.accept();
                h = new HandleConnection(s, this);
                h.start();
            }

        }
        catch(Exception ex){
            System.out.println(ex);
        }

    }

}
