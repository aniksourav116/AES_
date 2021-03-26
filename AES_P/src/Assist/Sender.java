/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assist;

//This is server
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anik Sourav
 */
public class Sender extends Thread {

    public int tracker;
    public int summation;

    public int portID;
    public String address;
    public int roundSize;

    public Sender(int portID) {
        this.portID = portID;
        this.roundSize = 5;
    }

    public Sender(int portID, String address) {
        this.portID = portID;
        this.address = address;
        this.roundSize = 5; //ROundSIze

        tracker = 0;
        summation = 0;

    }

    public String send(int recieverPrtID) throws IOException {
        Socket socket = new Socket("localhost", recieverPrtID);

        ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());

        try {
            String payload = "100";

            for (int i = 0; i < roundSize; i++) {
                oStream.writeObject(payload);
                Object reObject = iStream.readObject();
                System.out.println((String) reObject);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        socket.close();
        oStream.close();
        iStream.close();

        //socket = new Socket("localhost", recieverPrtID);
        //oStream = new ObjectOutputStream(socket.getOutputStream());
        //iStream = new ObjectInputStream(socket.getInputStream());
        //oStream.writeObject("STOP");
        return "DONE";
    }

    public void SendRound() throws IOException {

        Random random = new Random(100);
        System.out.println(address);
        Socket socket = new Socket(address, portID);

        ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());

        try {

            for (int i = 0; i < roundSize; i++) {

                Integer iRandom = random.nextInt();

                //int ir = iRandom;
                
                
                
                String payload = iRandom.toString();

                oStream.writeObject(payload);
                Object reObject = iStream.readObject();
                System.out.println((String) reObject);
                
                //System.out.println();
                
                this.tracker++;
                this.summation+=iRandom;
                
                

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        socket.close();
        oStream.close();
        iStream.close();

        //socket = new Socket("localhost", recieverPrtID);
        //oStream = new ObjectOutputStream(socket.getOutputStream());
        //iStream = new ObjectInputStream(socket.getInputStream());
        //oStream.writeObject("STOP");
        return;

    }

    public void sendFinal(int recieverPortID) throws Exception {
        Socket socket = new Socket("localhost", recieverPortID);
        ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());

        oStream.writeObject("STOP");
    }
   

    @Override
    public void run() {
        
        try {
            SendRound();
        } catch (IOException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
