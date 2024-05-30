public interface UserRepository {
    void addUser(User user);
    User authenticate(String username, String password);
    User findUserByUsername(String username);
}