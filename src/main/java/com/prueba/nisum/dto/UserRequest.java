package com.prueba.nisum.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {

    @NotEmpty(message = "Name is required")
    @Schema(example = "Jaime Che")
    private String name;

    @Email(message = "Invalid email format")
    @Schema(example = "jaime@gmail.com")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password is required")
    @Schema(example = "Password123")
    private String password;

    private List<Phone> phones;

    @Getter
    @Setter
    public static class Phone {
        @Schema(example = "1234567")
        private String number;
        @Schema(example = "1")
        private String citycode;
        @Schema(example = "56")
        private String contrycode;
    }
}
