/**
 * @author Tamer Zreg
 * @version 1.0
 * @since April 11, 2024
 * Description: This program reads student and assignments data from files, generates marks randomly for each student,
 * and writes the marks into a CSV file.
 */

package com.example;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TwoDArray {

    /**
     * Main method of the program.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // File names for student and assignments data
        String studentsFile = "students.txt";
        String assignmentsFile = "assignments.txt";
        
        // Read student and assignments data from files
        String[][] studentArray = readDataFromFile(studentsFile);
        String[] assignmentsArray = readAssignmentsFromFile(assignmentsFile);
        
        // If data reading is successful, generate marks and write them to a CSV file
        if (studentArray != null && assignmentsArray != null) {
            String[][] marks = generateMarks(studentArray, assignmentsArray);
            if (marks != null) {
                writeToCSV(marks, "marks.csv");
            } else {
                System.err.println("Failed to generate marks.");
            }
        } else {
            System.err.println("Failed to read student or assignments data.");
        }
    }

    /**
     * Reads student data from a file and returns it as a 2D array.
     * 
     * @param filename The name of the file containing student data.
     * @return A 2D array containing student data.
     */
    public static String[][] readDataFromFile(String filename) {
        try {
            // Open the file
            Scanner scanner = new Scanner(new File(filename));
            // Create a list to hold student data
            ArrayList<String[]> dataList = new ArrayList<>();
            // Read each line of the file
            while (scanner.hasNextLine()) {
                // Split the line by comma to get individual student data
                String line = scanner.nextLine();
                String[] row = line.split(",");
                // Add the student data to the list
                dataList.add(row);
            }
            // Close the scanner
            scanner.close();
            // Convert the list to a 2D array
            String[][] data = new String[dataList.size()][];
            dataList.toArray(data);
            // Return the 2D array containing student data
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads assignments from a file and returns them as an array.
     * 
     * @param filename The name of the file containing assignments.
     * @return An array containing assignments.
     */
    public static String[] readAssignmentsFromFile(String filename) {
        try {
            // Open the file
            Scanner scanner = new Scanner(new File(filename));
            // Check if there are assignments in the file
            if (scanner.hasNextLine()) {
                // If assignments are found, read them
                String line = scanner.nextLine();
                String[] assignments = line.split(",");
                // Close the scanner
                scanner.close();
                // Return the array containing assignments
                return assignments;
            } else {
                // If no assignments are found, print an error message
                System.err.println("No assignments found in the file: " + filename);
                // Close the scanner
                scanner.close();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generates marks randomly for each student based on a Gaussian distribution.
     * 
     * @param students An array containing student data.
     * @param assignments An array containing assignments.
     * @return A 2D array containing marks for each student.
     */
    public static String[][] generateMarks(String[][] students, String[] assignments) {
        // Create a random number generator
        Random random = new Random();
        // Create a 2D array to store marks
        String[][] marks = new String[students.length][assignments.length + 1];
        // Set the header row of the marks array
        marks[0][0] = "Student";
        for (int i = 1; i < marks[0].length; i++) {
            marks[0][i] = assignments[i - 1];
        }
        // Generate marks for each student
        for (int i = 1; i < marks.length; i++) {
            marks[i][0] = students[i - 1][0];
            for (int j = 1; j < marks[i].length; j++) {
                // Generate a mark based on a Gaussian distribution
                int mark = (int) (random.nextGaussian() * 10 + 75);
                marks[i][j] = Integer.toString(mark);
            }
        }
        // Return the 2D array containing marks
        return marks;
    }

    /**
     * Writes data to a CSV file.
     * 
     * @param data A 2D array containing the data to be written.
     * @param filename The name of the CSV file to write to.
     */
    public static void writeToCSV(String[][] data, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write each row of data to the CSV file
            for (String[] row : data) {
                writer.write(String.join(",", row) + "\n");
            }
            // Print a success message
            System.out.println("CSV file has been created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            // Print an error message if writing fails
            System.err.println("Failed to write to CSV file.");
        }
    }
}
