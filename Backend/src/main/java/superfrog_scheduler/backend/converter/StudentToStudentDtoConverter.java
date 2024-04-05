package superfrog_scheduler.backend.converter;

import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.Student;
import superfrog_scheduler.backend.dto.StudentDto;
import org.springframework.core.convert.converter.Converter;

@Component
public class StudentToStudentDtoConverter implements Converter<Student, StudentDto> {
    @Override
    public StudentDto convert(Student source) {
        StudentDto studentDto = new StudentDto(source.getId(),
                source.getName(),
                source.getPerformance_rating());
        return studentDto;
    }
}
