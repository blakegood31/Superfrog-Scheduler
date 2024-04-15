package superfrog_scheduler.backend.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.utils.IdWorker;

import java.util.List;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;

    private final IdWorker idWorker;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.idWorker = new IdWorker();
    }

    public List<Student> findAll(){
        return this.studentRepository.findAll();
    }

    public Student findById(String studentid){
        return this.studentRepository.findById(studentid).orElseThrow();
    }

    public Student save(Student newStudent){
        newStudent.setId(idWorker.nextId() + "");
        return this.studentRepository.save(newStudent);
    }

}
