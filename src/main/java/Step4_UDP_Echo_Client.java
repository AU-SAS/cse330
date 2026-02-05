import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Step 4: UDP Echo Client with Asynchronous Response Handling
 *
 * This class implements a UDP echo client that sends a message to the echo server
 * and asynchronously waits for the response. It demonstrates combining UDP networking
 * with Java's CompletableFuture for non-blocking operations.
 *
 * <h2>Key Concepts Covered:</h2>
 * <ul>
 *   <li><b>Asynchronous Programming</b> - Uses CompletableFuture to wait for responses</li>
 *   <li><b>Full-Duplex Communication</b> - Sends and receives on different ports</li>
 *   <li><b>Polling for Readiness</b> - Waits until receiver is bound before sending</li>
 *   <li><b>Echo Client Pattern</b> - Sends a message and expects it back unchanged</li>
 * </ul>
 *
 * <h2>How It Works:</h2>
 * <ol>
 *   <li>Prepares a UDP packet with the message to send</li>
 *   <li>Creates a receiver in a separate thread to listen for the response</li>
 *   <li>Waits until the receiver is bound to its port</li>
 *   <li>Sends the message to the server</li>
 *   <li>Blocks on the CompletableFuture until the response arrives</li>
 *   <li>Prints both the sent message and received response</li>
 * </ol>
 *
 * <h2>How to Run:</h2>
 * <ol>
 *   <li>Start {@link Step4_UDP_Echo_Server} first</li>
 *   <li>Then run this client</li>
 *   <li>Observe "Hello, World!" being sent and echoed back</li>
 * </ol>
 *
 * <h2>Why Asynchronous?</h2>
 * <p>The client starts listening for responses BEFORE sending the request to avoid
 * a race condition where the response arrives before the client is ready to receive it.
 * The polling loop ensures the receiver is bound before the message is sent.</p>
 *
 * @see Step4_UDP_Echo_Server
 * @see Step4_UDP_Receiver
 * @see Step4_PortsUsed
 * @see java.util.concurrent.CompletableFuture
 */
public class Step4_UDP_Echo_Client {

  /** The message to send to the echo server */
  private static final String MESSAGE = "Hello, World!";

  /**
   * Main entry point for the UDP Echo Client.
   *
   * Sends a message to the echo server and displays the response.
   *
   * @param args command line arguments (not used)
   * @throws IOException if an I/O error occurs during sending
   * @throws ExecutionException if the async receive operation fails
   * @throws InterruptedException if the thread is interrupted while waiting
   */
  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {

    // Convert message to bytes for UDP transmission
    byte[] buffer = MESSAGE.getBytes();

    // Create the outgoing packet addressed to the server
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
        InetAddress.getLocalHost(), Step4_PortsUsed.SERVER_PORT
    );

    // Create socket for sending (will use ephemeral port)
    DatagramSocket datagramSocket = new DatagramSocket();

    // Create receiver to listen for the echo response on CLIENT_PORT
    final Step4_UDP_Receiver receiver = new Step4_UDP_Receiver(Step4_PortsUsed.CLIENT_PORT);

    // Start receiving asynchronously - this runs in a separate thread
    CompletableFuture<String> response = CompletableFuture.supplyAsync(
        () -> receiver.receive()
    );

    // Poll until the receiver is bound and ready
    // This prevents sending before we can receive the response
    int i = 1;
    while (!receiver.isBound()) {
      System.out.println("" + i + ". not bound yet");
      i++;
      Thread.sleep(100);
    }

    // Now safe to send - the receiver is ready
    System.out.println("SENDING: '" + MESSAGE);
    datagramSocket.send(packet);

    // Block until the response arrives (CompletableFuture.get() is blocking)
    String received = response.get();
    System.out.println("SENT: '" + MESSAGE + "' RECEIVED: '" + received + "'");
  }
}
