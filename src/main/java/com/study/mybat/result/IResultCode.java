package com.study.mybat.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum IResultCode implements ResultCode
{
    //Error
    FAIL("E000", "   !", false),
    INVALID_KEY("E001", "유효하지 않은 key 값입니다.", false),
    NULLPOINTER("E002", "null 값에 접근하였습니다.", false),
    RESOURCE_NOT_FOUND("E003", "값이 존재하지 않습니다.", false),
//    DUPLICATED_KEY("E003", "중복되는 key 값입니다."),
    DIFFRENT_KEY("E004", "key 값이 일치하지 않습니다.", false),
    // DB 연결 확인은 어떻게 하는 것이 좋을까?
    DISCONNECTED("E005", "DataBase와 연결이 끊겼습니다.", false),
    //Success
    SUCCESS("OK", "   !", true);
    
    
    private String code;
    private String message;
    private boolean success;
    
    IResultCode(String code, String message, boolean success){
        this.code = code;
        this.message = message;
        this.success = success;
    }

    @Override
    public String getKey ()
    {
        // TODO Auto-generated method stub
        return this.code;
    }

    @Override
    public String getValue ()
    {
        // TODO Auto-generated method stub
        return this.message;
    }
    
    
    
}
