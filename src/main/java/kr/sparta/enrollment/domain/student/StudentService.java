package kr.sparta.enrollment.domain.student;

import kr.sparta.enrollment.domain.student.exception.NotFoundException;
import kr.sparta.enrollment.domain.student.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(@PathVariable long studentNo) {
        return studentRepository.findById(studentNo)
                .orElseThrow(() -> new NotFoundException("No student found"));
    }

    public Student addStudent(StudentAddRequest req) {
        final Student newStudent = Student.builder()
                .name(req.getName())
                .status(req.getStatus())
                .build();

        return studentRepository.save(newStudent);
    }

    public List<SimpleStudentDto> getStudents() {
        final List<Student> students = studentRepository.findAll();
        final List<SimpleStudentDto> dto = new ArrayList<>(students.size());

        students.forEach(s ->
                dto.add(SimpleStudentDto.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .build())
        );

        return dto;
    }

    public void studentStatus(long studentNo, SimpleStudentStatusDto status) {
        Student student = getStudent(studentNo);
        student = Student.builder()
                .id(student.getId())
                .name(student.getName())
                .status(status.getStatus())
                .courseList(student.getCourseList())
                .build();
        studentRepository.save(student);
    }

    public void updateStudentInfo(long studentNo, StudentAddRequest studentDto) {
        Student student = getStudent(studentNo);
        student = Student.builder()
                .id(student.getId())
                .name(studentDto.getName())
                .status(studentDto.getStatus())
                .courseList(student.getCourseList())
                .build();

        studentRepository.save(student);
    }
}
