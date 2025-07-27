package com.spring.myaccountmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MutationDto implements Serializable {
    private static final long serialVersionUID = 3562668895505408347L;

    private String accountNumber;
    private String type;
    private BigDecimal amount;
    private String description;
    private Date createdAt;
}
