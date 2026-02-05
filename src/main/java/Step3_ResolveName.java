import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Step 3: DNS Name Resolution
 *
 * This class demonstrates how to resolve domain names (hostnames) to IP addresses
 * using Java's built-in DNS resolution capabilities.
 *
 * <h2>Key Concepts Covered:</h2>
 * <ul>
 *   <li><b>DNS (Domain Name System)</b> - Translates human-readable domain names to IP addresses</li>
 *   <li><b>InetAddress.getByName()</b> - Performs DNS lookup for a given hostname</li>
 *   <li><b>UnknownHostException</b> - Thrown when DNS resolution fails</li>
 *   <li><b>Streaming I/O</b> - Uses Java 8 streams to process input lines</li>
 * </ul>
 *
 * <h2>How It Works:</h2>
 * <ol>
 *   <li>Reads hostnames from standard input (one per line)</li>
 *   <li>Performs DNS resolution for each hostname</li>
 *   <li>Prints the resolved IP address or "Unknown host" if resolution fails</li>
 *   <li>Continues until EOF (Ctrl+D on Unix, Ctrl+Z on Windows)</li>
 * </ol>
 *
 * <h2>Example Usage:</h2>
 * <pre>
 * Input:  google.com
 * Output: 142.250.185.46
 *
 * Input:  nonexistent.invalid
 * Output: Unknown host
 * </pre>
 *
 * <h2>Learning Objectives:</h2>
 * <ul>
 *   <li>Understand how DNS resolution works in Java</li>
 *   <li>Learn to handle resolution failures gracefully</li>
 *   <li>Practice using Java streams for input processing</li>
 * </ul>
 *
 * @see java.net.InetAddress
 * @see java.net.UnknownHostException
 */
public class Step3_ResolveName {

  /**
   * Main entry point that reads hostnames from stdin and resolves them to IP addresses.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {

    // Create a BufferedReader to read from standard input
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // Process each line of input using Java 8 streams
    input.lines().forEach((line) -> {

      try {
        // Perform DNS resolution - converts hostname to InetAddress
        // This contacts the system's configured DNS server
        InetAddress address = InetAddress.getByName(line);

        // Print the resolved IP address in dotted decimal notation (e.g., "192.168.1.1")
        System.out.println(address.getHostAddress());

      } catch (UnknownHostException e) {
        // DNS resolution failed - hostname doesn't exist or DNS server unreachable
        System.out.println("Unknown host");
      }

    });
  }

}