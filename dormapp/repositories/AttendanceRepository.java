package dormapp.repositories;

import dormapp.entities.Attendance;

import java.util.List;

public interface AttendanceRepository {
    Attendance getById(int id);
    List<Attendance> getAll();
    void save(Attendance attendance);
    void update(Attendance attendance);
    void delete(int id);
}