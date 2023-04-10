package co.id.app.servicebe.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Slf4j
@Component
public class PasswordUtil {

    public String createOrVerifyPassword(String password){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            return byteToHex(messageDigest.digest());
        }catch (Exception e){
            log.error("error create password={}",e.getMessage());
        }
        return null;
    }

    private static String byteToHex(byte[] bytes){
        StringBuilder stringBuffer = new StringBuilder();
        for (byte data : bytes){
            stringBuffer.append(Integer
                    .toString((data & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
}
