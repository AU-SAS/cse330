import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Step1_SocketToGoogle {

  public static void main(String[] args) {

    try {

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