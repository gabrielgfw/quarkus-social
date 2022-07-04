package io.github.gabrielgfw.quarkussocial.rest.dto;

import javax.validation.ConstraintViolation;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ResponseError {

    private String message;
    private Collection<FildError> errors;

    public ResponseError(String message, Collection<FildError> errors) {
        this.message = message;
        this.errors = errors;
    }

    // <T> define um tipo de retorno dinâmico, baseado na resposta do tratamento de validações
    public static <T> ResponseError createFromValidation(Set<ConstraintViolation<T>> violations) {
        // Utilizando o stream para possibilitar a aplicação do método map()
        // instanciando um FildError para cada validação violada presente no 'violations'.
        List<FildError> errors = violations
                .stream()
                .map(cv -> new FildError(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());

        String message = "Validation error";
        return new ResponseError(message, errors);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<FildError> getErrors() {
        return errors;
    }

    public void setErrors(Collection<FildError> errors) {
        this.errors = errors;
    }
}
