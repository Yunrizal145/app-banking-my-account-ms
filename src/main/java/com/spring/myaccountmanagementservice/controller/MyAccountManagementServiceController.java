package com.spring.myaccountmanagementservice.controller;

import com.spring.myaccountmanagementservice.dto.GetMutasiByAccountNumberRequest;
import com.spring.myaccountmanagementservice.dto.GetMutasiByAccountNumberResponse;
import com.spring.myaccountmanagementservice.dto.GetSaldoByAccountNumberRequest;
import com.spring.myaccountmanagementservice.dto.GetSaldoByAccountNumberResponse;
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

}
