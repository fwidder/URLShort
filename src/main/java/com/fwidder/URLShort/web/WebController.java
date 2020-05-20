package com.fwidder.URLShort.web;

import com.fwidder.URLShort.dao.UrlDAO;
import com.fwidder.URLShort.model.LongUrl;
import com.fwidder.URLShort.service.UrlShortService;
import com.fwidder.URLShort.util.UrlUtils;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@Controller
public class WebController {
    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    UrlShortService urlShortService;

    @Autowired
    UrlDAO urlDAO;

    @Value("${app.host.primary}")
    private String hostPrimary;

    @Value("${app.host.secondary}")
    private String hostSecondary;

    @Value("${app.host.hasSecondary}")
    private Boolean hasSecondaryHost;

    private void header(Model model) {
        model.addAttribute("count", urlDAO.count());
    }

    @GetMapping("/shortView")
    public String shortUrl(@RequestParam(name = "url") String url, Model model, HttpServletRequest request) {
        model.addAttribute("url", url);
        model.addAttribute("hostPrimary", hostPrimary);
        model.addAttribute("hostSecondary", hostSecondary);
        model.addAttribute("hasSecondaryHost", hasSecondaryHost);
        try {
            model.addAttribute("urlShoort", urlShortService.shortUrlOf(url));
            model.addAttribute("urlerror",false);
            header(model);
            return "short";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        model.addAttribute("urlerror",true);
        header(model);
        return "shortForm";
    }

    @GetMapping("/short")
    public String shortUrlForm(Model model, HttpServletRequest request) throws MalformedURLException {
        log.info("Request from IP \"{}\" for URL \"URL-Form\"", request.getHeader("X-FORWARDED-FOR"));
        model.addAttribute("longUrl", new LongUrl());
        return "shortForm";
    }

    @GetMapping("/s/{urlShort}")
    public RedirectView longUrl(@PathVariable(value = "urlShort") String urlShort, HttpServletRequest request) throws NotFoundException {
        log.info("Request from IP \"{}\" for Short-URL \"{}\"", request.getHeader("X-FORWARDED-FOR"),urlShort);
        String url = urlShortService.longUrlOf(urlShort);
        log.info("Redirect IP \"{}\" to URL \"{}\"", request.getHeader("X-FORWARDED-FOR"),url);
        return new RedirectView(url);
    }

    @PostMapping("/short")
    public String submitShortUrl(@ModelAttribute LongUrl longUrl, Model model, HttpServletRequest request) throws MalformedURLException {
        log.info("Request from IP \"{}\" to Short-URL \"{}\"", request.getHeader("X-FORWARDED-FOR"),longUrl.getUrl());
        String urlShort = urlShortService.shortUrlOf(longUrl.getUrl());
        log.info("Return to IP \"{}\" the Short-URL \"{}\"", request.getHeader("X-FORWARDED-FOR"),urlShort);
        return shortUrl(longUrl.getUrl(), model, request);
    }

}