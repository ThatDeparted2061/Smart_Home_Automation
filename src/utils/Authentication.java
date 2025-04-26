package utils;

import users.User;
import users.Admin;
import users.RegularUser;
import java.util.Scanner;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authentication {
    private User currentUser;
    private Scanner scanner;
    private static final String USER_FILE = "users.txt";

    public Authentication() {
        this.scanner = new Scanner(System.in);
    }

    // Basic SHA-256 hashing
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            
            // Convert byte array to hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error hashing password: " + e.getMessage());
            return null;
        }
    }

    public User login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        
        // Basic password input (no echo disable for simplicity)
        String password = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(username)) {
                    String hashedInput = hashPassword(password);
                    if (hashedInput != null && hashedInput.equals(parts[1])) {
                        boolean isAdmin = Boolean.parseBoolean(parts[2]);
                        currentUser = isAdmin ? new Admin(username) : new RegularUser(username);
                        System.out.println("Login successful! Welcome " + username);
                        return currentUser;
                    }
                }
            }
        } catch (IOException e) {
            // If file doesn't exist, create default users
            if (e instanceof FileNotFoundException) {
                createDefaultUsers();
                return login(); // Try again after creating default users
            }
            System.err.println("Error reading users: " + e.getMessage());
        }
        
        System.out.println("Invalid credentials!");
        return null;
    }

    private void createDefaultUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            // Create default admin (username: admin, password: admin123)
            writer.println("admin," + hashPassword("admin123") + ",true");
            
            // Create default regular user (username: user, password: user123)
            writer.println("user," + hashPassword("user123") + ",false");
            
            System.out.println("Created default user accounts");
        } catch (IOException e) {
            System.err.println("Error creating default users: " + e.getMessage());
        }
    }

    public void userManagement() {
        if (!(currentUser instanceof Admin)) {
            System.out.println("Admin privilege required!");
            return;
        }

        System.out.println("\n=== User Management ===");
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. List Users");
        System.out.println("4. Back to Main Menu");
        System.out.print("Choose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    removeUser();
                    break;
                case 3:
                    listUsers();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    private void addUser() {
        System.out.print("Enter new username: ");
        String newUser = scanner.nextLine();
        System.out.print("Enter password: ");
        String newPass = scanner.nextLine();
        System.out.print("Is admin? (1 for yes, 0 for no): ");
        boolean isAdmin = scanner.nextLine().equals("1");

        String hashedPassword = hashPassword(newPass);
        if (hashedPassword == null) {
            System.out.println("Error creating user - password hashing failed");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE, true))) {
            writer.println(newUser + "," + hashedPassword + "," + isAdmin);
            System.out.println("User added successfully!");
        } catch (IOException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    private void removeUser() {
        System.out.print("Enter username to remove: ");
        String userToRemove = scanner.nextLine();

        if (userToRemove.equals("admin")) {
            System.out.println("Cannot remove default admin user!");
            return;
        }

        try {
            File inputFile = new File(USER_FILE);
            File tempFile = new File("temp_users.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(userToRemove)) {
                    found = true;
                    continue; // Skip writing this line
                }
                writer.println(line);
            }

            writer.close();
            reader.close();

            if (found) {
                if (inputFile.delete()) {
                    tempFile.renameTo(inputFile);
                    System.out.println("User removed successfully!");
                } else {
                    System.out.println("Error updating user file");
                }
            } else {
                tempFile.delete();
                System.out.println("User not found!");
            }
        } catch (IOException e) {
            System.err.println("Error removing user: " + e.getMessage());
        }
    }

    private void listUsers() {
        System.out.println("\n=== User List ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    System.out.println(parts[0] + " (" + (Boolean.parseBoolean(parts[2]) ? "Admin" : "Regular") + ")");
                }
            }
        } catch (IOException e) {
            System.err.println("Error listing users: " + e.getMessage());
        }
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("Logging out " + currentUser.getUsername());
            currentUser = null;
        }
    }
}