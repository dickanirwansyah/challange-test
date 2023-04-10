package co.id.app.servicebe.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse extends BaseResponse{
    @JsonProperty("valid_response")
    private boolean validResponse;
}
