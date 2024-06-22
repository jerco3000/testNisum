package com.prueba.nisum.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.regex.Pattern;

@Component
public class PasswordValidator {

    @Value("${password.regex}")
    private String passwordRegex;

    private Pattern pattern;

    @PostConstruct
    public void init() {
        // Remove quotes if present
        if (passwordRegex.startsWith("\"") && passwordRegex.endsWith("\"")) {
            passwordRegex = passwordRegex.substring(1, passwordRegex.length() - 1);
        }
        pattern = Pattern.compile(passwordRegex);
    }

    public boolean validate(String password) {
        return password != null && pattern.matcher(password).matches();
    }

    // Método añadido para test
    public void setPasswordRegex(String passwordRegex) {
        this.passwordRegex = passwordRegex;
        this.init();
    }
}
