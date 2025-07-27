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
public class Mutation implements Serializable {
    private static final long serialVersionUID = -2202914078315551899L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "type")
    private String type; // "DEBIT" or "CREDIT"

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "user_profile_id")
    private Long userProfileId;

}
