package smarthome;

import smarthome.core.*;
import smarthome.devices.*;
import smarthome.users.*;
import smarthome.utils.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SmartHomeSystem system = new SmartHomeSystem();
        Light livingRoomLight = new Light("LivingRoomLight", 10, 75);
        Fan bedroomFan = new Fan("BedroomFan", 15, 3);

        system.addDevice(livingRoomLight);
        system.addDevice(bedroomFan);

        User admin = new User("admin", "Admin", "admin123");
        system.addUser(admin);

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Smart Home Automation System");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        boolean authenticated = false;
        for (User user : system.users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                authenticated = true;
                break;
            }
        }

        if (!authenticated) {
            System.out.println("Authentication failed!");
            return;
        }

        System.out.println("Login successful!\nAvailable devices:");
        for (int i = 0; i < system.getDeviceCount(); i++) {
            System.out.println("- " + system.devices[i].getName());
        }



        System.out.print("Enter device name to control: ");
        String deviceName = sc.nextLine();
        System.out.print("Enter action (on/off): ");
        String action = sc.nextLine();

        try {
            system.controlDevice(username, deviceName, action);
            Device device = system.getDeviceByName(deviceName);
            device.displayStatus(true);
            FileManager.readDeviceStatus(deviceName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
