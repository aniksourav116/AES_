/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collator;

import Assist.Sender;
import Node.Node;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Anik Sourav
 */
public class Collator {

    public int processCount = 0;

    public static void main(String[] args) throws Exception {

        int totalProcesses = 0;
        int portAlloctionStarter = -1;

        BufferedReader br = new BufferedReader(new FileReader("Collator.txt"));
        String line = null;

        line = br.readLine();
        totalProcesses = Integer.parseInt(line);
        portAlloctionStarter = Integer.parseInt(br.readLine());
        
        //HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
        Vector<Node> nodes = new Vector<Node>();

        for (int i = 0; i < totalProcesses; i++) {
            Node node = new Node(i, portAlloctionStarter + i);
            node.reciever.start();
            node.senderC.start();
            nodes.add(node);
            //node.sendRound();
            //
            //node.senderC.start();

        }

        for (int i = 0; i < totalProcesses; i++) {
            Node node = nodes.get(i);
            node.senderC.join();
            System.out.println("Node " + node.nodeID + "Sent " + node.senderC.tracker);
            System.out.println("Node " + node.nodeID + "Recieved " + node.reciever.tracker);

        }
        System.out.println("All Sent");
        
        
            
            
            
            Sender sender  = new Sender(1,"");
            
            sender.sendFinal();
            
            System.out.println("All done");
            

        

    }

}
