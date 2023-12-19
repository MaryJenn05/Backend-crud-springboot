package com.crud.entitydemo.entitySingapore.service.impl;

import com.crud.entitydemo.entitySingapore.dto.request.EntityRequestDto;
import com.crud.entitydemo.entitySingapore.dto.response.EntityResponseDto;
import com.crud.entitydemo.entitySingapore.model.EntityModel;
import com.crud.entitydemo.entitySingapore.repository.EntityRepository;
import com.crud.entitydemo.entitySingapore.service.EntityService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntityImpl implements EntityService {

    @Autowired
    private EntityRepository entityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EntityResponseDto createEntity(EntityRequestDto entityRequestDto) {
        EntityModel entityToCreate = modelMapper.map(entityRequestDto, EntityModel.class);
        if( entityRepository.existsByUen(entityToCreate.getUen())){
            throw new RuntimeException("UEN already exists");
        }
        entityToCreate.setUenIssueDate(LocalDate.now());
        entityRepository.save(entityToCreate);

        return modelMapper.map(entityToCreate, EntityResponseDto.class);
    }

    @Override
    public List<EntityResponseDto> getAllEntities() {
        List<EntityModel> entities = entityRepository.findAll();
        return entities.stream().map( entity -> modelMapper.map( entity, EntityResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteEntity(Long id) {
        if( !entityRepository.existsById(id)){
            throw new RuntimeException("Entity not found");
        }
        entityRepository.deleteById(id);
    }

    @Override
    public EntityResponseDto getEntityById(Long id) {
        if(!entityRepository.existsById(id)){
            throw new RuntimeException("Entity not found");
        }
        EntityModel entity = entityRepository.findById(id).get();
        return modelMapper.map(entity, EntityResponseDto.class);
    }

    @Override
    public EntityResponseDto updateEntity(Long id,  EntityRequestDto entityRequestDto) {

        if( !entityRepository.existsById(id)){
            throw new RuntimeException("Entity not found");
        }
        EntityModel entityToUpdate = entityRepository.findById(id).get();
        modelMapper.map(entityRequestDto, entityToUpdate);

        entityRepository.save(entityToUpdate);
        return modelMapper.map(entityToUpdate, EntityResponseDto.class);
    }

    @Override
    public void saveEntityData() {
        try{
            InputStream inputStream = getClass().getResourceAsStream("/static/EntitiesRegisteredwithACRA.csv");

            if( inputStream != null){
                CSVReader reader = new CSVReader( new InputStreamReader(inputStream));
                String[] headerLine = reader.readNext();
                String[] line;

                int insertionsSize = 1000;
                int count = 0;
                List<EntityModel> entitiesToSave = new ArrayList<>();

                while((line = reader.readNext()) != null && count<= 50){
                    EntityModel entityModel = new EntityModel();

                    entityModel.setUen(line[0]);
                    entityModel.setIssuanceAgency(line[1]);
                    entityModel.setUenStatus(line[2]);
                    entityModel.setEntityName(line[3]);
                    entityModel.setEntityType(line[4]);

                    entityModel.setUenIssueDate(LocalDate.parse(line[5]));

                    entityModel.setRegStreetName(line[6]);
                    entityModel.setRegPostalCode(line[7]);

                    entitiesToSave.add(entityModel);

                    if( ++count % insertionsSize == 0){
                        entityRepository.saveAll(entitiesToSave);
                        entitiesToSave.clear();
                    }
                }

                if(!entitiesToSave.isEmpty()){
                    entityRepository.saveAll(entitiesToSave);
                }
                reader.close();

            }else {
                throw new FileNotFoundException("File not found");
            }

        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
