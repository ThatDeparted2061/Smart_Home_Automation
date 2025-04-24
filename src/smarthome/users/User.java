package smarthome.users;

public class User implements Authentication {
    private String username;
    private String role; // "Admin" or "User"
    private String password;

    public User(String username, String role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }
    @Override
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
}
