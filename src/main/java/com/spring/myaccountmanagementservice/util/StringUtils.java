package com.spring.myaccountmanagementservice.util;

import java.util.Random;

public class StringUtils {

    public static String generatedUniqueAccountNumber(){
        return String.format("%010d", new Random().nextInt(999999999));
    }
}
