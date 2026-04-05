package com.booking.User.application.constant;

public class CommonConstant {
    // ================== SYSTEM ==================
    public static final String TIMEZONE = "UTC";

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String DATETIME_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    // ================== RESPONSE ==================
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";

    // ================== HEADER ==================
    public static final String REQUEST_ID = "X-Request-ID";

    // ================== ERROR CODE ==================
    public static final String ERROR_FILE_CODE = "OPS-001";
    public static final String ERROR_UPLOAD_CODE = "OPS-002";
    public static final String ERROR_CLIENT_CODE = "OPS-003";
    public static final String ERROR_ACCOUNT_CODE = "OPS-005";
    public static final String ERROR_BROKER_CODE = "OPS-004";

    // ================== ERROR MESSAGE ==================
    public static final String ERROR_FILE_MSG = "Document type code invalid";
    public static final String ERROR_UPLOAD_MSG = "Upload file failed";
    public static final String ERROR_CLIENT_MSG = "Client code invalid";
    public static final String ERROR_ACCOUNT_MSG = "Account no invalid";
    public static final String ERROR_BROKER_MSG = "Broker invalid";


}
