package dormapp.repositories;

import dormapp.entities.User;

import java.util.List;

public interface UserRepository {
    User getById(int id);
    User getByUsername(String username);
    List<User> getAll();
    void save(User user);
    void update(User user);
    void delete(int id);
}
