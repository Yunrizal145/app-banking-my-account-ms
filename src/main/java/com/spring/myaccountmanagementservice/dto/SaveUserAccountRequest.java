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
public class SaveUserAccountRequest implements Serializable {
    private static final long serialVersionUID = 3188770467646848359L;

    private Long userProfileId;
}
