package com.heidan.rediscombat.exception;

/**
 * Create by heidan on 2020/1/13 17:29
 */

public class BusinessException extends RuntimeException {

    private final int messageCode;

    private final String detailMessage;

    public BusinessException(int messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
        this.detailMessage = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
