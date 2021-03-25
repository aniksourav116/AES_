/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assist;

/**
 *
 * @author Anik Sourav
 */
public class Sender implements Runnable{

    int portID;

    public Sender(int portID) {
        this.portID = portID;
    }
    
    
        
    @Override
    public void run() {
        System.out.println("Sending");
        
    }
    
    
}
