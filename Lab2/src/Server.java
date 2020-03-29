import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket host5Server;
    private static int port = 1998;

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        host5Server = new ServerSocket(port);
        while(true){
            System.out.println("Waiting for the client");
            Socket socket = host5Server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Hello you "+message);
            ois.close();
            oos.close();
            socket.close();
            if(message.equalsIgnoreCase("oh no corona")) break;
        }
        System.out.println("Shutting down Socket server!!");
        host5Server.close();
    }

}