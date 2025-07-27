package com.spring.myaccountmanagementservice.service;

import com.spring.myaccountmanagementservice.dto.GetMutasiByAccountNumberRequest;
import com.spring.myaccountmanagementservice.dto.GetMutasiByAccountNumberResponse;
import com.spring.myaccountmanagementservice.dto.GetSaldoByAccountNumberRequest;
import com.spring.myaccountmanagementservice.dto.GetSaldoByAccountNumberResponse;
import com.spring.myaccountmanagementservice.dto.MutationDto;
import com.spring.myaccountmanagementservice.model.AccountUser;
import com.spring.myaccountmanagementservice.model.Mutation;
import com.spring.myaccountmanagementservice.repository.AccountUserRepository;
import com.spring.myaccountmanagementservice.repository.MutationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MyAccountManagementService {

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    private MutationRepository mutationRepository;

    public boolean checkUserBalance(String account, BigDecimal amount) {
        return accountUserRepository.findByAccountNumberAndIsDeleted(account, false)
                .map(a -> a.getBalance().compareTo(amount)!=0)
                .orElse(false);
    }

    public void deductBalanceAndRecordMutation(String account, BigDecimal amount, String description) {
        AccountUser acc = accountUserRepository.findByAccountNumberAndIsDeleted(account, false).orElseThrow();
        acc.setBalance(acc.getBalance().subtract(amount));
        accountUserRepository.save(acc);

        Mutation mutation = new Mutation();
        mutation.setAccountNumber(account);
        mutation.setType("DEBIT");
        mutation.setAmount(amount);
        mutation.setDescription(description);
        mutation.setCreatedAt(new Date());
        mutationRepository.save(mutation);
    }

    // Add Saldo User
    public void creditBalanceAndRecordMutation(String account, BigDecimal amount, String description) {
        AccountUser acc = accountUserRepository.findByAccountNumberAndIsDeleted(account, false).orElseThrow();
        log.info("");
        acc.setBalance(acc.getBalance().add(amount));
        accountUserRepository.save(acc);

        Mutation mutation = new Mutation();
        mutation.setAccountNumber(account);
        mutation.setType("CREDIT");
        mutation.setAmount(amount);
        mutation.setDescription(description);
        mutation.setCreatedAt(new Date());
        mutationRepository.save(mutation);
    }

    // check saldo
    public GetSaldoByAccountNumberResponse getSaldo(GetSaldoByAccountNumberRequest request){
        log.info("get saldo start...");
        try {
            AccountUser accountUser = accountUserRepository.findByAccountNumberAndIsDeleted(request.getAccountNumber(), false).orElseThrow(null);

            return GetSaldoByAccountNumberResponse.builder()
                    .saldo(accountUser.getBalance())
                    .accountNumber(accountUser.getAccountNumber())
                    .accountType(accountUser.getAccountType())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Check Saldo Failed");
        }
    }

    // get mutasi
    public GetMutasiByAccountNumberResponse getListMutasi(GetMutasiByAccountNumberRequest request){
        log.info("check mutasi start...");
        try {
            List<Mutation> listMutation = mutationRepository.findByAccountNumberAndUserProfileIdOrderByCreatedAtDesc(request.getAccountNumber(), request.getUserProfileId());
            List<MutationDto> mutationDtoList = listMutation.stream()
                            .map(mutation -> MutationDto.builder()
                                    .accountNumber(mutation.getAccountNumber())
                                    .type(mutation.getType())
                                    .amount(mutation.getAmount())
                                    .description(mutation.getDescription())
                                    .createdAt(mutation.getCreatedAt())
                                    .build()
                            ).collect(Collectors.toList());

            return GetMutasiByAccountNumberResponse.builder()
                    .mutationDtoList(mutationDtoList)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Check Mutasi Failed");
        }
    }
}
