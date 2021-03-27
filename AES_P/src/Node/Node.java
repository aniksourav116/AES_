/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Node;

import Assist.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Anik Sourav
 */
public class Node {
    public int nodeID;
    public int portID;
    public String address;
    public Sender sender;
    public Reciever reciever;
    public static int processCount = 0; 
    public MetaData metaData;
    public static int processCounter;
    public Vector<AddressPort> AllList;
    public SenderCont senderC;
    //

    public Node(int portID) throws IOException {
        
        this.nodeID = processCount++;
        
        this.portID = portID;
        
        this.reciever = new Reciever(portID);
        this.metaData = new MetaData();
        this.processCounter = 0;
        this.portID = portID;        
        this.reciever = new Reciever(portID);        
        this.metaData = new MetaData();
        this.processCounter = 0;
        AllList = new Vector<AddressPort>();
        this.getOtherNodeData();
        this.senderC = new SenderCont(AllList);
        
    }
    
    public Node(int nodeID, int portID) throws IOException {
        this.nodeID = nodeID;
        //System.out.println("Node.Node.<init>()" +portID);
        this.portID = portID;
        //this.sender = new Sender(portID);
        this.reciever = new Reciever(portID);
        //this.status = 0;
        this.metaData = new MetaData();
        this.processCounter = 0;
        AllList = new Vector<AddressPort>();
        this.getOtherNodeData();
        this.senderC = new SenderCont(AllList);
        
        
        
        
    }
    
    public void getOtherNodeData() throws IOException
    {
        BufferedReader br = new BufferedReader( new FileReader("Nodes.txt"));
        String line;
        int i =0;
        
        while ( (line=br.readLine())!=null) {
            String[] datas = line.split(" ");
            String Address = datas[0];
            int prtID = Integer.parseInt(datas[1]);
            if(prtID!=this.portID){
                //This is for running in same 
               AddressPort adp = new AddressPort(Address, prtID);
               AllList.add(adp);
            }
            else
            {
                this.address = Address;
            }
            
            
        }      
        
        
        
    }
    
         
    
    
    public void recieve() throws IOException
    {
        //reciever = new Reciever(9999);
        reciever.recieve();
    }
    
    
    
    public void sendRound() throws IOException
    {
        Random random = new Random();
        int choice = random.nextInt(AllList.size());
        
        AddressPort adp = AllList.get(choice);
        
        sender  = new Sender(adp.portID, adp.adress);
        
        sender.start();
        
        
    }
    
            
    
}
