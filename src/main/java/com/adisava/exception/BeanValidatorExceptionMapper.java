package com.adisava.exception;

import javax.json.Json;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BeanValidatorExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(createErrorMessage(exception))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private JsonArray createErrorMessage(ConstraintViolationException exception) {
        JsonArrayBuilder errors = Json.createArrayBuilder();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            errors.add(
                    Json.createObjectBuilder()
                            .add("path", violation.getPropertyPath().toString())
                            .add("message", violation.getMessage())
            );
        }
        return errors.build();
    }
}
