package comp3607_group_project.Joshua_Noel_816031055_A1;
//816030155

import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class LuggageManagementSystem
{
    
    public static void main(String[] args) {
        //You are required to create Flights and passengers from file
        LocalDateTime d = LocalDateTime.of(2023, 1, 23, 10, 00, 00);
        Passenger p;
        Flight f;
        ArrayList<Flight> flights = new ArrayList<Flight>();
        java.util.Random random = new java.util.Random();
        
        //Load flights
        try {
            File file = new File("FlightList.txt");
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String lineData = scanner.nextLine();
                String[] lineDataSplit = lineData.split("\\s+"); //splits by space
                flights.add(new Flight(lineDataSplit[0], lineDataSplit[1], lineDataSplit[2], d));
            
            }
            
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace(); //more detailed error print out
            
        }
       
        //load passengers
        try {
            File file = new File("PassengerList.txt");
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                int flightLoc = random.nextInt(3);
                String lineData = scanner.nextLine();
                String[] lineDataSplit = lineData.split("\\s+"); //splits by space
                p = new Passenger(lineDataSplit[0], lineDataSplit[1], lineDataSplit[2], flights.get(flightLoc).getFlightNo());
                System.out.println(flights.get(flightLoc).checkInLuggage(p, flights.get(flightLoc)));
                
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace(); //more detailed error print out
        } 
        
        for (int i = 0; i < flights.size(); i++) {
            System.out.println("Flight No: " + flights.get(i).getFlightNo());
            System.out.println(flights.get(i).printLuggageManifest());
            
        }
       
    } 

}
