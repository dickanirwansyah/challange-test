package co.id.app.servicebe.service;

import co.id.app.servicebe.model.request.BaseRequest;
import co.id.app.servicebe.model.response.BaseResponse;

public interface BaseService <T extends BaseRequest, V extends BaseResponse>{
    V execute(T request);
}
