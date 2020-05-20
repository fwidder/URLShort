package com.fwidder.URLShort.dao;

import com.fwidder.URLShort.model.Url;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UrlDAOTest{
    @Autowired
    UrlDAO urlDAO;

    @BeforeEach
    void clearAndFillDB(){
        assertNotNull(urlDAO);
        urlDAO.deleteAll();
        urlDAO.save(Url.builder().url("https://google.de").urlShort("abcdefg").build());
    }

    @Test
    void findUrlByUrlTest(){
        assertNull(urlDAO.findUrlByUrlShort("https://abc.de"));
    }

    @Test
    void findUrlByUrlShortTest(){
        Url url = urlDAO.findUrlByUrlShort("abcdefg");
        assertEquals("https://google.de", url.getUrl());

        assertNull(urlDAO.findUrlByUrlShort("1234567"));
    }
}
