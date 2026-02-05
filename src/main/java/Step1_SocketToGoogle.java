import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Step 1: Introduction to TCP Sockets
 *
 * This class demonstrates the fundamental concepts of TCP socket programming in Java.
 * It creates a TCP connection to Google's web server and sends an HTTP GET request.
 *
 * <h2>Key Concepts Covered:</h2>
 * <ul>
 *   <li><b>Socket</b> - A TCP endpoint for two-way communication between programs over a network</li>
 *   <li><b>Port 80</b> - The standard HTTP port used for unencrypted web traffic</li>
 *   <li><b>OutputStream</b> - Used to send data to the remote server</li>
 *   <li><b>InputStream</b> - Used to receive data from the remote server</li>
 *   <li><b>HTTP Protocol</b> - The request format follows HTTP/1.1 specification</li>
 * </ul>
 *
 * <h2>How It Works:</h2>
 * <ol>
 *   <li>Opens a TCP socket connection to www.google.com on port 80</li>
 *   <li>Sends an HTTP GET request for the root path ("/") with required Host header</li>
 *   <li>Reads and prints the HTTP response line by line</li>
 * </ol>
 *
 * <h2>Learning Objectives:</h2>
 * <ul>
 *   <li>Understand how to create a TCP socket connection in Java</li>
 *   <li>Learn to send and receive data over a socket</li>
 *   <li>See the structure of an HTTP request and response</li>
 * </ul>
 *
 * @see java.net.Socket
 * @see java.io.OutputStream
 * @see java.io.BufferedReader
 */
public class Step1_SocketToGoogle {

  /**
   * Main entry point that demonstrates a basic TCP socket connection.
   *
   * Creates a socket to Google's web server, sends an HTTP GET request,
   * and prints the response to the console.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {

    try {
      // Create a TCP socket connection to Google's web server on port 80 (HTTP)
      // This performs DNS resolution and establishes a TCP three-way handshake
      Socket socket = new Socket("www.google.com", 80);

      OutputStream os = socket.getOutputStream();

      os.write(("GET / HTTP/1.1\r\n"
          + "Host: www.google.com\r\n\r\n").getBytes());
      os.flush();

      BufferedReader reader = new BufferedReader(
          new InputStreamReader(socket.getInputStream())
      );
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}