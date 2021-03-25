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
public class Sender implements Runnable{

    public int portID;

    public Sender(int portID) {
        this.portID = portID;
    }
    
    public String send(int recieverPrtID) throws IOException
    {
        Socket socket = new Socket("localhost",recieverPrtID);
        
        ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
        
        try {
            String payload = "1000";
            oStream.writeObject(payload);
            Object reObject = iStream.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return "DONE";
    }
    
    public void connect() throws Exception{
        Socket sock = new Socket("127.0.0.1",9999);
        System.out.println("Sender Connected");
    }
    
        
    @Override
    public void run() {
        System.out.println("Sending");
        try {
            //connect();
            ServerSocket serverSocket =  new ServerSocket(9999);
        Socket sock = serverSocket.accept();
        
        ObjectOutputStream oStream = new ObjectOutputStream(sock.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(sock.getInputStream());
        
        
        try {
            Object recieved = iStream.readObject();
            System.out.println("Recieved"+(String) recieved);
            
            oStream.writeObject("Sent From Server");
            
        } catch (Exception e) {
            System.out.println("Test.TestServer.main()");
        }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        
    }
    
    
}
