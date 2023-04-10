package co.id.app.servicebe.service.accounts;

import co.id.app.servicebe.exception.UnauthorizeException;
import co.id.app.servicebe.model.request.LoginRequest;
import co.id.app.servicebe.model.response.AccountResponse;
import co.id.app.servicebe.security.JsonWebTokenUtil;
import co.id.app.servicebe.security.PasswordUtil;
import co.id.app.servicebe.service.BaseService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService implements BaseService<LoginRequest, AccountResponse> {

    @Autowired
    private PasswordUtil passwordUtil;
    @Autowired
    private FindAccountByUsernameService findAccountByUsernameService;
    @Autowired
    private JsonWebTokenUtil jsonWebTokenUtil;

    @Override
    public AccountResponse execute(LoginRequest request) {
        log.info("request login = {} ", JSON.toJSON(request));
        AccountResponse accountResponse = findAccountByUsernameService.execute(request);
        log.info("account response = {} ",JSON.toJSON(accountResponse));
        if (!accountResponse.getPassword().equals(passwordUtil.createOrVerifyPassword(request.getPassword()))){
            log.info("password request : {} - password in db : {} ",request.getPassword(), accountResponse.getPassword());
            throw new UnauthorizeException("wrong username & password");
        }
        log.info("success login");
        accountResponse.setAccessToken(jsonWebTokenUtil.createJwtToken(accountResponse));
        log.info("response accounts = {} ",JSON.toJSON(accountResponse));
        return accountResponse;
    }
}
