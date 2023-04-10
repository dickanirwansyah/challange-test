package co.id.app.servicebe.security;

import co.id.app.servicebe.model.response.AccountResponse;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JsonWebTokenUtil {

    @Value("${jwt.token.expired}")
    private Integer jwtExpiredInMs;
    @Value("${jwt.token.refreshtoken}")
    private Integer jwtRefreshTokenInMs;
    @Value("${jwt.token.secret}")
    private String jwtSecret;

    public String createJwtToken(AccountResponse accountResponse){
        log.info("start create token");
        String token = Jwts.builder()
                .setSubject(accountResponse.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(( new Date()).getTime() + jwtExpiredInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        log.info("finish create token");
        return token;
    }

    public String getAccountUsernameFromJwtToken(String token){
        log.info("start get username from jwt token");
        String userInformation = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        log.info("finish get username from jwt token");
        return userInformation;
    }

    public boolean validateJwtToken(String token){
        try{
            log.info("start validate jwt token");
            log.info("token expired setting={}",jwtExpiredInMs);
            log.info("token refresh expired setting={}",jwtRefreshTokenInMs);
            log.info("token secret={}",jwtSecret);
            log.info("token incomming={}",token);
            Jwts.parser().setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            log.info("finish validate jwt token");
            return true;
        }catch (SignatureException e){
            log.error("error because sinature={}",e.getMessage());
        }catch (MalformedJwtException e){
            log.error("error because malformed={}",e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("error because expired={}",e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("error because unsupported={}",e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("error because illegal argument={}",e.getMessage());
        }
        return false;
    }
}
