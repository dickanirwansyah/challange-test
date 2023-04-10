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
public class RoleResponse extends BaseResponse{

    @JsonProperty("role_id")
    private Long roleId;
    @JsonProperty("role_name")
    private String roleName;

}
