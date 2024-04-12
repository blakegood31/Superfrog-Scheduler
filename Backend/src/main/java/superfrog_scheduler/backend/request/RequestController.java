package superfrog_scheduler.backend.request;

import org.springframework.web.bind.annotation.*;
import superfrog_scheduler.backend.request.converter.RequestDtoToRequestConverter;
import superfrog_scheduler.backend.request.converter.RequestToRequestDtoConverter;
import superfrog_scheduler.backend.request.dto.RequestDto;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.dto.StudentDto;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RequestController {

    private final RequestService requestService;
    private final RequestToRequestDtoConverter requestToRequestDtoConverter;
    private final RequestDtoToRequestConverter requestDtoToRequestConverter;

    public RequestController(RequestService requestService, RequestToRequestDtoConverter requestToRequestDtoConverter, RequestDtoToRequestConverter requestDtoToRequestConverter){
        this.requestService = requestService;
        this.requestToRequestDtoConverter = requestToRequestDtoConverter;
        this.requestDtoToRequestConverter = requestDtoToRequestConverter;
    }

    @GetMapping("/requests")
    public Result findAllRequests(){
        List<Request> requestDtos = this.requestService.findAll();
        requestDtos.stream()
                .map(this.requestToRequestDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all success", requestDtos);
    }

    @GetMapping("/requests/{requestId}")
    public Result findStudentById(@PathVariable String requestId){
        Request foundRequest = this.requestService.findById(requestId);
        return new Result(true, StatusCode.SUCCESS, "Find One Success");
    }

    @PostMapping("/request")
    public Result addArtifact(@RequestBody RequestDto requestDto){
        // Convert artifactDto to artifact
        Request newRequest = this.requestDtoToRequestConverter.convert(requestDto);
        Request savedRequest = this.requestService.save(newRequest);
        RequestDto savedRequestDto = this.requestToRequestDtoConverter.convert(savedRequest);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedRequestDto);
    }
}
