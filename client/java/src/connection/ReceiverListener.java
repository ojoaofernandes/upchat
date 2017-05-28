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
        this.socket = s;
    }

/*    public ReceiverListener() throws IOException {
        this.socket = new Socket("localhost", 9090);
    }*/

    public static void main(String[] args) throws IOException {
        /*ReceiverListener receiverListener = new ReceiverListener();
        receiverListener.start();*/
        System.out.println(  );

    }


    @Override
    public void run() {
        BufferedReader br = null;
        InputStream inputStream = null;
        ArrayList<String>   fields  = null;

        while (true) {



           try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String answer = null;
                fields = new ArrayList<String>();

                while(!(answer=br.readLine()).equals( "#"))
                {

                    System.out.println(answer);
                    fields.add(answer);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            if(fields.size() != 0  )
            (new ReceiveHandler(fields)).start();
            br = null;

        }

    }


}