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
 * @author Anik Sourava
 */
public class CollatorMachine {

    public int processCount = 0;

    public static void main(String[] args) throws Exception {

        int totalProcesses;
        int portAlloctionStarter;

        BufferedReader br = new BufferedReader(new FileReader("CollatorMachine.txt"));
        String line;

        line = br.readLine();
        totalProcesses = Integer.parseInt(line);
        portAlloctionStarter = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new FileWriter("Nodes.txt"));
        for (int i = 0; i < totalProcesses; i++) {
            int k = portAlloctionStarter + i;
            line = "localhost " + k + "\n";
            bw.write(line);
        }
        bw.close();
        //Vector<Node> nodes = new Vector<Node>();
        Sender sender = new Sender(1);
        
        for (int i = 0; i < totalProcesses; i++) {
            
            
        }

        
    }

}
