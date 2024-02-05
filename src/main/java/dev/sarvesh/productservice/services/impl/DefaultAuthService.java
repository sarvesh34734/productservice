package dev.sarvesh.productservice.services.impl;

import dev.sarvesh.productservice.dtos.JwtDto;
import dev.sarvesh.productservice.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@AllArgsConstructor
public class DefaultAuthService implements AuthService {

    private static final String AUTH_VALIDATION_URL = "http://localhost:8080/auth/validate-token?email={e}&token={t}";

    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public JwtDto getUserFromJwtToken(String token,String email) throws AccessDeniedException {
        ResponseEntity<JwtDto> response;
        try{
            response = restTemplateBuilder.build().getForEntity(AUTH_VALIDATION_URL,
                    JwtDto.class,email,token);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            throw new AccessDeniedException("forbidden");
        }

        return response.getBody();
    }
}
