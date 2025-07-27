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
public class AccountUserDto implements Serializable {
    private static final long serialVersionUID = 8482537036298565057L;

    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String currency;
    private Date createdAt;

}
