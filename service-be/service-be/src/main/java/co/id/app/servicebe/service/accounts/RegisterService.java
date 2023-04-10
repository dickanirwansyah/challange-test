package co.id.app.servicebe.service.accounts;

import co.id.app.servicebe.constant.ConstantValue;
import co.id.app.servicebe.entity.Accounts;
import co.id.app.servicebe.exception.InvalidResourceException;
import co.id.app.servicebe.model.request.AccountRequest;
import co.id.app.servicebe.model.request.FindByIdRequest;
import co.id.app.servicebe.model.response.ValidationResponse;
import co.id.app.servicebe.repository.AccountRepository;
import co.id.app.servicebe.security.PasswordUtil;
import co.id.app.servicebe.service.BaseService;
import co.id.app.servicebe.service.roles.FindRoleByIdService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class RegisterService implements BaseService<AccountRequest, ValidationResponse> {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FindRoleByIdService findRoleByIdService;

    @Autowired
    private ValidateAccountByUsernameService validateAccountByUsernameService;

    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    public ValidationResponse execute(AccountRequest request) {
        log.info("request register accounts = {} ", JSON.toJSON(request));
        if (validateAccountByUsernameService.execute(request).isValidResponse() && validPassword(request)){
            Accounts accounts = new Accounts();
            accounts.setStatus(ConstantValue.STATUS_ACTIVE);
            accounts.setPassword(passwordUtil.createOrVerifyPassword(request.getPassword()));
            accounts.setCreatedAt(LocalDateTime.now());
            accounts.setFullName(request.getFullName());

            FindByIdRequest findByIdRequest = new FindByIdRequest();
            findByIdRequest.setId(request.getRoleId());
            accounts.setRoleId(findRoleByIdService.execute(findByIdRequest).getRoleId());
            accounts.setUsername(request.getUserName());
            accountRepository.save(accounts);

            log.info("success create new accounts");
            return ValidationResponse.builder()
                    .validResponse(true)
                    .build();
        }
        log.info("failed create accounts");
        throw new InvalidResourceException("failed register accounts");
    }

    private boolean validPassword(AccountRequest request){
        if (!request.getPassword().equals(request.getConfirmPassword())){
            return false;
        }
        return true;
    }
}
