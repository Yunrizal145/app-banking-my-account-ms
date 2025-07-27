package com.spring.myaccountmanagementservice.repository;

import com.spring.myaccountmanagementservice.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {

    Optional<AccountUser> findByAccountNumberAndIsDeleted(String accountNumber, boolean isDeleted);
}
