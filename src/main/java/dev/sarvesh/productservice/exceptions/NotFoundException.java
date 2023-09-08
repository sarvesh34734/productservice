package dev.sarvesh.productservice.exceptions;

import org.springframework.web.client.HttpClientErrorException;

public class NotFoundException extends Exception{

    public NotFoundException(String message){
        super(message);
    }

}
