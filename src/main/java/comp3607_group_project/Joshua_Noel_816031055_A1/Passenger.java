package comp3607_group_project.Joshua_Noel_816031055_A1;
//816031055

import java.util.Random;

public class Passenger
{
    private String passportNumber;
    private String flightNo;
    private String firstName;
    private String lastName;
    private int numLuggage;
    private char cabinClass;
    private char[] potentialCabinClass = {'F', 'B', 'P', 'E'};
    private java.util.Random random = new java.util.Random();
    
    //constructors
    public Passenger(String passportNumber, String firstName, String lastName, String fightNo) {
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flightNo = flightNo;
        assignNumLuggage();
        assignRandomCabinClass();
    }
    
    //helper methods
    private void assignRandomCabinClass() {
        cabinClass = potentialCabinClass[random.nextInt(potentialCabinClass.length)]; //picks random array location
        
    }
    
    private void assignNumLuggage() {
        numLuggage = random.nextInt(4);
        
    }
    
    //accessors
    public String toString() {
        return ("PP NO. " + passportNumber + " NAME: " + firstName.toUpperCase().charAt(0) + "." + lastName.toUpperCase() + " NUMLUGGAGE: " + numLuggage + " CLASS: " + cabinClass);        
    }
    
    public String getPassportNumber () {
        return passportNumber;
    }
    
    public String getFlightNo() {
        return flightNo;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public int getNumLuggage() {
        return numLuggage;
    }
    
    public char getCabinClass() {
        return cabinClass;
    }
    
}
