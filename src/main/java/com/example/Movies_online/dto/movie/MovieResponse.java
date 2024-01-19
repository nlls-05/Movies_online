package com.example.Movies_online.dto.movie;

import com.example.Movies_online.entities.Type;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieResponse {
    private Long id;

    private String name;
    private String author_full_name;
    private String transcript;
    private String created_date;
    private Integer price;
    private Integer age_access;
    private Boolean exist;
    private String type;
}