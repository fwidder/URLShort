package com.fwidder.URLShort.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "url", indexes = {
        @Index(columnList = "url"),
        @Index(columnList = "urlShort")
})
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Url {
    @Id
    private String url;

    @Column(unique = true, updatable = false)
    private String urlShort;
}
