package dormapp.service;

import dormapp.entities.Attendance;
import dormapp.repositories.AttendanceRepository;

import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Attendance getAttendanceById(int id) {
        return attendanceRepository.getById(id);
    }

    @Override
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.getAll();
    }

    @Override
    public void registerAttendance(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    @Override
    public void updateAttendance(Attendance attendance) {
        attendanceRepository.update(attendance);
    }

    @Override
    public void deleteAttendance(int id) {
        attendanceRepository.delete(id);
    }
}