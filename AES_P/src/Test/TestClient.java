/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Scanner;


/**
 *
 * @author Anik Sourav
 */
public class TestClient {
    public static void main(String[] args) throws IOException{
        Socket clientSocket = new Socket("localhost", 9993);
        
        ObjectOutputStream oStream = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(clientSocket.getInputStream());
        
        Scanner in=new Scanner(System.in);
        String message=in.nextLine();
        
        oStream.writeObject(message);
        
        
        try {
            Object recieved = iStream.readObject();
            System.out.println("From Server"+ (String) recieved );
            
        } catch (Exception e) {
            System.out.println("Test.TestClient.main()");
        }
        
        
    }
    
}
