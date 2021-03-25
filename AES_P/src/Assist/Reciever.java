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
public class Reciever implements Runnable{
    int portID;

    public Reciever(int portID) {
        this.portID = portID;
    }
    
    public String Recieve () throws IOException
    {
        String payload = "";
        
        ServerSocket serverSocket = new ServerSocket(portID);
        Socket socket = serverSocket.accept();
        
        ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(socket.getInputStream());
        
        try {
            Object message = iStream.readObject();
            
            payload = (String) message;
            
            String returnMessage = "ACCEPTED";
            
            oStream.writeObject(returnMessage);
            
            
            
            
            
        } catch (Exception e) {
            System.out.println("Assist.Reciever.Recieve()");
            System.out.println(e);
        }
        
        
        
        
        
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
        System.out.println("Started Recieving");
        try {
            //connect();
            
            Socket clientSocket = new Socket("localhost", 9998);
        
        ObjectOutputStream oStream = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(clientSocket.getInputStream());
        
        //Scanner in=new Scanner(System.in);
        //String message=in.nextLine();
        
        oStream.writeObject("message");
        
        
        try {
            Object recieved = iStream.readObject();
            System.out.println("From Server"+ (String) recieved );
            
        } catch (Exception e) {
            System.out.println("Test.TestClient.main()");
        }
        } catch (Exception ex) {
            //System.out.println("Except");
            System.out.println(ex);
        }
    }
    
}
