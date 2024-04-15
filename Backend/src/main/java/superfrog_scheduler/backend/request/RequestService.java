package superfrog_scheduler.backend.request;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.utils.IdWorker;
import superfrog_scheduler.backend.system.ObjectNotFoundException;
import superfrog_scheduler.backend.request.RequestRepository;
import superfrog_scheduler.backend.request.Request;

import java.util.List;

@Service
@Transactional
public class RequestService {
    private final RequestRepository requestRepository;
    private final IdWorker idWorker;

    //Constructor
    public RequestService(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
        this.idWorker = new IdWorker();
    }

    //Use cases
    public List<Request> findAll(){
        return this.requestRepository.findAll();
    }

    public Request findRequestById(String id){ // Use case 7
        return this.requestSFRepository.findRequestById(id)
                .orElseThrow(() -> new ObjectNotFoundException("request", id));
    }

}
