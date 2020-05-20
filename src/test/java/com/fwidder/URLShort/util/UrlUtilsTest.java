package com.fwidder.URLShort.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrlUtilsTest {

    @Test
    void isUrlValidTest(){
        assertTrue(UrlUtils.isUrlValid("https://google.de"));
        assertTrue(UrlUtils.isUrlValid("https://fwidder.de"));
        assertTrue(UrlUtils.isUrlValid("https://www.wikipedia.de"));

        assertFalse(UrlUtils.isUrlValid("wkjd.fsd"));
        assertFalse(UrlUtils.isUrlValid("Tes ich bin Falsch"));
        assertFalse(UrlUtils.isUrlValid(null));
    }
}
