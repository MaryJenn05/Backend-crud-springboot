package com.crud.entitydemo.entitySingapore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EntityResponseDto {
    private Long id;
    private String uen;
    private String issuanceAgency;
    private String uenStatus;
    private String entityName;
    private String entityType;
    private LocalDate uenIssueDate;
    private String regStreetName;
    private String regPostalCode;

}
