package com.fwidder.URLShort.util;

import com.fwidder.URLShort.service.UrlShortService;
import javassist.bytecode.DuplicateMemberException;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RandomStringTest {
    private static Logger log = LoggerFactory.getLogger(RandomStringTest.class);

    @RepeatedTest(value = 25)
    void nextStringTest() throws DuplicateMemberException {
        RandomString randomString = new RandomString(6);
        HashSet<String> strings = new HashSet<>();
        strings.add(randomString.nextString());

        for( int i = 1; i < 10000; i++){
            String ran = randomString.nextString();
            if(strings.contains(ran))
                throw new DuplicateMemberException("Random String \""+ran+"\" already exists in ArrayList! Size: "+strings.size());
            strings.add(ran);
        }

        log.info("ArrayList Size: " + strings.size());
    }

}