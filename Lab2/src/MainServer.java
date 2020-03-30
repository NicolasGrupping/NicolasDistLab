import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {
    private static final int PORT_NUMBER = 1998;
    private static ServerSocket serverSocket;
    private static server clientHandler;
    private static Thread thread;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(PORT_NUMBER);

        while (true) {
            clientHandler = new server(serverSocket.accept());
            thread = new Thread(clientHandler);
            thread.start();
        }
    }

    protected void finalize() throws IOException {
        serverSocket.close();
        System.out.println("Shutting down Socket server!!");
    }
}
