package com.crud.entitydemo.entitySingapore.controller;


import com.crud.entitydemo.entitySingapore.dto.request.EntityRequestDto;
import com.crud.entitydemo.entitySingapore.dto.response.EntityResponseDto;
import com.crud.entitydemo.entitySingapore.service.EntityService;
import com.crud.entitydemo.shared.model.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
//cors
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class EntityController {

    @Autowired
    private EntityService entityService;


    @Transactional
    @GetMapping("/data")
    public void setEntityData(){
        entityService.saveEntityData();
    }

    @Transactional
    @PostMapping("/entities")
    public ResponseEntity<EntityResponseDto> createEntity(@RequestBody EntityRequestDto entityRequestDto){
        return new ResponseEntity<EntityResponseDto>(entityService.createEntity(entityRequestDto), HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/entities")
    public ResponseEntity<List<EntityResponseDto>> getAllEntities(){
        return new ResponseEntity<List<EntityResponseDto>>(entityService.getAllEntities(), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/entities/page")
    public Page<EntityResponseDto> getAllEntitiesPage(Pageable pageable){
        return entityService.getAllEntitiesPage(pageable);
    }


    @Transactional
    @DeleteMapping("/entities/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable Long id){
        entityService.deleteEntity(id);
        return new ResponseEntity<String>( HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/entities/{id}")
    public ResponseEntity<EntityResponseDto> getEntityById(@PathVariable Long id){
         return new ResponseEntity<EntityResponseDto>( entityService.getEntityById(id), HttpStatus.OK);
    }

    @GetMapping("/entities/pageQuery")
    public Page<EntityResponseDto> getAllPageQuery(PageQuery pageableQuery){
        return entityService.getAllPageQuery(pageableQuery);
    }
}
