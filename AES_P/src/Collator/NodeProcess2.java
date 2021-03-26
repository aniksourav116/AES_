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
public class NodeProcess2 {
    
    public static void main(String[] args) throws IOException{
        System.out.println("Collator Started");
        
        //Scanner scan = new Scanner(System.in);
        
        int choice = 9998; //scan.nextInt();
        Node node = new Node(choice);
        
        node.reciever.start();
        node.sender = new Sender(9999);
        node.sender.start();
        
        
        
    }
    
}
