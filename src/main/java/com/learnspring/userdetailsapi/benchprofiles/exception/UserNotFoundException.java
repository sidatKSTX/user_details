package com.learnspring.userdetailsapi.benchprofiles.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
