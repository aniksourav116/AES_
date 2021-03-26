/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Node;

import Assist.*;
import java.io.IOException;

/**
 *
 * @author Anik Sourav
 */
public class Node {
    public int portID;
    public Sender sender;
    public Reciever reciever;
    public int status; //0 For Recieving 1 for sending.
    public MetaData metaData;
    public static int processCounter;

    public Node(int portID) {
        this.portID = portID;
        //this.sender = new Sender(portID);
        this.reciever = new Reciever(portID);
        this.status = 0;
        this.metaData = new MetaData();
        this.processCounter = 0;
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
