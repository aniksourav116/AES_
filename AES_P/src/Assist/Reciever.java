/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assist;

import java.net.ServerSocket;
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

    
    public void connect() throws Exception{
        ServerSocket servsocket = new ServerSocket(22222);
        System.out.println("Reciever Connected");
        servsocket.close();
    }
    
    
    @Override
    public void run()
    {
        System.out.println("Started Recieving");
        try {
            connect();
        } catch (Exception ex) {
            System.out.println("Except");
        }
    }
    
}
