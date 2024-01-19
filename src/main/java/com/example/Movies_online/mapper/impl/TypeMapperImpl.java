package com.example.Movies_online.mapper.impl;

import com.example.Movies_online.dto.type.TypeResponse;
import com.example.Movies_online.entities.Type;
import com.example.Movies_online.mapper.TypeMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeMapperImpl implements TypeMapper {
    @Override
    public List<TypeResponse> toDtoS(List<Type> all) {
        List<TypeResponse> typeResponses = new ArrayList<>();
        for (Type type: all){
            typeResponses.add(toDto(type));
        }
        return typeResponses;
    }

    private TypeResponse toDto(Type type) {
        TypeResponse typeResponse = new TypeResponse();
        typeResponse.setId(type.getId());
        typeResponse.setName(type.getName());
        return typeResponse;
    }
}