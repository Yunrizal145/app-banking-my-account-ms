package com.spring.myaccountmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneratedAccountNumberResponse implements Serializable {
    private static final long serialVersionUID = 3899957440988351533L;

    private Boolean isSuccess;
    private String accountNumber;
}
