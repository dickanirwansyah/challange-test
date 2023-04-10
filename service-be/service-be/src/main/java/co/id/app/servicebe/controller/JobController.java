package co.id.app.servicebe.controller;

import co.id.app.servicebe.constant.ConstantValue;
import co.id.app.servicebe.model.request.EmptyRequest;
import co.id.app.servicebe.model.response.ServerResponse;
import co.id.app.servicebe.service.job.JobListService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = "Job Controller")
@RestController
@RequestMapping(value = "/api/v1/job")
public class JobController {

    @Autowired
    private JobListService jobListService;

    @GetMapping(value = "/list")
    public ResponseEntity<ServerResponse> jobList(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ServerResponse.builder()
                        .message(ConstantValue.MESSAGE_SUCCESS)
                        .timestamp(new Date())
                        .status(HttpStatus.OK.value())
                        .data(jobListService.execute(new EmptyRequest()))
                        .build());
    }
}
