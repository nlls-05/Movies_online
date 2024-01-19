package com.example.Movies_online.service;

import com.example.Movies_online.dto.type.TypeResponse;

import java.util.List;

public interface TypeService {
    void addType(String name, String token);

    List<TypeResponse> getAll();
}