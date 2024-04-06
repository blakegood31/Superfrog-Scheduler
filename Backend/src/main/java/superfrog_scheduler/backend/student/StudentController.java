package superfrog_scheduler.backend.student;
import org.springframework.web.bind.annotation.*;
import superfrog_scheduler.backend.student.converter.StudentDtoToStudentConverter;
import superfrog_scheduler.backend.student.converter.StudentToStudentDtoConverter;
import superfrog_scheduler.backend.student.dto.StudentDto;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/students/{studentid}")
    public Result findStudentById(@PathVariable String studentid){
        Student foundStudent = this.studentService.findById(studentid);
        return new Result(true, StatusCode.SUCCESS, "Find One Success");
    }

    @PostMapping("/students")
    public Result addArtifact(@RequestBody StudentDto studentDto){
        // Convert artifactDto to artifact
        Student newStudent = this.studentDtoToStudentConverter.convert(studentDto);
        Student savedStudent = this.studentService.save(newStudent);
        StudentDto savedStudentDto = this.studentToStudentDtoConverter.convert(savedStudent);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedStudentDto);
    }

}
