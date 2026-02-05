import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Step 4: Reusable UDP Receiver Component
 *
 * This class provides a reusable UDP packet receiver that can be used by both
 * the server and client components of the echo application. It encapsulates
 * the socket binding and packet receiving logic.
 *
 * <h2>Key Concepts Covered:</h2>
 * <ul>
 *   <li><b>Encapsulation</b> - Wraps UDP receiving logic in a reusable class</li>
 *   <li><b>Socket Binding</b> - Binds to a specific port to receive packets</li>
 *   <li><b>Asynchronous Readiness</b> - The isBound() method allows checking if ready to receive</li>
 *   <li><b>Thread Safety Consideration</b> - Designed to be used with CompletableFuture</li>
 * </ul>
 *
 * <h2>Design Notes:</h2>
 * <ul>
 *   <li>The 1-second delay before binding allows the caller to set up response handling</li>
 *   <li>Package-private visibility limits usage to the same package</li>
 *   <li>Error handling returns exception message as string (for simplicity in training)</li>
 * </ul>
 *
 * @see Step4_UDP_Echo_Server
 * @see Step4_UDP_Echo_Client
 */
class Step4_UDP_Receiver {

  /** Maximum buffer size for receiving data (2000 bytes) */
  private static final int BUF_LEN = 2000;

  /** The port number this receiver will bind to */
  final private int port;

  /** The most recently received packet (available after receive() returns) */
  DatagramPacket receivePacket;

  /** The underlying DatagramSocket; null until receive() is called */
  private DatagramSocket serverSocket = null;

  /**
   * Creates a new UDP receiver that will bind to the specified port.
   *
   * @param port the port number to listen on (1024-65535 recommended for user applications)
   */
  Step4_UDP_Receiver(int port) {
    this.port = port;
  }

  /**
   * Checks if the socket has been bound to the port and is ready to receive.
   * This is useful when using the receiver asynchronously to know when it's ready.
   *
   * @return true if the socket is bound and ready to receive packets
   */
  boolean isBound() {
    return serverSocket != null && serverSocket.isBound();
  }

  /**
   * Binds to the configured port and waits for a UDP packet.
   *
   * <p>This method blocks until a packet is received. It includes a 1-second
   * delay before binding to allow callers time to set up their end of the
   * communication.</p>
   *
   * @return the received message as a String, or the exception message if an error occurs
   */
  String receive() {

    try {
      // Delay to allow the sender to prepare (useful in async scenarios)
      Thread.sleep(1000);

      // Bind to the specified port
      serverSocket = new DatagramSocket(port);

      // Prepare buffer for incoming data
      byte[] receiveData = new byte[BUF_LEN];

      // Create packet to receive data
      receivePacket = new DatagramPacket(receiveData, receiveData.length);

      // Block until a packet arrives
      serverSocket.receive(receivePacket);

      // Return the received data as a string
      return new String(receivePacket.getData());

    } catch (IOException e) {
      // Network or socket error
      return e.toString();

    } catch (InterruptedException e) {
      // Thread was interrupted during sleep
      return e.toString();
    }

  }
}
