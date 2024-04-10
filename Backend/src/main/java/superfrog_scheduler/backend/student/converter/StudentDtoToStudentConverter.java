package superfrog_scheduler.backend.student.converter;

import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.dto.StudentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoToStudentConverter implements Converter<StudentDto, Student> {

    @Override
    public Student convert(StudentDto source) {
        Student student = new Student();
        student.setId(source.id());
        student.setName(source.name());
        student.setPerformance_rating(source.performance_rating());
        return student;
    }
}
