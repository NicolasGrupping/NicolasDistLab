import java.net.*;

public class clientUDP {
        public static void main(String[] args) throws Exception {
            DatagramSocket ds = new DatagramSocket();
            String str = "Welcome java";
            InetAddress ip = InetAddress.getByName("10.0.14.3");

            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 32000);
            ds.send(dp);
            ds.close();
        }
}