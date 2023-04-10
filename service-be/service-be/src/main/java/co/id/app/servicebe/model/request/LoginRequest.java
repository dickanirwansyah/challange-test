package co.id.app.servicebe.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest extends BaseRequest{

    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("password")
    private String password;
}
