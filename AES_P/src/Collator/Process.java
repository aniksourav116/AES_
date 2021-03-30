
package Collator;

import Assist.*;
import Node.Node;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Process {
    
    public static void main(String[] args) throws IOException, InterruptedException, Exception{
        int collatorPortID = 22220;
        int totalProcesses;
        
        System.out.println("Process Started");       
        
                       
        SenderS senderS = new SenderS();
        IDPort idp = senderS.sendInitializer("localhost",new AddressPort("localhost", collatorPortID));
        
        
        
        //System.out.println(idp.id);
        //System.out.println(idp.portID);
        totalProcesses = idp.id;
        Node newNode = new Node(idp.portID, totalProcesses);
        
        newNode.reciever.start();
        newNode.senderC.start();
        
        newNode.senderC.join();
        
        senderS.sendSingleString(Integer.toString(idp.portID), new AddressPort("localhost", collatorPortID+1));
        newNode.reciever.join();
        System.out.println("Process Complete");
        
        newNode.prepareMetaData();
        //Thread.sleep(1000);
        newNode.senderC.sendSingleString(newNode.metaData.metaDataToString(), new  AddressPort("localhost", collatorPortID+2));
        //Thread.sleep(1000);
        return;
        
        
                   
        
        
        
        
        
        
        
    }
    
}
