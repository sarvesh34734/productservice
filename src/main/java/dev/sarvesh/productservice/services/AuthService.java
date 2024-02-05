package dev.sarvesh.productservice.services;

import dev.sarvesh.productservice.dtos.JwtDto;

import java.nio.file.AccessDeniedException;

public interface AuthService {

    JwtDto getUserFromJwtToken(final String token,String email) throws AccessDeniedException;

}
