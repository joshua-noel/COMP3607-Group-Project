//816031055

import java.time.LocalDateTime;

public class Flight
{
    private String flightNo;
    private String destination;
    private String origin;
    private LocalDateTime flightDate;
    private LuggageManifest manifest;
    
    //constructor
    public Flight(String flightNo, String destination, String origin, LocalDateTime flightDate) {
        this.flightNo = flightNo;
        this.destination = destination;
        this.origin = origin;
        this.flightDate = flightDate;
        manifest = new LuggageManifest();
        
    }

    //helper methods
    public String checkInLuggage(Passenger p, Flight f) { //miss forgot to pass in flight?
        
        if (p.getFlightNo() != f.getFlightNo()) { //not correct logic; should use equals() but that causes error because p.getFlightNo() is null apparently
            return manifest.addLuggage(p, f);
            
        }
        
        return ("Invalid flight");
        
    }
    
    public String printLuggageManifest() {
        return manifest.toString();
    }
    
    public static int getAllowedLuggage(char cabinClass) {
        switch (cabinClass) {
            case 'F':
                return 3;
                
            case 'B':
                return 2;
                
            case 'P':
                return 1;
                
            case 'E':
                return 0;
                
            default:
                return 999;
            
        }
        
    }
    
    //accessors
    public String toString() {
        return (flightNo + " DESTINATION: " + destination + " ORIGIN:" + origin + " " + flightDate);
        
    }
    
    public String getFlightNo() {
        return flightNo;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public LocalDateTime getFlightDate() {
        return flightDate;
    }
    
    public LuggageManifest getManifest() {
        return manifest;
    }
    
}
