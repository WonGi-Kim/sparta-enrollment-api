package kr.sparta.enrollment.domain.student;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import kr.sparta.enrollment.domain.student.model.SimpleStudentDto;
import kr.sparta.enrollment.domain.student.model.SimpleStudentStatusDto;
import kr.sparta.enrollment.domain.student.model.Student;
import kr.sparta.enrollment.domain.student.model.StudentAddRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{studentNo}")
    @ApiResponse(responseCode = "204", description = "수강생을 찾을 수 없음")
    public Student getStudent(@PathVariable long studentNo) {
        return studentService.getStudent(studentNo);
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody @Valid StudentAddRequest req) {
        studentService.addStudent(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("students/{studentNo}/status")
    public ResponseEntity<?> updateStudentStatus(@PathVariable long studentNo, @RequestBody SimpleStudentStatusDto status) {
        studentService.studentStatus(studentNo,status);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/v1/students/{studentNo}")
    public ResponseEntity<?> updateStudentInfo(@PathVariable long studentNo, @RequestBody StudentAddRequest studentDto) {
        studentService.updateStudentInfo(studentNo, studentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/status/{status}/students")
    public ResponseEntity<?> listStudentStatus(@PathVariable String status) {

        return ResponseEntity.ok().body(studentService.getStudentsOnStatus(status));
    }

    @GetMapping("/students")
    public List<SimpleStudentDto> getStudents() {
        return studentService.getStudents();
    }
}
