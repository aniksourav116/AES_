/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Node;

/**
 *
 * @author Anik Sourav
 */
public class MetaData {
    public int sendTracker;
    public int recieveTracker;
    public long sendSummation;
    public long recieveSummation;

    public MetaData() {
        this.sendTracker = 0;
        this.recieveTracker = 0;
        this.sendSummation = 0;
        this.recieveSummation = 0;
    }
    
    public void printMetadata()
    {
        System.out.println("Sent: " +sendTracker+" Received: "+recieveTracker);
        System.out.println("Sent summation: " +sendSummation+" Received Summation: "+recieveSummation);
    }
    public String metaDataToString()
    {
        String mdt = sendTracker + " " + recieveTracker + " " + sendSummation + " " + recieveSummation;
        return mdt;
    }
    
    
            
    
}
