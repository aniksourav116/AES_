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
public class TestClient2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int k = 0; k < 10; k++) {

            for (int j = 0; j < 4; j++) {

                Socket clientSocket = new Socket("localhost", 9990 + j);

                ObjectOutputStream oStream = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream iStream = new ObjectInputStream(clientSocket.getInputStream());
                
                
                
                //oStrea.writeObject("STO");
                
                //Scanner in=new Scanner(System.in);
                String message = "100";
                for (int i = 0; i < 5; i++) {
                    Integer a = i;
                    message = a.toString();
                    //message = "STOP";
                    try {
                        oStream.writeObject(message);
                    } catch (Exception e) {

                        //Thread.sleep(1000);
                        oStream.writeObject(message);
                    }

                    //Scanner in = new Scanner(System.in);
                    //String tt = in.nextLine();
                    //Thread.sleep(1000);
                    try {
                        Object recieved = iStream.readObject();
                        System.out.println("From Server" + (String) recieved);

                    } catch (Exception e) {
                        System.out.println("Test.TestClient.main()");
                    }
                }
                
                try {
                    System.out.println("Test.TestClient2.main()1");
                    Socket clentSocket = new Socket("localhost", 9990 + j);
                    System.out.println("Test.TestClient2.main()2");
                    ObjectOutputStream oStrea = new ObjectOutputStream(clientSocket.getOutputStream());
                    System.out.println("Test.TestClient2.main()3");
                    //ObjectInputStream iStrea = new ObjectInputStream(clientSocket.getInputStream());
                    oStrea.writeObject("STOP");
                    System.out.println("Test.TestClient2.main()4");
                    
                } catch (Exception e) {
                    System.out.println("Port in use");
                }
                
                
                clientSocket.close();

            }
        }

        for (int j = 0; j < 4; j++) {
            Socket clientSocket = new Socket("localhost", 9990 + j);

            ObjectOutputStream oStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream iStream = new ObjectInputStream(clientSocket.getInputStream());
            oStream.writeObject("STOP");
            clientSocket.close();
            
        }
    }

}
