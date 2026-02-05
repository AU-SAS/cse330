import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Step 2B: Receiving UDP Packets
 *
 * This class demonstrates how to receive data using the User Datagram Protocol (UDP).
 * It binds to a specific port and waits (blocks) until a UDP packet is received.
 *
 * <h2>Key Concepts Covered:</h2>
 * <ul>
 *   <li><b>Port Binding</b> - The socket binds to port 1024 to listen for incoming packets</li>
 *   <li><b>Blocking I/O</b> - The receive() method blocks until data arrives</li>
 *   <li><b>Buffer Management</b> - A pre-allocated byte array receives the incoming data</li>
 *   <li><b>DatagramPacket</b> - Contains both the received data and sender information</li>
 * </ul>
 *
 * <h2>How It Works:</h2>
 * <ol>
 *   <li>Creates a DatagramSocket bound to port 1024</li>
 *   <li>Prepares a buffer to hold incoming data (2000 bytes max)</li>
 *   <li>Calls receive() which blocks until a packet arrives</li>
 *   <li>Extracts and displays the received message</li>
 * </ol>
 *
 * <h2>How to Run:</h2>
 * <p>Run this class first, then run {@link Step2_SendPacket_UDP} to send a message.</p>
 *
 * @see Step2_SendPacket_UDP
 * @see java.net.DatagramSocket
 * @see java.net.DatagramPacket
 */
public class Step2_ReceivePacket_UDP {

  /** Maximum buffer size for receiving data (2000 bytes) */
  private static final int BUF_LEN = 2000;

  /**
   * Main entry point that listens for and receives a UDP packet on port 1024.
   *
   * @param args command line arguments (not used)
   * @throws IOException if an I/O error occurs while receiving the packet
   */
  public static void main(String[] args) throws IOException {

    // Create a DatagramSocket bound to port 1024
    // This port must match the destination port used by the sender
    DatagramSocket serverSocket = new DatagramSocket(1024);

    // Pre-allocate a buffer to hold the incoming packet data
    byte[] data = new byte[BUF_LEN];

    // Create a DatagramPacket to receive data into the buffer
    DatagramPacket pkt = new DatagramPacket(data, data.length);

    // Block and wait for an incoming packet
    // This call will not return until a packet is received
    serverSocket.receive(pkt);

    // Convert the received bytes to a String
    // Note: This includes the full buffer, which may contain trailing null bytes
    String sentence = new String(pkt.getData());

    System.out.println("RECEIVED: '" + sentence + "'");
  }

}