package com.fwidder.URLShort.service;

import com.fwidder.URLShort.dao.UrlDAO;
import com.fwidder.URLShort.model.Url;
import com.fwidder.URLShort.util.RandomString;
import com.fwidder.URLShort.util.UrlUtils;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class UrlShortService {
    private static final Logger log = LoggerFactory.getLogger(UrlShortService.class);
    @Autowired
    UrlDAO urlDAO;
    @Autowired
    private RandomString randomString;

    public String shortUrlOf(String url) throws MalformedURLException {
        if (!UrlUtils.isUrlValid(url))
            throw new MalformedURLException("URL is not Valid! " + url);

        Url dao = urlDAO.findUrlByUrl(url.trim());
        if (dao != null) {
            return dao.getUrlShort();
        }
        String shortUrl = randomString.nextString();
        log.debug("Url \"{}\" is shorted to \"{}\"", url, shortUrl);

        urlDAO.save(new Url(url, shortUrl));

        return shortUrl;
    }

    public String longUrlOf(String urlShort) throws NotFoundException {

        Url dao = urlDAO.findUrlByUrlShort(urlShort.trim());
        if (dao != null) {
            return dao.getUrl();
        }
        throw new NotFoundException("URL not found!");
    }
}
