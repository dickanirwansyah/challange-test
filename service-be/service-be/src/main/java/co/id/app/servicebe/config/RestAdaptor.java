package co.id.app.servicebe.config;

import co.id.app.servicebe.model.request.BaseRequest;
import co.id.app.servicebe.model.request.RestEsbRequest;
import co.id.app.servicebe.model.response.BaseResponse;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@Slf4j
public abstract class RestAdaptor <T extends BaseRequest, V extends BaseResponse>{

    protected String uri;
    protected HttpMethod httpMethod;
    protected Class<V> response;
    protected RestTemplate restTemplate;
    protected Boolean enabledDebug = false;

    protected ResponseEntity getResponse(RestEsbRequest esbRequest){
        String requestUrl;
        if (!ObjectUtils.isEmpty(esbRequest.getParams()) && !esbRequest.getParams().isEmpty()){
            LinkedMultiValueMap<String, String> params = esbRequest.getParams();
            log.info("params = {} ",params);
            UriComponents buildUriComponent = UriComponentsBuilder.fromUriString(uri)
                    .queryParams(params)
                    .build();
            requestUrl = buildUriComponent.toString();
        }else{
            requestUrl = uri;
        }
        if (getEnabledDebug()){
            log.info("requestUrl = {} ",requestUrl);
            log.info("requestPayload = {}", JSON.toJSONString(esbRequest.getPayload()));
        }
        ResponseEntity responseEntity = (esbRequest.getIsPlain()) ?
                this.restTemplate.exchange(requestUrl, httpMethod, esbRequest.getPayload(), String.class) :
                this.restTemplate.exchange(requestUrl, httpMethod, esbRequest.getPayload(), response);

        if (getEnabledDebug()){
            log.info("response for {} = {} ", uri, responseEntity);
        }
        return responseEntity;
    }

    public abstract V execute(T request);
    protected abstract RestEsbRequest generatePayload(T request);
}
