//package com.spring.myaccountmanagementservice.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//@Slf4j
//public class UserManagementService {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Value("${url.getUserMs}")
//    private String userManagementServiceUrl;
//
//    public String getValueFromAnotherService(){
//        log.info("Start getValueFromAnotherService ... ");
//
//        ResponseEntity<String> getValueFromAnotherService = restTemplate.getForEntity(userManagementServiceUrl, String.class);
//        return getValueFromAnotherService.getBody();
//    }
//}
