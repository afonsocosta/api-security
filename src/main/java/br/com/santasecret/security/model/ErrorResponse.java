package br.com.santasecret.security.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ErrorResponse {

    private String message;
    private int httpStatus;
    private long timeStamp;
    private Map<String, String> errors;
}
