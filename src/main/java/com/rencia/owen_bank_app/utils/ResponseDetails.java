package com.rencia.owen_bank_app.utils;

import lombok.Data;

@Data
public class ResponseDetails {

    public static String ACCOUNT_CREATION_SUCCESS_CODE = "200";

    public static String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Account Created";

    public static String ACCOUNT_CREATION_FAILED_CODE = "400";

    public static String ACCOUNT_CREATION_FAILED_MESSAGE = "User Already Exists";

    public static String ACCOUNT_NOT_FOUND_CODE = "600";

    public static String ACCOUNT_NOT_FOUND_MESSAGE = "Account Does Not Exist";

    public static String ACCOUNT_CREDIT_SUCCESSFUL_CODE = "800";

    public static String ACCOUNT_CREDIT_SUCCESSFUL_MESSAGE = "Account Credited";

    public static String ACCOUNT_DEBIT_SUCCESSFUL_CODE = "700";

    public static String ACCOUNT_DEBIT_SUCCESSFUL_MESSAGE = "Account Debited";

    public static String INSUFFICIENT_FUND_CODE="300";

    public static String INSUFFICIENT_FUND_MESSAGE="Insufficient Fund";

}
