
package Collator;

import Assist.Sender;
import Assist.SenderS;
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
        
        BufferedWriter bw = new BufferedWriter( new FileWriter("Nodes.txt"));
        for (int i = 0; i < totalProcesses; i++) {
            int k = portAlloctionStarter + i;
            line = "localhost "+k+"\n";
            bw.write(line);
        }
        
        
        bw.close();

        
                
                
                
                Vector<Node> nodes = new Vector<Node>();
                
                for (int i = 0; i < totalProcesses; i++) {
                Node node = new Node( portAlloctionStarter + i,totalProcesses);
                node.reciever.start();
                node.senderC.start();
                nodes.add(node);
                //node.sendRound();
                //
                //node.senderC.start();
                
                }
                
                int totalS = 0;
                int totalR = 0;
                long sumS=0;
                long sumR=0;
                
                
                for (int i = 0; i < totalProcesses; i++) {
                Node node = nodes.get(i);
                node.senderC.join();
                //Thread.sleep(5000);
                
                }
                
                SenderS sender = new SenderS();
                
                sender.sendFinal(totalProcesses);
                
                for (int i = 0; i < totalProcesses; i++) {
                Node node = nodes.get(i);
                node.reciever.join();
                
                System.out.println("Node " + node.nodeID + "Sent " + node.senderC.tracker);
                System.out.println("Node " + node.nodeID + "Recieved " + node.reciever.tracker);
                totalS += node.senderC.tracker;
                totalR += node.reciever.tracker;
                sumS+=node.senderC.summation;
                sumR+=node.reciever.summation;
                
                }
                
                System.out.println("Total Sent: " + totalS);
                System.out.println("Total Recieved: " + totalR);
                
                System.out.println("Total Sent Sum: " + sumS);
                System.out.println("Total Recieved Sum: " + sumR);
                
                System.out.println("All done");
                
    }

}
