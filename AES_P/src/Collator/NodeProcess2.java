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
        int totalProcesses;
        
        System.out.println("Process Started");
        
        //Scanner sc = new Scanner(System.in);
        //int portID = sc.nextInt();
                       
        SenderS senderS = new SenderS();
        IDPort idp = senderS.sendInitializer("localhost",new AddressPort("localhost", collatorPortID));
        
        //System.out.println(idp.id);
        //System.out.println(idp.portID);
        totalProcesses = idp.id;
        Node newNode = new Node(idp.portID);
        
        newNode.reciever.start();
        newNode.senderC.start();
        
        newNode.senderC.join();
        
        senderS.sendSingleString(Integer.toString(idp.portID), new AddressPort("localhost", collatorPortID));
        newNode.reciever.join();
        System.out.println("Process Complete");
        
        newNode.prepareMetaData();
        //Thread.sleep(1000);
        newNode.senderC.sendSingleString(newNode.metaData.metaDataToString(), new  AddressPort("localhost", collatorPortID));
        //Thread.sleep(1000);
        
        
        
        
        
        
        
        
        
        
        return;
        
        
                   
        
        
        
        
        
        
        
    }
    
}
