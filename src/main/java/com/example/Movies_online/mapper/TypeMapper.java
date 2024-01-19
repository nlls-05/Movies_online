package com.example.Movies_online.mapper;

import com.example.Movies_online.dto.type.TypeResponse;
import com.example.Movies_online.entities.Type;

import java.util.List;

public interface TypeMapper {
    List<TypeResponse> toDtoS(List<Type> all);
}