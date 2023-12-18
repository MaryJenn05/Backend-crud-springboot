package com.crud.entitydemo.entitySingapore.service;

import com.crud.entitydemo.entitySingapore.dto.request.EntityRequestDto;
import com.crud.entitydemo.entitySingapore.dto.response.EntityResponseDto;

public interface EntityService {
    public abstract EntityResponseDto createEntity(EntityRequestDto entityRequestDto);
    public abstract void saveEntityData();
}
