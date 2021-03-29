
package Node;


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
