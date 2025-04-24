package smarthome.utils;

import smarthome.devices.Device;
import java.io.*;
import java.util.Scanner;

public class FileManager {
    // Save device status to file
    public static void saveDeviceStatus(Device device) throws IOException {
        try (FileWriter fw = new FileWriter(device.getName() + ".txt")) {
            fw.write("Status: " + (device.isOn() ? "ON" : "OFF"));
        }
    }
    // Read device status from file
    public static void readDeviceStatus(String deviceName) throws IOException {
        try (Scanner sc = new Scanner(new File(deviceName + ".txt"))) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
    }
}
