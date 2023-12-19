package com.crud.entitydemo.entitySingapore.service;

import com.crud.entitydemo.entitySingapore.dto.request.EntityRequestDto;
import com.crud.entitydemo.entitySingapore.dto.response.EntityResponseDto;

import java.util.List;

public interface EntityService {
    public abstract EntityResponseDto createEntity(EntityRequestDto entityRequestDto);
    public abstract List<EntityResponseDto> getAllEntities();

    public abstract EntityResponseDto updateEntity(Long id, EntityRequestDto entityRequestDto);
    public abstract void saveEntityData();
}
