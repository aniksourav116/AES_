/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assist;

import Node.MetaData;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class SenderS extends Thread {

    public int tracker;
    public long summation;
    public int roundSize;
    public int totalMessages;
    public Vector<AddressPort> AllList;

    public SenderS(Vector<AddressPort> AllList) {
        this.tracker = 0;
        this.summation = 0;
        this.roundSize = 5;
        this.AllList = AllList;
        this.roundSize = 5;
        this.totalMessages = 10 * 5;
    }

    public SenderS() {
    }

    @Override
    public synchronized void run() {
        System.out.println("Continuous Sending Started");

        Random random = new Random();

        while (this.tracker < totalMessages) {
            AddressPort adp = AllList.get(random.nextInt(AllList.size()));
            try {
                sendRound(adp.adress, adp.portID);
            } catch (IOException ex) {
                System.out.println("Sender Failed");
                return;
            }

        }

        System.out.println("Total Messages Sent: " + this.tracker);

    }

    private synchronized void sendRound(String adress, int portID) throws IOException {

        try {

            Random random = new Random(117);
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
                    //System.out.println((String) reObject);
                    String reString = (String) reObject;

                    if (reString.equals("ACCEPTED")) {
                        this.tracker++;
                    }

                    //System.out.println();
                    this.summation += iRandom;

                }
                //this.tracker+=5;
                socket.close();

            } catch (Exception e) {

                System.out.println(e);
                return;
            }

        } catch (Exception e) {
            System.out.println("Could not connect");
            return;
        }

    }

    public void sendMetaData(MetaData mdt, AddressPort adp) throws InterruptedException {
        //mdt.printMetadata();
        try {
            System.out.println("pre socket");
            Socket socket = new Socket(adp.adress, adp.portID);
            System.out.println("post socket");
            ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("ost socket");

            oStream.writeObject(mdt);
            System.out.println("ost2 socket");
            socket.close();

        } catch (Exception e) {
            Thread.sleep(1000);                    
            sendMetaData(mdt, adp);
            return;
        }

    }

    public void sendSingleString(String obj, AddressPort adp) {
        try {
            System.out.println("Assist.SenderS.sendSingleString()");
            Socket socket = new Socket(adp.adress, adp.portID);
            ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
            
            oStream.writeObject(obj);
            socket.close();
            System.out.println("Not here" + obj);

        } catch (Exception e) {
            sendSingleString(obj, adp);
            return;
        }

    }

    public void sendComplete() {

    }

    public IDPort sendInitializer(String host, AddressPort adp) {
        System.out.println("Assist.SenderS.sendInitializer()");

        try {
            Socket socket = new Socket(adp.adress, adp.portID);
            ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
            oStream.writeObject(host);

            Object sentItem = iStream.readObject();

            String idN = (String) sentItem;

            int prtID = Integer.parseInt(idN);

            sentItem = iStream.readObject();
            socket.close();

            idN = (String) sentItem;

            int id = Integer.parseInt(idN);

            System.out.println("port id " + prtID);
            System.out.println("id " + id);

            return new IDPort(id, prtID);

        } catch (Exception e) {
            System.out.println("Failed to Send");
            return sendInitializer(host, adp);
        }

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

    public synchronized void sendFinalEach(AddressPort adp) throws Exception {

        try {
            Socket socket = new Socket(adp.adress, adp.portID);
            ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());

            oStream.writeObject("STOP");
            Object object = iStream.readObject();
            socket.close();

        } catch (Exception e) {
            sendFinalEach(adp);

        }

    }

    public synchronized void sendFinal2() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("Nodes.txt"));
        String line;
        int i = 0;
        try {

            while ((line = br.readLine()) != null) {
                String[] datas = line.split(" ");
                String Address = datas[0];
                int prtID = Integer.parseInt(datas[1]);
                
                this.sendFinalEach(new AddressPort(Address, prtID));

            }
        }
        catch(Exception e)
        {
                  System.out.println("Assist.SenderS.sendFinal2()");  
        }
    }
}
