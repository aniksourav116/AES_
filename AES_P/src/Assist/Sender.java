/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assist;

//This is server
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
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

    public Vector<AddressPort> AllList;

    public Sender(int portID) {
        this.portID = portID;
        this.roundSize = 5;
    }

    public synchronized void sendSpecific(AddressPort adp, String message) {
        try {
            Socket socket = new Socket(adp.adress, adp.portID);
            ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());

            oStream.writeObject("STOP");
            Object object = iStream.readObject();
            socket.close();
        } catch (Exception e) {
            sendSpecific(adp, message);
        }

    }

    public Sender(int portID, String address) {
        this.portID = portID;
        this.address = address;
        this.roundSize = 5; //ROundSIze

        this.tracker = 0;
        this.summation = 0;

    }

    public synchronized void sendFinal() throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("Nodes.txt"));
        String line;
        int i = 0;
        try {

            while ((line = br.readLine()) != null) {
                String[] datas = line.split(" ");
                String Address = datas[0];
                int prtID = Integer.parseInt(datas[1]);
                try {
                    Socket socket = new Socket(Address, prtID);
                    ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());

                    oStream.writeObject("STOP");
                    Object object = iStream.readObject();
                    socket.close();
                } catch (Exception e) {

                    System.out.println("Not Connecting");
                    Thread.sleep(1000);
                    Socket socket = new Socket(Address, prtID);
                    ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());

                    oStream.writeObject("STOP");
                    Object object = iStream.readObject();
                    socket.close();

                }

                //System.out.println((String) object);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("455");

        }

    }

    @Override
    public void run() {

        return;

    }

}
