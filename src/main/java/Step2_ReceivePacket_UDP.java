import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Step2_ReceivePacket_UDP {

  private static final int BUF_LEN = 2000;

  public static void main(String[] args) throws IOException {

    DatagramSocket serverSocket = new DatagramSocket(1024);
    byte[] data = new byte[BUF_LEN];


    DatagramPacket pkt = new DatagramPacket(data, data.length);

    serverSocket.receive(pkt);

    String sentence = new String(pkt.getData());

    System.out.println("RECEIVED: '" + sentence + "'");
  }

}