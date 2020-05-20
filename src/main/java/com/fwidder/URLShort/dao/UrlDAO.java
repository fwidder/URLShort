package com.fwidder.URLShort.dao;

import com.fwidder.URLShort.model.Url;
import org.springframework.data.repository.CrudRepository;

public interface UrlDAO extends CrudRepository<Url, String> {
    Url findUrlByUrl(String url);

    Url findUrlByUrlShort(String urlShort);
}
