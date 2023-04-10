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
public class AccountRequest extends BaseRequest{

    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("role_id")
    private Long roleId;
    @JsonProperty("password")
    private String password;
    @JsonProperty("confirm_password")
    private String confirmPassword;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("status")
    private String status;
}
