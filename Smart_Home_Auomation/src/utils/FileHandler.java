package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Handles file operations
public class FileHandler {
    public static void saveDeviceStatus(String filename, String status) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            writer.write(status + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public static void readDeviceStatus(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("No status history found");
                return;
            }

            Scanner reader = new Scanner(file);
            System.out.println("\n=== Device Status History ===");
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}