package co.id.app.servicebe.client;

import co.id.app.servicebe.config.RestAdaptor;
import co.id.app.servicebe.exception.InvalidResourceException;
import co.id.app.servicebe.model.request.EmptyRequest;
import co.id.app.servicebe.model.request.RestEsbRequest;
import co.id.app.servicebe.model.response.ListPositionJobResponse;
import co.id.app.servicebe.model.response.PositionJobResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class RecruitmentPositionClient extends RestAdaptor<EmptyRequest, ListPositionJobResponse> {

    @Value("${uri.surrounding.recruitment-position}")
    private String uriSurrounding;
    @Autowired
    private RestTemplate restClientBasic;

    @Override
    public ListPositionJobResponse execute(EmptyRequest request) {
        log.info("uri notification = {}",uriSurrounding);
        try{
            this.setUri(uriSurrounding);
            this.setHttpMethod(HttpMethod.GET);
            this.setRestTemplate(restClientBasic);
            ResponseEntity<String> response = super.getResponse(generatePayload(request));
            log.info("response notification = {} ", JSON.toJSONString(response));
            /** parsing data **/
            List<PositionJobResponse> dataPosition = JSONArray.parseArray(response.getBody(), PositionJobResponse.class);
            return ListPositionJobResponse.builder()
                    .listPositions(dataPosition)
                    .build();
        }catch (HttpClientErrorException | HttpServerErrorException hhe){
            log.error("RecruitmentPositionClient.error hee = {} - {} ", hhe.getRawStatusCode(), hhe.getResponseBodyAsString());
            throw new InvalidResourceException("something went wrong send request");
        }catch (ResourceAccessException re){
            log.error("RecruitmentPositionClient.error re = {} {} ",re.getMessage());
            throw new InvalidResourceException("something went wrong send request");
        }
    }

    @Override
    protected RestEsbRequest generatePayload(EmptyRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        /**
        httpHeaders.set("apiKey", apiKeyNotification); opsional if needed
        httpHeaders.set("Authorization", ""); opsional if needed
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
         **/
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return RestEsbRequest.builder()
                .payload(new HttpEntity<>("", httpHeaders))
                .isPlain(true)
                .build();
    }
}
