package com.plavsic.network.authorization.feign.config;

import com.plavsic.network.authorization.feign.exception.NotFoundException;
import com.plavsic.network.authorization.feign.exception.UserAlreadyExistsException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        String errorMessage;
        try {
            errorMessage = handleErrorMessage(readResponseBody(response));
            return switch (response.status()) {
                case 404 -> new NotFoundException("User Not Found!");
                case 409 -> new UserAlreadyExistsException(errorMessage);
                default -> new Exception("Generic error");
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readResponseBody(Response response) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }

    private String handleErrorMessage(String errorMsg){
        if(errorMsg.contains("users_email")){
            return "Email already exists!";
        }else if(errorMsg.contains("users_username")){
            return "Username already exists!";
        }
        return null;
    }
}
