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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anik Sourav
 */
public class Sender extends Thread {

    public int portID;
    public  int roundSize;
    public Sender(int portID) {
        this.portID = portID;
        this.roundSize = 5;
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
    
    public void sendFinal(int recieverPortID) throws Exception{
        Socket socket = new Socket("localhost", recieverPortID);
        ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
        
        oStream.writeObject("STOP");
    }
    
    public void connect() throws Exception {
        Socket sock = new Socket("127.0.0.1", 9999);
        System.out.println("Sender Connected");
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Assist.Sender.run()");

        }

        try {
            send(portID);
        } catch (IOException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
