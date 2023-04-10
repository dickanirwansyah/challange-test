package co.id.app.servicebe.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse {

    @JsonProperty("time_stamp")
    private Date timestamp;
    @JsonProperty("data")
    private Object data;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
}
