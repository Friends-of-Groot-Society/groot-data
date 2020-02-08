package net.groot.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GrootNotFoundException extends RuntimeException {
    public GrootNotFoundException(String message) {
        super(message);
    }
}
