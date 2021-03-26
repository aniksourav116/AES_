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
import java.util.Vector;

/**
 *
 * @author Anik Sourav
 */
public class Node {
    public int nodeID;
    public int portID;
    public Sender sender;
    public Reciever reciever;
    public int status; //0 For Recieving 1 for sending.
    public MetaData metaData;
    public static int processCounter;
    public Vector<AddressPort> AllList;
    //

    public Node(int portID) {
        this.portID = portID;
        //this.sender = new Sender(portID);
        this.reciever = new Reciever(portID);
        this.status = 0;
        this.metaData = new MetaData();
        this.processCounter = 0;
    }
    
    public Node(int nodeID, int portID) {
        this.nodeID = nodeID;
        this.portID = portID;
        //this.sender = new Sender(portID);
        this.reciever = new Reciever(portID);
        this.status = 0;
        this.metaData = new MetaData();
        this.processCounter = 0;
        AllList = new Vector<AddressPort>();
        
        
        
        
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
               AddressPort adp = new AddressPort(Address, prtID);
               AllList.add(adp);
            }
            
            
        }      
        
        
        
    }
    
         
    
    
    public void recieve() throws IOException
    {
        //reciever = new Reciever(9999);
        reciever.recieve();
    }
    
    public void send(int recieverID) throws IOException{
        sender = new Sender(portID);
        sender.send(recieverID);
        
    }
    
    
    
            
    
}
