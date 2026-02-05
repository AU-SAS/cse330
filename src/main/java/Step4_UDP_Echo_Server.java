import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Step 4: UDP Echo Server
 *
 * This class implements a simple UDP echo server that receives a message from a client
 * and sends it back (echoes it) to the client. This is a classic networking exercise
 * that demonstrates bidirectional UDP communication.
 *
 * <h2>Key Concepts Covered:</h2>
 * <ul>
 *   <li><b>Echo Protocol</b> - A simple protocol that returns whatever it receives</li>
 *   <li><b>Server Pattern</b> - Waits for incoming requests, then responds</li>
 *   <li><b>Component Reuse</b> - Uses {@link Step4_UDP_Receiver} for receiving logic</li>
 *   <li><b>Fixed Response Port</b> - Sends response to a known client port</li>
 * </ul>
 *
 * <h2>How It Works:</h2>
 * <ol>
 *   <li>Creates a receiver bound to SERVER_PORT (1024)</li>
 *   <li>Blocks waiting for an incoming UDP packet</li>
 *   <li>Extracts the received message</li>
 *   <li>Sends the same message back to the client on CLIENT_PORT (1025)</li>
 * </ol>
 *
 * <h2>How to Run:</h2>
 * <ol>
 *   <li>Start this server first</li>
 *   <li>Then run {@link Step4_UDP_Echo_Client} to send a message</li>
 *   <li>Observe the message being echoed back to the client</li>
 * </ol>
 *
 * <h2>Limitations:</h2>
 * <ul>
 *   <li>Only handles one message then exits (not a continuous server)</li>
 *   <li>Assumes client is on localhost</li>
 *   <li>Uses fixed port numbers (not dynamic discovery)</li>
 * </ul>
 *
 * @see Step4_UDP_Echo_Client
 * @see Step4_UDP_Receiver
 * @see Step4_PortsUsed
 */
public class Step4_UDP_Echo_Server {

    /**
     * Main entry point for the UDP Echo Server.
     *
     * Waits for a message on SERVER_PORT and echoes it back to CLIENT_PORT.
     *
     * @param args command line arguments (not used)
     * @throws IOException if an I/O error occurs during sending or receiving
     */
    public static void main(String[] args) throws IOException {

        // Create a receiver to listen on the server port
        Step4_UDP_Receiver receiver = new Step4_UDP_Receiver(Step4_PortsUsed.SERVER_PORT);

        // Block until a message is received
        String sentence = receiver.receive();

        // Create a response packet with the same content (echo)
        // Note: sentence.length() gives character count, which may differ from byte count
        // for non-ASCII characters, but works for this training example
        DatagramPacket packet = new DatagramPacket(
            sentence.getBytes(), sentence.length(),
            InetAddress.getLocalHost(), Step4_PortsUsed.CLIENT_PORT
        );

        // Create a new socket to send the response
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(packet);
    }
}
