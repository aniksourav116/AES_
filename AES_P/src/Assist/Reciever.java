/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assist;

//This is client
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anik Sourav
 */
public class Reciever extends Thread {

    public int portID;
    public int tracker;
    public long summation;
    public int roundSize;

    public Reciever(int portID) {
        this.portID = portID;
        this.tracker = 0;
        this.summation = 0;
        this.roundSize = 5;
    }
    

    public synchronized String  recieve() throws IOException {
        String payload = "";

        ServerSocket serverSocket = new ServerSocket(portID);
        Socket socket = serverSocket.accept();

        ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
        while (true) {
            try {

                for (int i = 0; i < roundSize; i++) {

                    Object message = iStream.readObject();

                    System.out.println("Message Recieved " + (String) message);
                    payload = (String) message;

                    String returnMessage = "ACCEPTED";
                    System.out.println(tracker);
                    this.tracker++;
                    System.out.println(tracker);
                    oStream.writeObject(returnMessage);

                    if (payload.equals("STOP")) {
                        return " ";
                        //break;
                    } else {
                        
                        int pLoad = Integer.parseInt(payload);
                        this.summation += pLoad;

                    }
                }
                serverSocket.close();

                serverSocket = new ServerSocket(portID);
                socket = serverSocket.accept();

                oStream = new ObjectOutputStream(socket.getOutputStream());
                iStream = new ObjectInputStream(socket.getInputStream());

            } catch (Exception e) {
                System.out.println("Assist.Reciever.Recieve()");
                System.out.println(e);
            }
            
            if (payload.equals("STOP")) {
                break;
            }
        }
        System.out.println("Broken");
        return "DONE";

    }

    public void connect() throws Exception {
        ServerSocket servsocket = new ServerSocket(22222);
        Socket sock = servsocket.accept();
        System.out.println("Reciever Connected");
        //servsocket.close();
    }

    @Override
    public void run() {

        try {
            while (true) {
                System.out.println("Running Reciever");
                recieve();
                System.out.println("Recieve Done");
                break;
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
