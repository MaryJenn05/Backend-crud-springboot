package com.crud.entitydemo.entitySingapore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EntityRequestDto {

    private String issuanceAgencyId;
    private String uenStatus;
    private String entityName;
    private String entityType;
    private String regStreetName;
    private String regPostalCode;

}
