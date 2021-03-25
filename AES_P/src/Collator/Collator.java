/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collator;

import Assist.*;

/**
 *
 * @author Anik Sourav
 */
public class Collator {
    public static void main(String[] args) {
        System.out.println("Collator.Collator.main()");
        Sender sender = new Sender(9999);
        Reciever reciever = new Reciever(9999);
        reciever.run();
        sender.run();
        
        
        
        
        
    }
    
}
