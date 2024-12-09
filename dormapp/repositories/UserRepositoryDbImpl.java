package dormapp.repositories;

import dormapp.config.Database;
import dormapp.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryDbImpl implements UserRepository {
    private final Database database;

    // Constructor to initialize the Database
    public UserRepositoryDbImpl(Database database) {
        this.database = database; // Use the provided Database instance
    }

    @Override
    public User getById(int id) {
        String query = "SELECT * FROM users WHERE id = ? ";
        try (Connection connection = database.getConnection(); // Use the connection from the database
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("is_admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? ";
        try (Connection connection = database.getConnection(); // Use the connection from the database
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("is_admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection connection = database.getConnection(); // Use the connection from the database
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("is_admin")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) {
        String query = "INSERT INTO users (username, password, is_admin) VALUES (?, ?, ? )";
        try (Connection connection = database.getConnection(); // Use the connection from the database
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setBoolean(3, user.isAdmin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String query = "UPDATE users SET username = ?, password = ?, is_admin = ? WHERE id = ? ";
        try (Connection connection = database.getConnection(); // Use the connection from the database
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setBoolean(3, user.isAdmin());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM users WHERE id = ? ";
        try (Connection connection = database.getConnection(); // Use the connection from the database
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}