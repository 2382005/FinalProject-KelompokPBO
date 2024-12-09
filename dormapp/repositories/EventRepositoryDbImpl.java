package dormapp.repositories;

import dormapp.config.Database;
import dormapp.entities.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryDbImpl implements EventRepository {
    private final Database database;

    public EventRepositoryDbImpl(Database database) {
        this.database = database;
    }

    @Override
    public Event getById(int id) {
        String query = "SELECT * FROM events WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Event(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("event_date").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";
        try (Connection connection = database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                events.add(new Event(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("event_date").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public void save(Event event) {
        String query = "INSERT INTO events (name, event_date) VALUES (?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, event.getName());
            stmt.setTimestamp(2, Timestamp.valueOf(event.getEventDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        String query = "UPDATE events SET name = ?, event_date = ? WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, event.getName());
            stmt.setTimestamp(2, Timestamp.valueOf(event.getEventDate()));
            stmt.setInt(3, event.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM events WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}