package com.example.shoppro.exceptions;



public class UserIdExceptionResponse {

    private String UserNotFound;

    public UserIdExceptionResponse(String userNotFound) {
        UserNotFound = userNotFound;
    }

    public String getUserNotFoundException() {
        return UserNotFound;
    }

    public void setUserNotFoundException(String userNotFound) {
        this.UserNotFound = userNotFound;
    }

    //    public UserIdExceptionResponse(String userIdException) {
//        this.userIdException = userIdException;
//    }
//
//    public String getUserIdException() {
//        return userIdException;
//    }
//
//    public void setUserIdException(String userIdException) (
//            userIdException = userIdException;
//    }

}