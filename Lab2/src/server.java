import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class server implements Runnable{

        private Socket socket;

        server(Socket serversocket){
            this.socket = serversocket;
        }

        public void run(){
            System.out.println("Waiting for the client request");
            String message;
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                message = (String) ois.readObject();
                System.out.println("Message Received: " + message);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject("Hello you"+message);
                ois.close();
                oos.close();
                socket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Shutting down Socket server!!");
        }

}
