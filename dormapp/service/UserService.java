package dormapp.service;

import dormapp.entities.User;

import java.util.List;

public interface UserService {
    User authenticate(String username, String password);
    List<User> getAllUsers();
    void register(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User getUserByUsername(String username);
}
