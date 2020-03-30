import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        InetAddress host = InetAddress.getLocalHost();
        Socket socket;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        for(int i=0; i<5;i++){
            socket = new Socket(host.getHostName() , 1998);
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if(i==4)oos.writeObject(", oh no corona");
            else oos.writeObject(" "+i);
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
    }
}
