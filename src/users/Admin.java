package users;

import devices.Device;

public class Admin extends RegularUser {
    public Admin(String username) {
        super(username);
    }

    public void checkEnergyConsumption(Device... devices) {
        double total = 0;
        for (Device device : devices) {
            total += device.getPowerConsumption();
        }
        System.out.println("Total energy consumption: " + total + "W");
    }
}