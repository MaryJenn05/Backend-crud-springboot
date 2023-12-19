package com.crud.entitydemo.entitySingapore.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

@Table(name = "entitie")
public class EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uen", nullable = false)
    private String uen;

    @Column( name = "issuance_agency_id", nullable = false)
    private String issuanceAgency;

    @Column( name = "uen_status", nullable = false)
    private String uenStatus;

    @Column( name = "entity_name", nullable = false)
    private String entityName;

    @Column( name = "entity_type", nullable = false)
    private String entityType;

    @Column( name = "uen_issue_date", nullable = false)
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Date format must be yyyy-mm-dd")
    private LocalDate uenIssueDate;

    @Column( name = "reg_street_name", nullable = false)
    private String regStreetName;

    @Column( name = "reg_postal_code", nullable = false)
    private String regPostalCode;


}
