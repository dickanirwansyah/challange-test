package co.id.app.servicebe.service.job;

import co.id.app.servicebe.client.RecruitmentPositionByIdClient;
import co.id.app.servicebe.model.request.FindByIdRequest;
import co.id.app.servicebe.model.response.PositionJobResponse;
import co.id.app.servicebe.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobByIdService implements BaseService<FindByIdRequest, PositionJobResponse> {

    @Autowired
    private RecruitmentPositionByIdClient recruitmentPositionByIdClient;

    @Override
    public PositionJobResponse execute(FindByIdRequest request) {

        return recruitmentPositionByIdClient
                .execute(request);
    }
}
