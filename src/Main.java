import devices.*;
import users.*;
import utils.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Authentication auth;
    private static User user;
    
    // Devices organized by room
    private static Light livingRoomLight = new Light("Living Room Light", 15.0);
    private static AC livingRoomAC = new AC("Living Room AC", 120.0);
    
    private static Light bedroomLight = new Light("Bedroom Light", 12.0);
    private static Fan bedroomFan = new Fan("Bedroom Fan", 40.0);
    private static AC bedroomAC = new AC("Bedroom AC", 100.0);
    
    private static Light kitchenLight = new Light("Kitchen Light", 18.0);
    private static Fan kitchenFan = new Fan("Kitchen Exhaust Fan", 30.0);

    private static SecuritySystem mainSecurity = new SecuritySystem("Main Security");

    public static void main(String[] args) {
        auth = new Authentication();
        
        // Login loop
        while (user == null) {
            user = auth.login();
        }

        // Main menu loop
        boolean running = true;
        while (running) {
            running = mainMenu();
        }

        scanner.close();
    }

    private static boolean mainMenu() {
        System.out.println("\n=== Smart Home Main Menu ===");
        System.out.println("1. Living Room");
        System.out.println("2. Bedroom");
        System.out.println("3. Kitchen");
        System.out.println("4. Security System");
        System.out.println("5. View All Device Statuses");
        if (user instanceof Admin) {
            System.out.println("6. Check Energy Consumption (Admin only)");
            System.out.println("7. User Management");
        }
        System.out.println("0. Logout");
        System.out.print("Choose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    livingRoomMenu();
                    break;
                case 2:
                    bedroomMenu();
                    break;
                case 3:
                    kitchenMenu();
                    break;
                case 4:
                    securityMenu();
                    break;
                case 5:
                    displayAllStatuses();
                    break;
                case 6:
                    if (user instanceof Admin) {
                        ((Admin)user).checkEnergyConsumption(
                            livingRoomLight, livingRoomAC,
                            bedroomLight, bedroomFan, bedroomAC,
                            kitchenLight, kitchenFan,
                            mainSecurity);
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;
                case 7:
                    if (user instanceof Admin) {
                        auth.userManagement();
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;
                case 0:
                    auth.logout();
                    return false;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
        return true;
    }

    private static void livingRoomMenu() {
        boolean inLivingRoom = true;
        while (inLivingRoom) {
            System.out.println("\n=== Living Room Controls ===");
            System.out.println("1. Control Light");
            System.out.println("2. Control AC");
            System.out.println("3. View Living Room Status");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        controlLight(livingRoomLight);
                        break;
                    case 2:
                        controlAC(livingRoomAC);
                        break;
                    case 3:
                        livingRoomLight.displayStatus();
                        livingRoomAC.displayStatus();
                        break;
                    case 0:
                        inLivingRoom = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private static void bedroomMenu() {
        boolean inBedroom = true;
        while (inBedroom) {
            System.out.println("\n=== Bedroom Controls ===");
            System.out.println("1. Control Light");
            System.out.println("2. Control Fan");
            System.out.println("3. Control AC");
            System.out.println("4. View Bedroom Status");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        controlLight(bedroomLight);
                        break;
                    case 2:
                        controlFan(bedroomFan);
                        break;
                    case 3:
                        controlAC(bedroomAC);
                        break;
                    case 4:
                        bedroomLight.displayStatus();
                        bedroomFan.displayStatus();
                        bedroomAC.displayStatus();
                        break;
                    case 0:
                        inBedroom = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private static void kitchenMenu() {
        boolean inKitchen = true;
        while (inKitchen) {
            System.out.println("\n=== Kitchen Controls ===");
            System.out.println("1. Control Light");
            System.out.println("2. Control Exhaust Fan");
            System.out.println("3. View Kitchen Status");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        controlLight(kitchenLight);
                        break;
                    case 2:
                        controlFan(kitchenFan);
                        break;
                    case 3:
                        kitchenLight.displayStatus();
                        kitchenFan.displayStatus();
                        break;
                    case 0:
                        inKitchen = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private static void securityMenu() {
        boolean inSecurity = true;
        while (inSecurity) {
            System.out.println("\n=== Security System Controls ===");
            System.out.println("1. Turn On Security");
            System.out.println("2. Turn Off Security");
            System.out.println("3. Activate Alarm");
            System.out.println("4. Deactivate Alarm");
            System.out.println("5. Simulate Motion Detection");
            System.out.println("6. View Security Status");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        mainSecurity.turnOn();
                        break;
                    case 2:
                        mainSecurity.turnOff();
                        break;
                    case 3:
                        mainSecurity.setAlarm(true);
                        break;
                    case 4:
                        mainSecurity.setAlarm(false);
                        break;
                    case 5:
                        mainSecurity.detectMotion();
                        // Automatically turn on lights when motion detected
                        livingRoomLight.turnOn();
                        livingRoomLight.setBrightness(100);
                        kitchenLight.turnOn();
                        kitchenLight.setBrightness(100);
                        System.out.println("All main lights turned on due to motion detection");
                        break;
                    case 6:
                        mainSecurity.displayStatus();
                        break;
                    case 0:
                        inSecurity = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private static void displayAllStatuses() {
        System.out.println("\n=== All Device Statuses ===");
        System.out.println("--- Living Room ---");
        livingRoomLight.displayStatus();
        livingRoomAC.displayStatus();
        
        System.out.println("\n--- Bedroom ---");
        bedroomLight.displayStatus();
        bedroomFan.displayStatus();
        bedroomAC.displayStatus();
        
        System.out.println("\n--- Kitchen ---");
        kitchenLight.displayStatus();
        kitchenFan.displayStatus();
        
        System.out.println("\n--- Security ---");
        mainSecurity.displayStatus();
    }

    // Device control methods remain the same as previous version
    private static void controlLight(Light light) {
        System.out.println("\n=== " + light.getName() + " Control ===");
        System.out.println("1. Turn On");
        System.out.println("2. Turn Off");
        System.out.println("3. Set Brightness");
        System.out.println("4. Check Status");
        System.out.print("Choose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    light.turnOn();
                    break;
                case 2:
                    light.turnOff();
                    break;
                case 3:
                    System.out.print("Enter brightness (0-100): ");
                    int brightness = Integer.parseInt(scanner.nextLine());
                    light.setBrightness(brightness);
                    break;
                case 4:
                    light.displayStatus();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    private static void controlFan(Fan fan) {
        System.out.println("\n=== " + fan.getName() + " Control ===");
        System.out.println("1. Turn On");
        System.out.println("2. Turn Off");
        System.out.println("3. Set Speed (0-3)");
        System.out.println("4. Check Status");
        System.out.print("Choose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    fan.turnOn();
                    break;
                case 2:
                    fan.turnOff();
                    break;
                case 3:
                    System.out.print("Enter speed (0-3): ");
                    int speed = Integer.parseInt(scanner.nextLine());
                    fan.setSpeed(speed);
                    break;
                case 4:
                    fan.displayStatus();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    private static void controlAC(AC ac) {
        System.out.println("\n=== " + ac.getName() + " Control ===");
        System.out.println("1. Turn On");
        System.out.println("2. Turn Off");
        System.out.println("3. Set Temperature");
        System.out.println("4. Set Mode");
        System.out.println("5. Check Status");
        System.out.print("Choose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ac.turnOn();
                    break;
                case 2:
                    ac.turnOff();
                    break;
                case 3:
                    System.out.print("Enter temperature: ");
                    int temp = Integer.parseInt(scanner.nextLine());
                    ac.setTemperature(temp);
                    break;
                case 4:
                    System.out.println("1. Cool");
                    System.out.println("2. Heat");
                    System.out.println("3. Fan");
                    System.out.print("Choose mode: ");
                    int modeChoice = Integer.parseInt(scanner.nextLine());
                    switch (modeChoice) {
                        case 1: ac.setMode("cool"); break;
                        case 2: ac.setMode("heat"); break;
                        case 3: ac.setMode("fan"); break;
                        default: System.out.println("Invalid mode choice!");
                    }
                    break;
                case 5:
                    ac.displayStatus();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
}