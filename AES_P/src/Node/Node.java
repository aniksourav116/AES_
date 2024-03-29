package Node;

import Assist.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Node {

    public int nodeID;
    public int portID;
    public String address;
    public Reciever reciever;
    public static int processCount = 0;
    public MetaData metaData;
    public Vector<AddressPort> AllList;
    //public SenderCont senderC;
    public SenderS senderC;
    public int totalProcesses;
    //

    public Node(int portID, int tprocesses) throws IOException {
        this.totalProcesses = tprocesses;
        this.nodeID = processCount++;
        this.portID = portID;
        this.reciever = new Reciever(portID);
        this.metaData = new MetaData();
        this.portID = portID;
        this.reciever = new Reciever(portID);
        this.metaData = new MetaData();
        AllList = new Vector<AddressPort>();
        this.getOtherNodeData();
        //this.senderC = new SenderCont(AllList);
        this.senderC = new SenderS(AllList);

    }

    public void getOtherNodeData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Nodes.txt"));
        String line;
        int i = 0;

        while ((line = br.readLine()) != null) {
            String[] datas = line.split(" ");
            String Address = datas[0];
            int prtID = Integer.parseInt(datas[1]);
            if (prtID != this.portID) {
                //This is for running in same 
                AddressPort adp = new AddressPort(Address, prtID);
                AllList.add(adp);
            }
            i++;
            if (i >= totalProcesses) {
                break;

            }
            //br.close();

        }

        br.close();

    }

    public void recieve() throws IOException {
        //reciever = new Reciever(9999);
        reciever.recieve();
    }

    public void prepareMetaData() {
        this.metaData.recieveSummation = this.reciever.summation;
        this.metaData.recieveTracker = this.reciever.tracker;
        this.metaData.sendSummation = this.senderC.summation;
        this.metaData.sendTracker = this.senderC.tracker;
        
    }

}
