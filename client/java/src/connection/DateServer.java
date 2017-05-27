package connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class DateServer {

    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        Socket socket = listener.accept();
        try {
            while (true) {

                InputStream inputStream = null;
                try {
                   BufferedReader inFromClient =
                            new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String read;
                    while((read=inFromClient.readLine()) != null){
                        System.out.println(read);
                    }





                   /* PrintWriter out =
                            new PrintWriter(socket.getOutputStream(), true);
                    out.println("MESSAGE_FROM Ruben \r\n\r\n Hi lets go all round!");*/

                } finally {
                   // socket.close();
                }
            }
        }
        finally {
           // listener.close();
        }
    }
}