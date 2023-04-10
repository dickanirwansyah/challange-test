package co.id.app.servicebe.service.job;

import co.id.app.servicebe.client.RecruitmentPositionClient;
import co.id.app.servicebe.model.request.EmptyRequest;
import co.id.app.servicebe.model.response.ListPositionJobResponse;
import co.id.app.servicebe.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobListService implements BaseService<EmptyRequest, ListPositionJobResponse> {

    @Autowired
    private RecruitmentPositionClient recruitmentPositionClient;

    @Override
    public ListPositionJobResponse execute(EmptyRequest request) {
        log.info("request get job list");
        return recruitmentPositionClient
                .execute(request);
    }
}
