package co.id.app.servicebe.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindByIdRequest extends BaseRequest{
    private Long id;
    private String jobId;
}
