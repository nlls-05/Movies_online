package com.example.Movies_online.service.impl;

import com.example.Movies_online.dto.type.TypeResponse;
import com.example.Movies_online.entities.Type;
import com.example.Movies_online.exception.NotFoundException;
import com.example.Movies_online.mapper.TypeMapper;
import com.example.Movies_online.repositories.TypeRepository;
import com.example.Movies_online.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeMapper typeMapper;
    private final TypeRepository typeRepository;
    @Override
    public void addType(String name, String token) {
        if (typeRepository.findByName(name).isPresent())
            throw new NotFoundException("type with name: "+name+" is already exist!", HttpStatus.BAD_REQUEST);
        Type type = new Type();
        type.setName(name);
        typeRepository.save(type);
    }

    @Override
    public List<TypeResponse> getAll() {
        return typeMapper.toDtoS(typeRepository.findAll());
    }
}