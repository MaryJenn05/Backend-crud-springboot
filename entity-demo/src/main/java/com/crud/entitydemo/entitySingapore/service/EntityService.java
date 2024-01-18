package com.crud.entitydemo.entitySingapore.service;

import com.crud.entitydemo.entitySingapore.dto.request.EntityRequestDto;
import com.crud.entitydemo.entitySingapore.dto.response.EntityResponseDto;
import com.crud.entitydemo.shared.model.PageQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntityService {
    public abstract EntityResponseDto createEntity(EntityRequestDto entityRequestDto);
    public abstract List<EntityResponseDto> getAllEntities();
    public abstract Page<EntityResponseDto> getAllEntitiesPage(Pageable pageable) ;

    public abstract Page<EntityResponseDto> getAllPageQuery(PageQuery pageableQuery);
    public abstract void deleteEntity(Long id);
    public abstract EntityResponseDto getEntityById(Long id);
    public abstract EntityResponseDto updateEntity(Long id, EntityRequestDto entityRequestDto);
    public abstract void saveEntityData();
}
