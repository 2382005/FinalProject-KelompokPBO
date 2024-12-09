package dormapp.service;

import dormapp.config.Database;
import dormapp.entities.User;
import dormapp.repositories.UserRepository;
import dormapp.repositories.UserRepositoryDbImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    private Database database;
    private UserRepository userRepository = new UserRepositoryDbImpl(database);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public void register(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.update(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}
