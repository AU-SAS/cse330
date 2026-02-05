import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Step 2A: Sending UDP Packets
 *
 * This class demonstrates how to send data using the User Datagram Protocol (UDP).
 * UDP is a connectionless protocol that sends data without establishing a connection first.
 *
 * <h2>Key Concepts Covered:</h2>
 * <ul>
 *   <li><b>DatagramSocket</b> - A socket for sending and receiving UDP packets</li>
 *   <li><b>DatagramPacket</b> - A container for data to be sent or received via UDP</li>
 *   <li><b>InetAddress</b> - Represents an IP address (used to specify the destination)</li>
 *   <li><b>Connectionless Communication</b> - UDP doesn't establish a connection before sending</li>
 * </ul>
 *
 * <h2>UDP vs TCP:</h2>
 * <ul>
 *   <li>UDP is faster but unreliable (no guaranteed delivery)</li>
 *   <li>UDP packets may arrive out of order or be lost</li>
 *   <li>UDP has lower overhead (no connection setup/teardown)</li>
 *   <li>UDP is used for real-time applications (video streaming, gaming, DNS)</li>
 * </ul>
 *
 * <h2>How to Run:</h2>
 * <p>Run {@link Step2_ReceivePacket_UDP} first to start listening, then run this class to send the packet.</p>
 *
 * @see Step2_ReceivePacket_UDP
 * @see java.net.DatagramSocket
 * @see java.net.DatagramPacket
 */
public class Step2_SendPacket_UDP {

  /** The message to be sent over UDP */
  private static final String MESSAGE = "Hello, World!";

  /**
   * Main entry point that sends a UDP packet to localhost on port 1024.
   *
   * @param args command line arguments (not used)
   * @throws IOException if an I/O error occurs while sending the packet
   */
  public static void main(String[] args) throws IOException {

    // Convert the message string to a byte array for transmission
    byte[] buffer = MESSAGE.getBytes();

    // Get the IP address of the local machine
    InetAddress address = InetAddress.getLocalHost();

    // Create a DatagramPacket containing:
    // - The data buffer
    // - The length of data to send
    // - The destination address
    // - The destination port (1024)
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 1024);

    // Create a DatagramSocket for sending (uses any available local port)
    DatagramSocket datagramSocket = new DatagramSocket();

    // Send the packet (fire-and-forget - no confirmation of receipt)
    datagramSocket.send(packet);

    System.out.println("SENT: '" + MESSAGE + "'");
  }
}