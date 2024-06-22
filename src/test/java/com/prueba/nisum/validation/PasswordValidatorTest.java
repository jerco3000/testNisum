package com.prueba.nisum.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PasswordValidatorTest {

    @Value("${password.regex}")
    private String passwordRegex;

    private PasswordValidator passwordValidator;

    @BeforeEach
    public void setUp() {
        passwordValidator = new PasswordValidator();
        passwordValidator.setPasswordRegex(passwordRegex);
    }

    @Test
    public void validPasswordTest() {
        assertTrue(passwordValidator.validate("Valida123"));
    }

    @Test
    public void invalidPasswordTest() {
        assertFalse(passwordValidator.validate("passerror"));
    }
}
