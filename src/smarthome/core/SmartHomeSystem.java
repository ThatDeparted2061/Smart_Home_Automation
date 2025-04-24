package smarthome.core;

import smarthome.devices.*;
import smarthome.users.*;
import smarthome.utils.FileManager;
import java.io.IOException;

public class SmartHomeSystem {
    // Maximum number of devices and users
    private static final int MAX_DEVICES = 10;
    private static final int MAX_USERS = 10;

    public Device[] devices = new Device[MAX_DEVICES];
    public User[] users = new User[MAX_USERS];
    private int deviceCount = 0;
    private int userCount = 0;

    // Add a device to the system
    public void addDevice(Device device) {
        if (deviceCount < MAX_DEVICES) {
            devices[deviceCount++] = device;
        } else {
            System.out.println("Device limit reached!");
        }
    }

    // Add a user to the system
    public void addUser(User user) {
        if (userCount < MAX_USERS) {
            users[userCount++] = user;
        } else {
            System.out.println("User limit reached!");
        }
    }

    // Find a device by name
    public Device getDeviceByName(String name) throws DeviceNotFoundException {
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i].getName().equalsIgnoreCase(name)) {
                return devices[i];
            }
        }
        throw new DeviceNotFoundException("Device not found: " + name);
    }

    // Control a device (turn on/off)
    public void controlDevice(String username, String deviceName, String action)
            throws Exception {
        User user = null;
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username)) {
                user = users[i];
                break;
            }
        }
        if (user == null) throw new UnauthorizedAccessException("User not found!");
        Device device = getDeviceByName(deviceName);
        if ("on".equalsIgnoreCase(action)) device.turnOn();
        else if ("off".equalsIgnoreCase(action)) device.turnOff();
        FileManager.saveDeviceStatus(device);
    }

    public int getDeviceCount() {
        return deviceCount;
    }
}
