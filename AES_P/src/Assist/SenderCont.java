/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assist;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anik Sourav
 */
public class SenderCont extends Thread {

    public int tracker;
    public long summation;
    public int roundSize;
    public int totalMessages;
    public Vector<AddressPort> AllList;

    public SenderCont(Vector<AddressPort> AllList) {
        this.tracker = 0;
        this.summation = 0;
        this.roundSize = 5;
        this.AllList = AllList;
        this.totalMessages = 50;
        this.roundSize = 5;
    }

    @Override
    public void run() {
        System.out.println("Assist.SenderCont.run()");

        Random random = new Random(116);

        AddressPort adp = AllList.get(random.nextInt(AllList.size()));

        while (this.tracker < totalMessages) {
            try {
                sendRound(adp.adress, adp.portID);
            } catch (IOException ex) {
                System.out.println("Sender Failed");
                return;
            }

        }

        for (int i = 0; i < AllList.size(); i++) {
            System.out.println(AllList.get(i).portID);

        }
    }

    private void sendRound(String adress, int portID) throws IOException {
        Random random = new Random(116);
        Socket socket = new Socket(adress, portID);
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
                    this.summation += iRandom;

                }
                socket.close();

            } catch (Exception e) {

                System.out.println(e);
                return;
            }

        }

    }


