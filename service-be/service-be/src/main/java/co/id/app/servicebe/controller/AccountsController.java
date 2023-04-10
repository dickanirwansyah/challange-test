package co.id.app.servicebe.controller;

import co.id.app.servicebe.constant.ConstantValue;
import co.id.app.servicebe.model.request.AccountRequest;
import co.id.app.servicebe.model.request.LoginRequest;
import co.id.app.servicebe.model.response.ServerResponse;
import co.id.app.servicebe.service.accounts.LoginService;
import co.id.app.servicebe.service.accounts.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountsController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/register")
    public ResponseEntity<ServerResponse> register(@RequestBody AccountRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ServerResponse.builder()
                        .message(ConstantValue.MESSAGE_SUCCESS)
                        .timestamp(new Date())
                        .status(HttpStatus.CREATED.value())
                        .data(registerService.execute(request))
                        .build());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ServerResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ServerResponse.builder()
                        .message(ConstantValue.MESSAGE_SUCCESS)
                        .timestamp(new Date())
                        .status(HttpStatus.OK.value())
                        .data(loginService.execute(request))
                        .build());
    }
}
