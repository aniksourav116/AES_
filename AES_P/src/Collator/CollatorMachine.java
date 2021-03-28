/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collator;

import Assist.*;
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
import javax.sound.midi.Receiver;

/**
 *
 * @author Anik Sourava
 */
public class CollatorMachine {

    public int processCount = 0;

    public static void main(String[] args) throws Exception {

        int totalProcesses;
        int portAlloctionStarter;
        int collatorPort = 22222;
        
        BufferedReader br = new BufferedReader(new FileReader("CollatorMachine.txt"));
        String line;
        
        line = br.readLine();
        totalProcesses = Integer.parseInt(line);
        portAlloctionStarter = Integer.parseInt(br.readLine());
        
        
        for (int j = 0; j < totalProcesses; j++) {
            String filName = "Nodes"+j+".txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(filName));
            int m = portAlloctionStarter + j;
            line = "localhost " + m + "\n";
            bw.write(line);
            for (int i = 0; i < totalProcesses; i++) {
                if (i == j) {
                    continue;
                }
                int k = portAlloctionStarter + i;
                line = "localhost " + k + "\n";
                bw.write(line);
            }
            bw.close();
        }

        
        
        
        //Vector<Node> nodes = new Vector<Node>();
        //Sender sender = new Sender(1);
        //Reciever receiver = new Reciever(collatorPort);

        //receiver.completionReciever(collatorPort);
        for (int i = 0; i < totalProcesses; i++) {
            //Sting receiver.singleRec();
            Reciever reciever = new Reciever(collatorPort);
            String sent = reciever.initializeNode(portAlloctionStarter+i);
            System.out.println(sent);
            

        }

    }

}
