package com.spring.myaccountmanagementservice.repository;

import com.spring.myaccountmanagementservice.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {

    Optional<AccountUser> findByAccountNumberAndIsDeleted(String accountNumber, boolean isDeleted);
    Optional<AccountUser> findByUserProfileIdAndIsDeleted(Long userProfileId, boolean isDeleted);

    @Modifying
    @Transactional
    @Query("UPDATE AccountUser au SET au.balance = :balance WHERE au.accountNumber = :accountNumber")
    int updateAccountBalance(@Param("accountNumber") String accountNumber, @Param("balance") BigDecimal balance);
}
