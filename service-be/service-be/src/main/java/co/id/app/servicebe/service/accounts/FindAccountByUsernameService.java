package co.id.app.servicebe.service.accounts;

import co.id.app.servicebe.exception.UnauthorizeException;
import co.id.app.servicebe.model.request.LoginRequest;
import co.id.app.servicebe.model.response.AccountResponse;
import co.id.app.servicebe.repository.AccountRepository;
import co.id.app.servicebe.service.BaseService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FindAccountByUsernameService implements BaseService<LoginRequest, AccountResponse> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountResponse execute(LoginRequest request) {
        log.info("find account by username = {} ", JSON.toJSON(request));
        return accountRepository.getAccountByUsername(request.getUserName())
                .map(data -> AccountResponse.builder()
                        .id(data.getId())
                        .status(data.getStatus())
                        .fullName(data.getFullName())
                        .roleCode(data.getRoleId())
                        .userName(data.getUsername())
                        .password(data.getPassword())
                        .build())
                .orElseThrow(() -> new UnauthorizeException("wrong username & password"));
    }
}
