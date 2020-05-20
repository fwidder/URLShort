package com.fwidder.URLShort.service;

import com.fwidder.URLShort.util.UrlUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UrlShortServiceTest {

    @Autowired
    UrlShortService urlShortService;

    @Test
    void shortUrlOfTest() throws MalformedURLException {
        assertNotNull(urlShortService.shortUrlOf("https://fwidder.de"));

        assertThrows(MalformedURLException.class, () -> {
            urlShortService.shortUrlOf("Test keine Url oder so 123");
        });
    }
}
