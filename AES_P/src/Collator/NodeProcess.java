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
        int collatorPortID = 22220;
        
        System.out.println("Process Started");
        
        Scanner sc = new Scanner(System.in);
        int portID = sc.nextInt();
                       
        Node node = new Node(portID);
        
        node.reciever.start();
        node.senderC.start();
        
        node.senderC.join();
        node.reciever.join();
        AddressPort adp = null; 
        
        //implement getting collator info
        
        
        node.senderC.sendMetaData(node.metaData, adp);
        
        return;
        
        
                   
        
        
        
        
        
        
        
    }
    
}
