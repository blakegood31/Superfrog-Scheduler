package superfrog_scheduler.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superfrog_scheduler.backend.converter.StudentDtoToStudentConverter;
import superfrog_scheduler.backend.converter.StudentToStudentDtoConverter;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final StudentToStudentDtoConverter studentToStudentDtoConverter;
    private final StudentDtoToStudentConverter studentDtoToStudentConverter;

    public StudentController(StudentService studentService, StudentToStudentDtoConverter studentToStudentDtoConverter, StudentDtoToStudentConverter studentDtoToStudentConverter) {
        this.studentService = studentService;
        this.studentToStudentDtoConverter = studentToStudentDtoConverter;
        this.studentDtoToStudentConverter = studentDtoToStudentConverter;
    }
    @GetMapping("/students")
    public Result findAllStudents(){
        List<Student> studentDtos = this.studentService.findAll();
        studentDtos.stream()
                .map(this.studentToStudentDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all success", studentDtos);
    }
}
