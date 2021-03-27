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

    public synchronized String recieve() throws IOException {
        try {

            String payload = "";
            
            System.out.println("Recieving"+portID);
            ServerSocket serverSocket = new ServerSocket(portID);
            Socket socket = serverSocket.accept();
            ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                try {

                    for (int i = 0; i < roundSize; i++) {

                        Object message = iStream.readObject();
                        payload = (String) message;

                        

                        if (payload.equals("STOP")) {
                            serverSocket.close();
                            
                            String returnMessage = "ACCEPTED";
                            oStream.writeObject(returnMessage);
                            return " ";
                            //break;
                        } else {

                            int pLoad = Integer.parseInt(payload);
                            this.tracker++;

                            this.summation += pLoad;
                            String returnMessage = "ACCEPTED";
                            oStream.writeObject(returnMessage);

                        }
                    }
                    //this.tracker+=5;
                    serverSocket.close();

                    serverSocket = new ServerSocket(portID);
                    socket = serverSocket.accept();
                    oStream = new ObjectOutputStream(socket.getOutputStream());
                    iStream = new ObjectInputStream(socket.getInputStream());

                } catch (Exception e) {
                    System.out.println("Problem Recieving");
                    System.out.println(e);
                }

                if (payload.equals("STOP")) {
                    break;
                }
            }
            System.out.println("Broken");

        } catch (Exception e) {
            System.out.println("Reciever Failed");
            return "FAILED";
        }

        return "DONE";

    }

    
    public synchronized void completionReciever(int prtID)
    {
        try {
            ServerSocket serverSocket = new ServerSocket(prtID);
        } catch (Exception e) {
        }
    }


    
    @Override
    public void run() {

        try {
            while (true) {
                //System.out.println("Running Reciever");
                recieve();
                //System.out.println("Recieve Done");
                break;
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
