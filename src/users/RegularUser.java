package users;

import devices.Device;
import devices.Light;
import devices.Fan;
import devices.AC;
import devices.SecuritySystem;

public class RegularUser extends User {
    public RegularUser(String username) {
        super(username);
    }

    @Override
    public void controlDevice(Device device, String command) {
        switch (command.toLowerCase()) {
            case "on":
                device.turnOn();
                break;
            case "off":
                device.turnOff();
                break;
            case "status":
                device.displayStatus();
                break;
            default:
                // Handle device-specific commands
                if (device instanceof Light) {
                    Light light = (Light)device;
                    try {
                        int brightness = Integer.parseInt(command);
                        if (brightness >= 0 && brightness <= 100) {
                            light.setBrightness(brightness);
                        } else {
                            System.out.println("Brightness must be between 0-100");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid command for light");
                    }
                } 
                else if (device instanceof Fan) {
                    Fan fan = (Fan)device;
                    try {
                        int speed = Integer.parseInt(command);
                        if (speed >= 0 && speed <= 3) {
                            fan.setSpeed(speed);
                        } else {
                            System.out.println("Speed must be between 0-3");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid command for fan");
                    }
                }
                else if (device instanceof AC) {
                    AC ac = (AC)device;
                    if (command.equalsIgnoreCase("cool") || 
                        command.equalsIgnoreCase("heat") || 
                        command.equalsIgnoreCase("fan")) {
                        ac.setMode(command);
                    } else {
                        try {
                            int temp = Integer.parseInt(command);
                            ac.setTemperature(temp);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid command for AC");
                        }
                    }
                }
                else if (device instanceof SecuritySystem) {
                    SecuritySystem security = (SecuritySystem)device;
                    if (command.equalsIgnoreCase("alarm on")) {
                        security.setAlarm(true);
                    } else if (command.equalsIgnoreCase("alarm off")) {
                        security.setAlarm(false);
                    } else if (command.equalsIgnoreCase("motion")) {
                        security.detectMotion();
                    } else {
                        System.out.println("Invalid command for security system");
                    }
                }
        }
    }
}