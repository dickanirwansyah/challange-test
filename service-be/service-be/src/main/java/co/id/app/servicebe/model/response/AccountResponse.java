package co.id.app.servicebe.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse extends BaseResponse{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("role_id")
    private Long roleCode;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("status")
    private String status;

    @JsonIgnore
    private String password;
    @JsonProperty("access_token")
    private String accessToken;
}
