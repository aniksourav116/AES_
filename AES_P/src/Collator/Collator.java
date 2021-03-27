/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collator;

import Assist.Sender;
import Node.Node;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
        int portAlloctionStarter;

        BufferedReader br = new BufferedReader(new FileReader("Collator.txt"));
        String line = null;

        line = br.readLine();
        totalProcesses = Integer.parseInt(line);
        portAlloctionStarter = Integer.parseInt(br.readLine());
        File fl = new File("Nodes.txt");
        FileWriter fw = new FileWriter(fl);
        PrintWriter  pn  = new PrintWriter(fw);
        
        pn.println("11");
        pn.println("11");

        
                
                
                /*
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
                
                int totalS = 0;
                int totalR = 0;
                
                for (int i = 0; i < totalProcesses; i++) {
                Node node = nodes.get(i);
                node.senderC.join();
                
                }
                
                Sender sender = new Sender(1, "");
                
                sender.sendFinal();
                
                for (int i = 0; i < totalProcesses; i++) {
                Node node = nodes.get(i);
                node.reciever.join();
                
                System.out.println("Node " + node.nodeID + "Sent " + node.senderC.tracker);
                System.out.println("Node " + node.nodeID + "Recieved " + node.reciever.tracker);
                totalS += node.senderC.tracker;
                totalR += node.reciever.tracker;
                }
                
                System.out.println("Total Sent: " + totalS);
                System.out.println("Total Recieved: " + totalR);
                System.out.println("All done");
                */;
    }

}
