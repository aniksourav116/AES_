/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import java.io.IOException;
import java.io.*;
import java.net.*;

/**
 *
 * @author Anik Sourav
 */
public class TestServer {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket =  new ServerSocket(9999);
        Socket sock = serverSocket.accept();
        
        ObjectOutputStream oStream = new ObjectOutputStream(sock.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(sock.getInputStream());
        
        while (true) {            
            
        
        
        try {
            Object recieved = iStream.readObject();
            System.out.println("Recieved"+(String) recieved);
            
            if (recieved.equals("stop")) {
                return;
            }
            
            oStream.writeObject("Sent From Server");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        }
    }
}
