//816031055

import java.util.ArrayList;
import java.lang.Math;
import java.time.LocalDateTime;

public class LuggageManifest
{
    private ArrayList<LuggageSlip> slips;
    private String label;
    
    public LuggageManifest() {
        slips = new ArrayList<LuggageSlip>();
        
    }

    //helper methods
    public String addLuggage(Passenger p, Flight f) {
        boolean flag = true;
        
        if  (Integer.compare(p.getNumLuggage(), 0) == 0) {
           label = "No Luggage to add.";
           flag = false;
            
        } else {
            label = "$" + getExcessLuggageCost(p.getNumLuggage(), f.getAllowedLuggage(p.getCabinClass()));
        }
        
        for (int i = 1;i < p.getNumLuggage(); i++) {
            LuggageSlip luggageSlip = new LuggageSlip(p, f, label);
            slips.add(luggageSlip);
        }        
        
        if (!flag) {
            return ("PP NO. " + p.getPassportNumber() + " NAME: " + p.getFirstName().toUpperCase().charAt(0) + "." + p.getLastName().toUpperCase() + " NUMLUGGAGE: " + p.getNumLuggage() + " CLASS: " + p.getCabinClass() + "\n" + label + "\n");
        }
        
        return ("PP NO. " + p.getPassportNumber() + " NAME: " + p.getFirstName().toUpperCase().charAt(0) + "." + p.getLastName().toUpperCase() + " NUMLUGGAGE: " + p.getNumLuggage() + " CLASS: " + p.getCabinClass() + "\nPieces added (" + p.getNumLuggage() + "). Excess Cost: " + label + "\n");
        
    }
    
    private double getExcessLuggageCost(int numPieces, int numAllowedPieces) {
       int excess = numAllowedPieces - numPieces;
        
        if (excess < 0) {
            excess = Math.abs(excess);
            return excess * 35.00;
        }
        
        return 0.00;
        
    }
    
    private String getExcessLuggageCostByPassenger(String passportNumber) {
        int found = slips.indexOf(passportNumber);
        
        if (found != -1) {
            Passenger p = slips.get(found).getOwner();
            
            return (getExcessLuggageCost(p.getNumLuggage(), Flight.getAllowedLuggage(p.getCabinClass()))+""); //typecast to string
        
        }
        
        return "No Cost";
        
    }
    
    //accessors
    public String toString() {
        System.out.println("LUGGAGE MANIFEST:");
        
        for (int i = 0; i < slips.size(); i++) { //iterate over array list
            System.out.println(slips.get(i).toString());
        }
        
        return ""; //??
        
    }
    
    public ArrayList<LuggageSlip> getSlips() {
        return slips;
    }
    
}
