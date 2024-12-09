package dormapp.repositories;

import dormapp.config.Database;
import dormapp.entities.Attendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepositoryDbImpl implements AttendanceRepository {
    private final Database database;

    public AttendanceRepositoryDbImpl(Database database) {
        this.database = database;
    }

    @Override
    public Attendance getById(int id) {
        String query = "SELECT * FROM attendance WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Attendance(rs.getInt("id"), rs.getInt("user_id"), rs.getTimestamp("timestamp").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Attendance> getAll() {
        List<Attendance> attendances = new ArrayList<>();
        String query = "SELECT * FROM attendance";
        try (Connection connection = database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                attendances.add(new Attendance(rs.getInt("id"), rs.getInt("user_id"), rs.getTimestamp("timestamp").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    @Override
    public void save(Attendance attendance) {
        String query = "INSERT INTO attendance (user_id, timestamp) VALUES (?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, attendance.getUserId());
            stmt.setTimestamp(2, Timestamp.valueOf(attendance.getTimestamp()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Attendance attendance) {
        String query = "UPDATE attendance SET user_id = ?, timestamp = ? WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, attendance.getUserId());
            stmt.setTimestamp(2, Timestamp.valueOf(attendance.getTimestamp()));
            stmt .setInt(3, attendance.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM attendance WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}