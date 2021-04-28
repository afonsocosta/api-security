package br.com.santasecret.security.handler;

import br.com.santasecret.security.model.Response;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(AuthenticationException.class)
    @Primary
    public ResponseEntity<Response<String>> handlerSecurityException(
            AuthenticationException securityException) {
        Response<String> response = new Response<>();
        response.setStatusCode(HttpStatus.NO_CONTENT.value());
        response.setData(securityException.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

}
