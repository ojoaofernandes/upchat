package connection;

/**
 * Created by ei10117 on 26/05/2017.
 */
import handlers.ReceiveHandler;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

class ReceiverListener extends Thread{
    private Socket socket;

    public ReceiverListener(Socket s) throws IOException {
        this.socket = new Socket("localhost", 9090);
    }

    public ReceiverListener() throws IOException {
        this.socket = new Socket("localhost", 5570);
    }

    public static void main(String[] args) throws IOException {
        ReceiverListener receiverListener = new ReceiverListener();
        receiverListener.start();
    }

    @Override
    public void run() {

        while (true) {
            BufferedReader br = null;

            InputStream inputStream = null;
            ArrayList<String>   fields = new ArrayList<String>();

            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String answer = null;


                while((answer=br.readLine()) != null)
                {
                    fields.add(answer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String answer = null;

            try {
                answer = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(answer != null)
           JOptionPane.showMessageDialog(null, answer);

            if(fields.size() != 0  )
            (new ReceiveHandler(fields)).start();
            br = null;

        }

    }
}