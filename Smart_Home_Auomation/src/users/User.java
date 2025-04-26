package users;

import devices.Device;

// Abstract class for User
public abstract class User {
    protected String username;
    protected String password;

    // Constructor 1
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Constructor 2 (overloaded)
    public User(String username) {
        this.username = username;
        this.password = "default123";
    }

    public abstract void controlDevice(Device device, String command);

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}