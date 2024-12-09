package dormapp.service;

import dormapp.entities.Attendance;

import java.util.List;

public interface AttendanceService {
    Attendance getAttendanceById(int id);
    List<Attendance> getAllAttendances();
    void registerAttendance(Attendance attendance);
    void updateAttendance(Attendance attendance);
    void deleteAttendance(int id);
}