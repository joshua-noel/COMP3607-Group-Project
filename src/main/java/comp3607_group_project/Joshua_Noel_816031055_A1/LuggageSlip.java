package comp3607_group_project.Joshua_Noel_816031055_A1;
//816031055

public class LuggageSlip
{
    private Passenger owner;
    private static int luggageSlipIDCounter = 1;
    private String luggageSlipID;
    private String label;
    
    //constructors
    public LuggageSlip(Passenger p, Flight f) {
        owner = p;
        luggageSlipID = f.getFlightNo() + "_" + owner.getLastName() + "_" + luggageSlipIDCounter;
        label = "";
        luggageSlipIDCounter++;
    }
    
    public LuggageSlip(Passenger p, Flight f, String label) {
        owner = p;
        luggageSlipID = f.getFlightNo() + "_" + owner.getLastName() + "_" + luggageSlipIDCounter;
        this.label = label;
        luggageSlipIDCounter++;
    }
    
    //helper methods
    private boolean hasOwner(String passportNumber) {
        if (owner.getPassportNumber().equals(passportNumber)) {
            return true;
        }
        
        return false;
    }
    
    //accessors
    public String toString() {
        return (luggageSlipID + " " + "PP NO. " + owner.getPassportNumber() + " NAME: " + owner.getFirstName().toUpperCase().charAt(0) + "." + owner.getLastName().toUpperCase() + " NUMLUGGAGE: " + owner.getNumLuggage() + " CLASS: " + owner.getCabinClass() + " " + label);
    }
    
    public Passenger getOwner() {
        return owner;
    }
    
    public int getLuggageSlipIDCounter() {
        return luggageSlipIDCounter;
    }
    
    public String getLuggageSlipID() {
        return luggageSlipID;
    }
    
    public String getLabel() {
        return label;
    }

}
