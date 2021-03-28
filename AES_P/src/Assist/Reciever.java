/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assist;

//This is client
import Node.MetaData;
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

            System.out.println("Recieving" + portID);
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

    public synchronized void completionReciever(int prtID) {
        try {
            ServerSocket serverSocket = new ServerSocket(prtID);
            Socket socket = serverSocket.accept();
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
            Object obj = iStream.readObject();

        } catch (Exception e) {
            completionReciever(prtID);
            return;
        }
    }

    public synchronized String singleRec() {

        try {
            ServerSocket serverSocket = new ServerSocket(portID);
            Socket socket = serverSocket.accept();
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
            Object obj = iStream.readObject();

            String recieved = (String) obj;
            //System.out.println("Assist.Reciever.singleRec()");
            //System.out.println(recieved);
            serverSocket.close();
            return recieved;

        } catch (Exception e) {
            System.out.println(e + "Port in use");
            return singleRec();
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

    public String initializeNode(int ID, int totalProcesses) {

        Integer i = totalProcesses;
        Integer j = ID;
        String proString = i.toString();
        String idString = j.toString();

        try {
            ServerSocket serverSocket = new ServerSocket(portID);
            Socket socket = serverSocket.accept();
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());

            Object obj = iStream.readObject();
            oStream.writeObject(proString);
            oStream.writeObject(idString);

            String recieved = (String) obj;

            System.out.println(recieved);
            serverSocket.close();

            return recieved;

        } catch (Exception e) {
            System.out.println(e + "Port in use");
            return initializeNode(ID, totalProcesses);
        }

    }

    public MetaData recieveMetadata() {

        try {
            ServerSocket serverSocket = new ServerSocket(portID);
            Socket socket = serverSocket.accept();
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
            
            Object obj = iStream.readObject();
            
            MetaData mdt = (MetaData) obj;
            
            mdt.printMetadata();
            return mdt;
            

        } catch (Exception e) {
            return recieveMetadata();
        }

        
    }
    
    public String waitForSendFinish() {

        

        try {
            ServerSocket serverSocket = new ServerSocket(portID);
            Socket socket = serverSocket.accept();
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());

            Object obj = iStream.readObject();
            String recieved = (String) obj;

            System.out.println(recieved);
            serverSocket.close();

            return recieved;

        } catch (Exception e) {
            System.out.println(e + "Port in use");
            return waitForSendFinish();
        }

    }

    public String singleRec2() {
        try {
            System.out.println("Assist.Reciever.singleRec2 1()");
            ServerSocket serverSocket = new ServerSocket(portID);
            System.out.println("Assist.Reciever.singleRec2 2()");
            Socket socket = serverSocket.accept();
            System.out.println("Assist.Reciever.singleRec2 3()");
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Assist.Reciever.singleRec2 4()");
            Object obj = iStream.readObject();
            serverSocket.close();

            String recieved = (String) obj;
            System.out.println("Assist.Reciever.singleRec() waeawe");
            System.out.println(recieved);
            
            return recieved;

        } catch (Exception e) {
            System.out.println(e + "Port in use");
            return singleRec();
        }
        
    }
    

}
