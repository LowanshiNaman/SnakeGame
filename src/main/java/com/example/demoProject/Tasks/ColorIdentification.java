package com.example.demoProject.Tasks;

import java.util.Scanner;

public class ColorIdentification {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get X and Y coordinates from the user
        int x = getCoordinate(scanner, "X");
        int y = getCoordinate(scanner, "Y");

        String color = getColorBasedOnCoordinates(x, y);
        System.out.println("The color based on the coordinates (" + x + ", " + y + ") is: " + color);

        scanner.close();
    }

    private static int getCoordinate(Scanner scanner, String axis) {
        System.out.print("Enter " + axis + " coordinate: ");
        return scanner.nextInt();
    }

    private static String getColorBasedOnCoordinates(int x, int y) {
        if (x >= 40 && x <= 100) {
            if (y >= 70 && y <= 90) return "Blue";
            else if (x >= 60 && x <= 80) {
                if (y >= 70 && y <= 90) return "Blue";
                if (y >= 90 && y <= 120) return "Green";
            }
            else if (x >= 80) {
                if (y >= 70 && y <= 90) return "Blue";
                if (y >= 90 && y <= 120) return "Green";
                if (y >= 120 && y <= 140) return "Yellow";
                }
            if (x >= 90 && y >= 140)
                if (y >= 70 && y <= 90) return "Blue";
                if (y >= 90 && y <= 120) return "Green";
                if (y >= 120 && y <= 140) return "Yellow";
            return "Red";
        }
        return "No color"; // If not matching the co-ordinates range, then no color will be output
    }

    /*
    Assumptions :
    1. if Co-ordinates are not in the range of graph given, We are simply returning a No Colour output.
    2. We could have restricted and provide a promt of input screen itself to user, that following can be the range of co-ordinates that can be given.
     */
}
