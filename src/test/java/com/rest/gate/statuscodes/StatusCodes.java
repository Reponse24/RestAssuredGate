package com.rest.gate.statuscodes;

public enum StatusCodes {
    CODE_200(200, "Ok"),
    CODE_201(201, "Created"),
    CODE_400(400, "Missing required field"),
    CODE_404(404, "Not Found"),
    CODE_401(401, "Unauthorized");

    public final int code;
    public final String msg;

    StatusCodes(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
