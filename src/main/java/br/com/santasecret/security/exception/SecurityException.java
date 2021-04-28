package br.com.santasecret.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@Getter
public class SecurityException extends OAuth2Exception {

    private static final long serialVersionUID = -7060729589315962389L;

    private final HttpStatus httpStatus;

    public SecurityException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
