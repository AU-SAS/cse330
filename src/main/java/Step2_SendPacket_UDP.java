import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Step2_SendPacket_UDP {

  private static final String MESSAGE = "Hello, World!";

  public static void main(String[] args) throws IOException {

    byte[] buffer = MESSAGE.getBytes();

    InetAddress address = InetAddress.getLocalHost();

    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 1024);

    DatagramSocket datagramSocket = new DatagramSocket();

    datagramSocket.send(packet);

    System.out.println("SENT: '" + MESSAGE + "'");
  }
}