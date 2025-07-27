package com.spring.myaccountmanagementservice.service;

import com.spring.myaccountmanagementservice.AccountTypeEnum;
import com.spring.myaccountmanagementservice.dto.AccountUserDto;
import com.spring.myaccountmanagementservice.dto.GetMutasiByAccountNumberRequest;
import com.spring.myaccountmanagementservice.dto.GetMutasiByAccountNumberResponse;
import com.spring.myaccountmanagementservice.dto.GetSaldoByAccountNumberRequest;
import com.spring.myaccountmanagementservice.dto.GetSaldoByAccountNumberResponse;
import com.spring.myaccountmanagementservice.dto.MutationDto;
import com.spring.myaccountmanagementservice.dto.SaveAccountUserResponse;
import com.spring.myaccountmanagementservice.dto.SaveUserAccountRequest;
import com.spring.myaccountmanagementservice.model.AccountUser;
import com.spring.myaccountmanagementservice.model.Mutation;
import com.spring.myaccountmanagementservice.repository.AccountUserRepository;
import com.spring.myaccountmanagementservice.repository.MutationRepository;
import com.spring.myaccountmanagementservice.util.StringUtils;
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

    public AccountUser getAccountUser(GetMutasiByAccountNumberRequest request) {
        log.info("get account user by user profile id");
        try {
            var accountUser = accountUserRepository.findByUserProfileIdAndIsDeleted(request.getUserProfileId(), false);
            if (accountUser.isPresent()) {
                return accountUser.get();
            }
            return AccountUser.builder().build();
        } catch (Exception e) {
            throw new RuntimeException("Get Account User Failed");
        }
    }

    public SaveAccountUserResponse saveAccountUser(SaveUserAccountRequest request){
        log.info("start saveAccountUser ... ");
        Boolean isSuccess = Boolean.FALSE;
        try {
            var saveDataUserAccount = AccountUser.builder()
                    .userProfileId(request.getUserProfileId())
                    .accountType(AccountTypeEnum.TABUNGAN.name())
                    .accountNumber(StringUtils.generatedUniqueAccountNumber())
                    .balance(BigDecimal.ZERO)
                    .currency("IDR")
                    .createdAt(new Date())
                    .isDeleted(false)
                    .build();

            // Save Account User
            log.info("req data for save to account user: {}", saveDataUserAccount);
            var accountUser = accountUserRepository.save(saveDataUserAccount);
            log.info("save data success");
            isSuccess = Boolean.TRUE;

            return SaveAccountUserResponse.builder()
                    .accountUserDto(AccountUserDto.builder()
                            .accountNumber(accountUser.getAccountNumber())
                            .balance(accountUser.getBalance())
                            .currency(accountUser.getCurrency())
                            .accountType(accountUser.getAccountType())
                            .createdAt(accountUser.getCreatedAt())
                            .build())
                    .message("Data Berhasil Ditambahkan")
                    .isSuccess(isSuccess)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error when save account user");
        }
    }
}
