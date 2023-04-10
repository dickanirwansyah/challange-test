package co.id.app.servicebe.service.roles;

import co.id.app.servicebe.entity.Roles;
import co.id.app.servicebe.model.request.EmptyRequest;
import co.id.app.servicebe.model.response.ValidationResponse;
import co.id.app.servicebe.repository.RolesRepository;
import co.id.app.servicebe.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class InitiateRoleService implements BaseService<EmptyRequest, ValidationResponse> {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public ValidationResponse execute(EmptyRequest request) {
        return ValidationResponse.builder()
                .validResponse(true)
                .build();
    }

    @PostConstruct
    public void initializeDataRole(){
        if (rolesRepository.findAll().isEmpty()){
            log.info("start initilize data role");
            List<String> dataRoles = Arrays.asList(
                    "Super Admin",
                    "Employee",
                    "Customer");

            dataRoles.stream().forEach(data -> {
                Roles roles = new Roles();
                roles.setName(data);
                rolesRepository.save(roles);
            });
            log.info("success initialize data role");
        }
    }
}
