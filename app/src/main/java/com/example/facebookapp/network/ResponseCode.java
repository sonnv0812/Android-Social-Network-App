package com.example.facebookapp.network;

public interface ResponseCode {
    public String OK = "1000";
    public String CANNOT_CONNECT_TO_DB = "1001";
    public String PARAMETER_IS_NOT_ENOUGH = "1002";
    public String PARAMETER_TYPE_IS_INVALID = "1003";
    public String PARAMETER_VALUE_IS_INVALID = "1004";
    public String UNKNOWN_ERROR = "1005";

    public String USER_EXISTED = "9996";
}
