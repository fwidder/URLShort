package com.fwidder.URLShort.util;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlUtils {
    private static final Logger log = LoggerFactory.getLogger(UrlUtils.class);
    private static final UrlValidator urlValidator;

    static {
        urlValidator = UrlValidator.getInstance();
    }

    public static boolean isUrlValid(String url) {
        boolean valid = urlValidator.isValid(url);
        log.debug("Url \"{}\" valid? {}", url, valid);
        return valid;
    }
}
