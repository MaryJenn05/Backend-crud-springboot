package com.crud.entitydemo.entitySingapore.controller;


import com.crud.entitydemo.entitySingapore.dto.request.EntityRequestDto;
import com.crud.entitydemo.entitySingapore.dto.response.EntityResponseDto;
import com.crud.entitydemo.entitySingapore.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
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
    @PutMapping("/entities/{id}")
    public ResponseEntity<EntityResponseDto> updateEntity(@PathVariable Long id, @RequestBody EntityRequestDto entityRequestDto){
        return new ResponseEntity<EntityResponseDto>( entityService.updateEntity(id, entityRequestDto), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/entities/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable Long id){
        entityService.deleteEntity(id);
        return new ResponseEntity<String>("Entity deleted", HttpStatus.OK);
    }
}
