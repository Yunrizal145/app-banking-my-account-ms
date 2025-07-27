package com.spring.myaccountmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSaldoByAccountNumberResponse implements Serializable {
    private static final long serialVersionUID = 3798951982733434695L;

    private BigDecimal saldo;
    private String accountNumber;
    private String accountType;
}
