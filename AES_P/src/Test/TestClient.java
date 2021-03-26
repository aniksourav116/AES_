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

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket clientSocket = new Socket("localhost", 9991);

        ObjectOutputStream oStream = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream iStream = new ObjectInputStream(clientSocket.getInputStream());

        //Scanner in=new Scanner(System.in);
        String message = "100";
        for (int i = 0; i < 5; i++) {
            Integer a = i;
            message = a.toString();
            //message = "STOP";
            try {
                oStream.writeObject(message);
            } catch (Exception e) {
                Thread.sleep(1000);
                oStream.writeObject(message);
            }
            
            //Scanner in = new Scanner(System.in);
            //String tt = in.nextLine();
            Thread.sleep(1000);
            try {
                Object recieved = iStream.readObject();
                System.out.println("From Server" + (String) recieved);

            } catch (Exception e) {
                System.out.println("Test.TestClient.main()");
            }
        }
        clientSocket.close();

    }

}
