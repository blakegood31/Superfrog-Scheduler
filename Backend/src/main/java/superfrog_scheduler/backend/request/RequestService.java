package superfrog_scheduler.backend.request;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.utils.IdWorker;

import java.util.List;

@Service
@Transactional
public class RequestService {
    private final RequestRepository requestRepository;
    private final IdWorker idWorker;

    public RequestService(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
        this.idWorker = new IdWorker();
    }

    public List<Request> findAll(){
        return this.requestRepository.findAll();
    }

    public Request findById(String requestId){
        return this.requestRepository.findById(requestId).orElseThrow();
    }

    public Request save(Request newRequest){
        newRequest.setId(idWorker.nextId() + "");
        return this.requestRepository.save(newRequest);
    }
}
