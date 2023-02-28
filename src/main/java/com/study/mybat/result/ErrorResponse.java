package com.study.mybat.result;

import lombok.Data;

@Data
public class ErrorResponse
{
    private String message;
    private String code;
    private Object data;
    private String sentence;
    private boolean success;
    
    public ErrorResponse(IResultCode code) {
        this.setMessage(code.getValue());
        this.setCode(code);
        this.setSuccess (code.isSuccess());
    }
    
    public ErrorResponse(IResultCode code,
                         Object data) {
        this.setCode(code);
        this.setMessage(code.getValue());
        this.setData(data);
        this.setSuccess (code.isSuccess());
    }
    
    public ErrorResponse(IResultCode code,
                         String sentence) {
        this.setCode(code);
        this.setMessage(code.getValue());
        this.setSentence(sentence);
        this.setSuccess (code.isSuccess());
    }
    
    public ErrorResponse (IResultCode code,
                          String message,
                          Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
        this.setSuccess (code.isSuccess ());
    }
    
    public ErrorResponse(IResultCode code,
                         Object data,
                         String sentence) {
        this.setCode(code);
        this.setMessage(code.getValue());
        this.setData(data);
        this.setSentence(sentence);
        this.setSuccess (code.isSuccess());
    }
    
    public static ErrorResponse of(IResultCode code) {
        return new ErrorResponse(code);
    }
    
    public void setCode(IResultCode code) {
        this.code = code.getKey();
    }

}
