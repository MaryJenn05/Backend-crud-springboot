package com.crud.entitydemo.entitySingapore.repository;

import com.crud.entitydemo.entitySingapore.model.EntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<EntityModel, Long> {
    boolean existsByUen(String uen);
}
