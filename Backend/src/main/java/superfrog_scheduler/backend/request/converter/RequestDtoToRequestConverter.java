package superfrog_scheduler.backend.request.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.request.dto.RequestDto;

@Component
public class RequestDtoToRequestConverter implements Converter<RequestDto, Request> {

    @Override
    public Request convert(RequestDto source){
        Request request = new Request();
        request.setId(source.id());
        request.setAddress(source.address());
        request.setDescription(source.description());
        request.setEvent(source.event());
        request.setStatus(source.status());
        request.setSup_id(source.sup_id());
        request.setCust_id(source.cust_id());
        request.setInfo(source.info());
        request.setOther_orgs(source.other_orgs());
        return request;
    }
}
