package superfrog_scheduler.backend.request.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.request.dto.RequestDto;

@Component
public class RequestToRequestDtoConverter implements Converter<Request, RequestDto> {

    @Override
    public RequestDto convert(Request source){
        RequestDto request = new RequestDto(
                source.getId(),
                source.getAddress(),
                source.getDescription(),
                source.getEvent(),
                source.getStatus(),
                source.getSup_id(),
                source.getCust_id(),
                source.getInfo(),
                source.getOther_orgs()
        );
        return request;
    }
}
