/**
 * Step 4: Port Configuration Constants
 *
 * This class defines the port numbers used by the UDP Echo Server and Client.
 * Using constants ensures consistency between the client and server components.
 *
 * <h2>Key Concepts Covered:</h2>
 * <ul>
 *   <li><b>Ports</b> - 16-bit numbers (0-65535) that identify specific applications on a host</li>
 *   <li><b>Well-Known Ports (0-1023)</b> - Reserved for system services (requires admin privileges)</li>
 *   <li><b>Registered Ports (1024-49151)</b> - Available for user applications</li>
 *   <li><b>Dynamic/Private Ports (49152-65535)</b> - Typically used for ephemeral client ports</li>
 * </ul>
 *
 * <h2>Why Separate Ports:</h2>
 * <p>The server and client use different ports so they can run on the same machine.
 * The server listens on SERVER_PORT for requests, and the client listens on CLIENT_PORT
 * for responses. This enables bidirectional communication in the echo scenario.</p>
 *
 * @see Step4_UDP_Echo_Server
 * @see Step4_UDP_Echo_Client
 */
public class Step4_PortsUsed {

    /** Port number where the UDP Echo Server listens for incoming messages */
    public static final int SERVER_PORT = 1024;

    /** Port number where the UDP Echo Client listens for server responses */
    public static final int CLIENT_PORT = 1025;
}
