
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class ParkingSlot {
    private int slotNumber;
    private boolean occupied;

    ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.occupied = false;
    }

    void parkVehicle() {
        if (occupied) {
            throw new IllegalStateException("Slot is occupied!");
        }
        occupied = true;
        System.out.println("Vehicle parked in slot " + slotNumber);
    }
}

public class Main1 {

    // CO3: Recursive method to calculate total vehicles
    static int totalVehicles(int[] vehicles, int n) {
        if (n == 0) {
            return 0;
        }
        return vehicles[n - 1] + totalVehicles(vehicles, n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // CO1: Parking Fee Calculation
        System.out.println("=== CO1: Parking Fee ===");

        System.out.print("Enter vehicle number: ");
        int vehicleNumber = sc.nextInt();

        System.out.print("Enter parking hours: ");
        int hours = sc.nextInt();

        double ratePerHour = 30.0;
        double totalFee = hours * ratePerHour;

        System.out.println("--- Parking Receipt ---");
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Parking Hours: " + hours);
        System.out.println("Rate Per Hour: Rs." + ratePerHour);
        System.out.println("Total Parking Fee: Rs." + totalFee);


        // CO2: Parking Slot Management
        System.out.println("\n=== CO2: Parking Manager ===");

        int floors = 3;
        int capacity = 5;
        int totalAvailable = 0;

        for (int i = 1; i <= floors; i++) {
            System.out.print("Available slots on floor " + i + ": ");
            int slots = sc.nextInt();

            if (slots >= 0 && slots <= capacity) {
                System.out.println("Floor " + i + " has "
                        + (slots > 0 ? "space." : "no available slots."));
                totalAvailable += slots;
            } else {
                System.out.println("Invalid slot count.");
            }
        }

        System.out.println("Total available slots: " + totalAvailable);


        // CO3: Recursive Vehicle Counting
        System.out.println("\n=== CO3: Vehicle Counting ===");

        int[] floorVehicles = {4, 3, 5};

        for (int i = 0; i < floorVehicles.length; i++) {
            System.out.println("Floor " + (i + 1)
                    + ": " + floorVehicles[i] + " vehicles");
        }

        System.out.println("Total vehicles: "
                + totalVehicles(floorVehicles, floorVehicles.length));


        // CO4: Exception Handling
        System.out.println("\n=== CO4: Parking Slot Exception ===");

        ParkingSlot slot = new ParkingSlot(101);

        try {
            slot.parkVehicle();
            slot.parkVehicle();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }


        // CO5: Sorting, Searching and File Handling
        System.out.println("\n=== CO5: Parking Records ===");

        int[] vehicles = {103, 101, 105, 102};
        Arrays.sort(vehicles);

        System.out.println("Sorted vehicle numbers:");
        System.out.println(Arrays.toString(vehicles));

        int search = 103;
        int result = Arrays.binarySearch(vehicles, search);

        if (result >= 0) {
            System.out.println("Vehicle " + search + " found.");
        } else {
            System.out.println("Vehicle not found.");
        }

        StringBuilder record = new StringBuilder();
        record.append("Parking Record\n");
        record.append(Arrays.toString(vehicles));

        try {
            FileWriter writer = new FileWriter("parking.txt");
            writer.write(record.toString());
            writer.close();

            BufferedReader reader =
                    new BufferedReader(new FileReader("parking.txt"));

            String line;


            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }


        // CO6: Parking Analytics using Collections and Streams
        System.out.println("\n=== CO6: Parking Analytics ===");

        ArrayList<Integer> vehicleList = new ArrayList<>();
        vehicleList.add(105);
        vehicleList.add(101);
        vehicleList.add(103);
        vehicleList.add(102);

        Collections.sort(vehicleList);

        System.out.println("All vehicles: " + vehicleList);
        System.out.println("Vehicles above 102:");

        vehicleList.stream()
                .filter(v -> v > 102)
                .forEach(System.out::println);

        long count = vehicleList.stream()
                .filter(v -> v > 102)
                .count();

        System.out.println("Matching vehicles: " + count);

        sc.close();
    }
}