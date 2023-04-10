package co.id.app.servicebe.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListPositionJobResponse extends BaseResponse{
    @JsonProperty("list_positions")
    private List<PositionJobResponse> listPositions;
}
