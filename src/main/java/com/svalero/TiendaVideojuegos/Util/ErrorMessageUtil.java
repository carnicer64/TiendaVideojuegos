package com.svalero.TiendaVideojuegos.Util;

import com.svalero.TiendaVideojuegos.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

public class ErrorMessageUtil {
    private static final Logger logger = LoggerFactory.getLogger(ErrorMessageUtil.class);

    public static ResponseEntity<ErrorMessage> getErrorExceptionResponseEntity(ConstraintViolationException cve) {
        Map<String, String> errors = new HashMap<>();
        cve.getConstraintViolations().forEach(error -> {
            logger.error(error.getMessage());
            String fieldName = error.getMessage();
            String name = error.getMessageTemplate();
            errors.put(fieldName, name);
        });
        ErrorMessage errorException = new ErrorMessage(500, "Internal Server Error", errors);
        return new ResponseEntity<>(errorException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
