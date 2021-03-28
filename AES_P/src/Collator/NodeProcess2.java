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
    
    public static void main(String[] args) throws IOException, InterruptedException, Exception{
        int collatorPortID = 22220;
        
        System.out.println("Process Started");
        
        //Scanner sc = new Scanner(System.in);
        //int portID = sc.nextInt();
                       
        Node node = new Node(45);
        System.out.println("Process Started 2");
        node.senderC.sendInitializer("localhost", new AddressPort("localhost", 22220));
        
        
        
        
        return;
        
        
                   
        
        
        
        
        
        
        
    }
    
}
