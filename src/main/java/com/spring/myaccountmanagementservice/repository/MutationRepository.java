package com.spring.myaccountmanagementservice.repository;

import com.spring.myaccountmanagementservice.model.Mutation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MutationRepository extends JpaRepository<Mutation, Long> {

    List<Mutation> findByAccountNumberAndUserProfileIdOrderByCreatedAtDesc(String accountNumber, Long userProfileId);
}
