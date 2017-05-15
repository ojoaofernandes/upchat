package sockets;

import java.io.*;
import java.net.Socket;

/**
 * Created by ei10117 on 11/05/2017.
 */
public class SocketTCP {
    private Socket socket;

    public SocketTCP(String hostname, int port) throws IOException {
        socket = new Socket(hostname,port);
    }

    void send(String msg) throws Exception{
        //create output stream attached to socket
        PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        //send msg to server
        outToServer.print(msg + '\n');
        outToServer.flush();
    }

    String receive() throws Exception{
        //create input stream attached to socket
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //read line from server
        String res = inFromServer.readLine(); // if connection closes on server end, this throws java.net.SocketException
        return res;
    }

    void close() throws IOException{
        socket.close();
    }

    public static void main(String args[]) throws Exception{
        SocketTCP client = new SocketTCP("localhost",6789);
        client.send("Hey dude 1");
        System.out.println("Server Said(1): "+client.receive());
        client.send("Hey dude 2");
        System.out.println("Server Said(2): "+client.receive());
        client.close();
    }
}
