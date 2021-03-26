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
public class Reciever extends Thread{
    int portID;
    int tracker;
    long summation;
    public Reciever(int portID) {
        this.portID = portID;
    }
    
    public String recieve () throws IOException
    {
        String payload = "";
        
        ServerSocket serverSocket = new ServerSocket(portID);
        Socket socket = serverSocket.accept();
        
        ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
        
        try {
            Object message = iStream.readObject();
            
            System.out.println((String) message);
            payload = (String) message;
            
            String returnMessage = "ACCEPTED";
            
            oStream.writeObject(returnMessage);
            serverSocket.close();
            socket.close();
            oStream.close();
            iStream.close();
            
            
            
            
            
        } catch (Exception e) {
            System.out.println("Assist.Reciever.Recieve()");
            System.out.println(e);
        }
        
        socket.close();
        oStream.close();
        iStream.close();
        
        
        
        
        return "DONE";
        
        
        
        
    }

    
    public void connect() throws Exception{
        ServerSocket servsocket = new ServerSocket(22222);
        Socket sock = servsocket.accept();
        System.out.println("Reciever Connected");
        //servsocket.close();
    }
    
    
    @Override
    public void run()
    {
        
        
        try {
            while (true) {                
                System.out.println("Running Reciever");
                recieve();
                System.out.println("Recieve Done");
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
