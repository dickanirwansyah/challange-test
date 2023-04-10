package co.id.app.servicebe.security;

import co.id.app.servicebe.constant.ConstantValue;
import co.id.app.servicebe.model.response.ServerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Configuration
public class SecurityFilter implements Filter {

    @Value("${internal.namingApiKey}")
    private String APIKEY;
    @Value("${internal.namingAuthorization}")
    private String AUTHORIZATION;

    @Value("${internal.apiKey}")
    private String internalKey;

    @Autowired
    private JsonWebTokenUtil jwtUtil;

    /** api ignore secure **/
    private static final String[] endPointIgnores = {
            "/swagger",
            "/swagger-ui/",
            "/swagger-ui/springfox.css",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/springfox.js",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-ui/favicon-16x16.png",
            "/swagger-resources",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/v2",
            "/v2/api-docs",
            "/",
            "/api/v1/accounts/register",
            "/api/v1/accounts/login"
    };

    /** api secure **/
    private static final String[] endpointBackOffices = {
            "/api/v1/job",
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String apiKey = request.getHeader(APIKEY);
        log.info("api key={}", apiKey);

        /** check api key internal */
        AtomicReference<Boolean> validIgnores = new AtomicReference<>();
        Arrays.stream(endPointIgnores)
                .filter(url -> request.getRequestURI().equalsIgnoreCase(url))
                .findAny()
                .ifPresentOrElse(data -> validIgnores.set(true), () -> validIgnores.set(false));

        boolean validIgnoresApis = validIgnores.get();
        boolean validInternalKey = this.dovalidInternalKey(apiKey);
        boolean validBackOffice = this.doValidBackOfficeEndpoint(request, response, filterChain);

        log.info("valid ingnores api = {} ",validIgnoresApis);
        log.info("valid internal key = {} ",validInternalKey);
        log.info("valid backoffice = {} ",validBackOffice);

        if (validIgnoresApis || validInternalKey || validBackOffice) {
            filterChain.doFilter(request, servletResponse);
            return;
        }
        this.sendError(servletResponse);
    }

    private Boolean doValidBackOfficeEndpoint(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                                              FilterChain filterChain) {
        AtomicReference<Boolean> isValidPermission = new AtomicReference<>();
        log.info("request uri={}", servletRequest.getRequestURI());
        Arrays.stream(endpointBackOffices).filter(url -> servletRequest.getRequestURI().contains(url)).findAny()
                .ifPresentOrElse(data -> {
                    String tokenFromHeader = servletRequest.getHeader(AUTHORIZATION);
                    if (ObjectUtils.isEmpty(tokenFromHeader)) {
                        log.info("Authorization header is null");
                        isValidPermission.set(false);
                    }
                    if (!jwtUtil.validateJwtToken(tokenFromHeader)) {
                        log.info("validation token bearer not valid");
                        isValidPermission.set(false);
                    } else {
                        log.info("token valid");
                        isValidPermission.set(true);
                    }
                }, () -> isValidPermission.set(false));
        return isValidPermission.get();
    }

    private Boolean dovalidInternalKey(String apiKey) {
        AtomicReference<Boolean> isValidPermission = new AtomicReference<>();
        if (StringUtils.isNotEmpty(apiKey)) {
            log.info("check api key={}", apiKey);
            boolean valid = apiKey.equals(internalKey) ? true : false;
            if (!valid) {
                log.info("api key not match");
                isValidPermission.set(false);
            } else {
                isValidPermission.set(true);
            }
        } else {
            isValidPermission.set(false);
        }
        return isValidPermission.get();
    }

    private HttpServletResponse sendError(ServletResponse servletResponse) throws IOException {
        byte[] serverResponseByte = convertErrorResponseByte(ServerResponse
                .builder()
                .status(HttpStatus.FORBIDDEN.value())
                .timestamp(new Date())
                .message(ConstantValue.MESSAGE_FORBIDEN)
                .build());
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Content-Type", "application/json");
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.getOutputStream().write(serverResponseByte);
        return httpServletResponse;
    }

    private byte[] convertErrorResponseByte(ServerResponse serverResponse) throws IOException {
        String convertSerialize = new ObjectMapper().writeValueAsString(serverResponse);
        return convertSerialize.getBytes();
    }
}
