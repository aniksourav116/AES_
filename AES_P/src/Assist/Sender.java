/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assist;

import java.io.IOException;
import java.net.Socket;
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
    
    public void connect() throws Exception{
        Socket sock = new Socket("127.0.0.1",9999);
        System.out.println("Sender Connected");
    }
    
        
    @Override
    public void run() {
        System.out.println("Sending");
        try {
            connect();
        } catch (Exception ex) {
            System.out.println("Except s");
        }
        
        
    }
    
    
}
