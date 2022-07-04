package io.github.gabrielgfw.quarkussocial.rest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateUserRequest {

    @NotBlank(message = "Name is required for this operation") // Não permite valores vazios ou somente espaços.
    private String name;

    @NotNull(message = "Age is required for this operation")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
