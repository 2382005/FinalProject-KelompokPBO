package dormapp.repositories;

import dormapp.config.Database;
import dormapp.entities.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackRepositoryDbImpl implements FeedbackRepository {
    private final Database database;

    public FeedbackRepositoryDbImpl(Database database) {
        this.database = database;
    }

    @Override
    public Feedback getById(int id) {
        String query = "SELECT * FROM feedback WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Feedback(rs.getInt("id"), rs.getInt("user_id"), rs.getString("comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Feedback> getAll() {
        List<Feedback> feedbacks = new ArrayList<>();
        String query = "SELECT * FROM feedback";
        try (Connection connection = database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                feedbacks.add(new Feedback(rs.getInt("id"), rs.getInt("user_id"), rs.getString("comment")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    @Override
    public void save(Feedback feedback) {
        String query = "INSERT INTO feedback (user_id, comment) VALUES (?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, feedback.getUserId());
            stmt.setString(2, feedback.getComment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Feedback feedback) {
        String query = "UPDATE feedback SET user_id = ?, comment = ? WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, feedback.getUserId());
            stmt.setString(2, feedback.getComment());
            stmt.setInt(3, feedback.getId());
            stmt.executeUpdate();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM feedback WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}