public class User {
    private String fullName;
    private String username;
    private String password;
    private String role;
    private String batch;

    public User(String fullName, String username, String password, String role, String batch) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.batch = batch;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
//    abcd?
}