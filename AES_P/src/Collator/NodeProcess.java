/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collator;

import Assist.*;
import Node.Node;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Anik Sourav
 */
public class NodeProcess {
    
    public static void main(String[] args) throws IOException, InterruptedException, Exception{
        System.out.println("Process Started");
                       
        int choice = 9999; //scan.nextInt();        
        Node node = new Node(choice);
        
        node.reciever.start();
        
        
        node.sender = new Sender(9999);
        node.sender.start();
        node.sender.join();
        node.sender = new Sender(9999);
        node.sender.sendFinal(9999);
        
        
        
        
        
    }
    
}
