package co.id.app.servicebe.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestEsbRequest extends BaseRequest{

    private transient HttpEntity payload;
    private Boolean isPlain;
    private LinkedMultiValueMap<String, String> params;
}
