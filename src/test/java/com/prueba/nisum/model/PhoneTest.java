package com.prueba.nisum.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhoneTest {

    private Phone phone;

    @BeforeEach
    public void setUp() {
        phone = new Phone();
    }

    @Test
    public void testSetId() {
        Long id = 1L;
        phone.setId(id);
        assertEquals(id, phone.getId());
    }

    @Test
    public void testSetNumber() {
        String number = "123456789";
        phone.setNumber(number);
        assertEquals(number, phone.getNumber());
    }

    @Test
    public void testSetCitycode() {
        String citycode = "01";
        phone.setCitycode(citycode);
        assertEquals(citycode, phone.getCitycode());
    }

    @Test
    public void testSetContrycode() {
        String contrycode = "57";
        phone.setContrycode(contrycode);
        assertEquals(contrycode, phone.getContrycode());
    }

    @Test
    public void testGetId() {
        Long id = 1L;
        phone.setId(id);
        assertEquals(id, phone.getId());
    }

    @Test
    public void testGetNumber() {
        String number = "123456789";
        phone.setNumber(number);
        assertEquals(number, phone.getNumber());
    }

    @Test
    public void testGetCitycode() {
        String citycode = "01";
        phone.setCitycode(citycode);
        assertEquals(citycode, phone.getCitycode());
    }

    @Test
    public void testGetContrycode() {
        String contrycode = "57";
        phone.setContrycode(contrycode);
        assertEquals(contrycode, phone.getContrycode());
    }
}
