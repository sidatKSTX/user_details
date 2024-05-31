package com.learnspring.userdetailsapi.dailysubmissions.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
