import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class socket_client {
    public static void main(String args[]){
        try {
            long h=1000000000;
            long st = System.nanoTime()/h;
            long bt =System.nanoTime()/h;
            int check = 0;
            String ip = "localhost";
            int port = 9999;
            Socket Client_socket = new Socket(ip, port);
            String filePath = "C:\\Users\\Lenovo\\Downloads\\rxp.jpg";
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            String[] ver = {"345567", "1222", "89790", "245467"};
            container c = new container("435456", 'a', ver);
            ObjectOutputStream oos = new ObjectOutputStream(Client_socket.getOutputStream());
            c = new container( "7054319", "verification", "youwj", "4567890", 'g');
            System.out.println("secong");
            while (true) {
                if((bt-st)>3 && check==0){

                }
                bt=System.nanoTime()/h;
            }
        }
        catch (Exception ex){

        }
    }

}
