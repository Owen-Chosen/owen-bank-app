package com.rencia.owen_bank_app.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Response {

    public static String ACCOUNT_CREATION_SUCCESS_CODE = "200";
    public static String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Account Created Successfully";
    public static String ACCOUNT_CREATION_FAILED_CODE = "400";
    public static String ACCOUNT_CREATION_FAILED_MESSAGE = "User Already Exists";

}
