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
    int portID;
    Sender sender;
    Reciever reciever;
    
    public void recieve() throws IOException
    {
        reciever = new Reciever(9999);
        reciever.recieve();
    }
    
    public void send() throws IOException{
        sender = new Sender(9999);
        sender.send(9999);
        
    }
    
    
    
            
    
}
