package co.id.app.servicebe.service.roles;

import co.id.app.servicebe.exception.ResourceNotfoundException;
import co.id.app.servicebe.model.request.FindByIdRequest;
import co.id.app.servicebe.model.response.RoleResponse;
import co.id.app.servicebe.repository.RolesRepository;
import co.id.app.servicebe.service.BaseService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FindRoleByIdService implements BaseService<FindByIdRequest, RoleResponse> {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public RoleResponse execute(FindByIdRequest request) {
        log.info("request role id = {} ", JSON.toJSON(request));
        return rolesRepository.findById(request.getId())
                .map(data -> RoleResponse
                        .builder()
                        .roleId(data.getId())
                        .roleName(data.getName())
                        .build())
                .orElseThrow(() -> new ResourceNotfoundException("role not found"));
    }
}
