package com.techmanage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("E-mail já utilizado: " + email);
    }
}
