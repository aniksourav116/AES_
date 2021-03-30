
package Collator;

import Assist.*;
import Node.MetaData;
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


public class Collator {

    public int processCount = 0;

    public static void main(String[] args) throws Exception {

        int totalProcesses;
        int portAlloctionStarter;
        int collatorPort = 22220;

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

        String hostsInfo = "";
        Vector<AddressPort> adresses = new Vector<AddressPort>();
        for (int i = 0; i < totalProcesses; i++) {

            Reciever reciever = new Reciever(collatorPort);
            String received = reciever.initializeNode(totalProcesses, portAlloctionStarter + i);
            adresses.add(new AddressPort(received, portAlloctionStarter+i));
            hostsInfo += received + " " + (portAlloctionStarter + i) + "\n";
        }
        System.out.println("HostInfo: ");
        System.out.println(hostsInfo);

        System.out.println("Initialized");
      
        
        
        for (int i = 0; i < totalProcesses; i++) {
            Reciever reciever = new Reciever(collatorPort + 1);
            reciever.singleRec();
            System.out.println("Collator.CollatorMachine.main()");
        }
       

        SenderS senderS = new SenderS();
        senderS.sendFinal2();

        
        Reciever reciever = new Reciever(collatorPort + 2);

        String a = reciever.recieveNumber(totalProcesses);
        System.out.println(a);

        MetaData mdt = new MetaData();
        System.out.println("Sent----Recieved-----SentSummation-----RecievedSummation");
        String metadatas[] = a.split("\n");
        for (int i = 0; i < totalProcesses; i++) {
            System.out.print("\nNode " + i);
            String values[] = metadatas[i].split(" ");
            int sentT = Integer.parseInt(values[0]);
            System.out.print("  :");
            System.out.print(sentT);
            int receivedT = Integer.parseInt(values[1]);
            System.out.print("  :");
            System.out.print(receivedT);
            long sentSum = Long.parseLong(values[2]);
            System.out.print("  :");
            System.out.print(sentSum);
            long receivedsum = Long.parseLong(values[3]);
            System.out.print("  :");
            System.out.print(receivedsum);

            mdt.sendTracker += sentT;
            mdt.recieveTracker += receivedT;
            mdt.sendSummation += sentSum;
            mdt.recieveSummation += receivedsum;

        }

        System.out.println("\nOverall");

        mdt.printMetadata();

    }

}
