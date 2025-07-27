package com.spring.myaccountmanagementservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "account_user", schema = "my_account_management_service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountUser implements Serializable {
    private static final long serialVersionUID = -6076203037854058620L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long accountId;

    @Column(name = "user_profile_id")
    private Long userProfileId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency")
    private String currency;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
