package com.spring.myaccountmanagementservice.controller;

import com.spring.myaccountmanagementservice.dto.GetMutasiByAccountNumberRequest;
import com.spring.myaccountmanagementservice.dto.GetMutasiByAccountNumberResponse;
import com.spring.myaccountmanagementservice.dto.GetSaldoByAccountNumberRequest;
import com.spring.myaccountmanagementservice.dto.GetSaldoByAccountNumberResponse;
import com.spring.myaccountmanagementservice.dto.SaveAccountUserResponse;
import com.spring.myaccountmanagementservice.dto.SaveUserAccountRequest;
import com.spring.myaccountmanagementservice.model.AccountUser;
import com.spring.myaccountmanagementservice.service.MyAccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAccountManagementServiceController {

    @Autowired
    private MyAccountManagementService myAccountManagementService;

    @PostMapping("/getsaldo")
    public GetSaldoByAccountNumberResponse getSaldo(@RequestBody GetSaldoByAccountNumberRequest request){
        return myAccountManagementService.getSaldo(request);
    }

    @PostMapping("/getlistmutasi")
    public GetMutasiByAccountNumberResponse getListMutasi(@RequestBody GetMutasiByAccountNumberRequest request){
        return myAccountManagementService.getListMutasi(request);
    }

    @PostMapping("/getaccountuser")
    public AccountUser getAccountUser(@RequestBody GetMutasiByAccountNumberRequest request){
        return myAccountManagementService.getAccountUser(request);
    }

    @PostMapping("/saveaccountuser")
    public SaveAccountUserResponse saveAccountUser(@RequestBody SaveUserAccountRequest request){
        return myAccountManagementService.saveAccountUser(request);
    }

}
