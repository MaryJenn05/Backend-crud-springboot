package com.crud.entitydemo.entitySingapore.service.impl;

import com.crud.entitydemo.entitySingapore.model.EntityModel;
import com.crud.entitydemo.entitySingapore.repository.EntityRepository;
import com.crud.entitydemo.entitySingapore.service.EntityService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntityImpl implements EntityService {

    @Autowired
    private EntityRepository entityRepository;

    @Override
    public void saveEntityData() {
        try{
            InputStream inputStream = getClass().getResourceAsStream("/static/EntitiesRegisteredwithACRA.csv");

            if( inputStream != null){
                CSVReader reader = new CSVReader( new InputStreamReader(inputStream));
                String[] line;

                int insertionsSize = 1000;
                int count = 0;
                List<EntityModel> entitiesToSave = new ArrayList<>();

                while((line = reader.readNext()) != null){
                    EntityModel entityModel = new EntityModel();

                    entityModel.setUen(line[0]);
                    entityModel.setIssuanceAgencyId(line[1]);
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
