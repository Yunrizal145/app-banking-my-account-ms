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
public class SaveAccountUserResponse implements Serializable {
    private static final long serialVersionUID = 4702637699933924482L;

    private AccountUserDto accountUserDto;
    private String message;
    private Boolean isSuccess;
}
