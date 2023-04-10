package co.id.app.servicebe.service.accounts;

import co.id.app.servicebe.model.request.AccountRequest;
import co.id.app.servicebe.model.response.ValidationResponse;
import co.id.app.servicebe.repository.AccountRepository;
import co.id.app.servicebe.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidateAccountByUsernameService implements BaseService<AccountRequest, ValidationResponse> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ValidationResponse execute(AccountRequest request) {
        Integer counter = accountRepository.countUserByUsername(request.getUserName());
        if (counter > 0){
            return ValidationResponse.builder()
                    .validResponse(false)
                    .build();
        }
        return ValidationResponse.builder()
                .validResponse(true)
                .build();
    }
}
