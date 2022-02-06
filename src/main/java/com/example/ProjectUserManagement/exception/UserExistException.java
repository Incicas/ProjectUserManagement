package com.example.ProjectUserManagement.exception;

import com.example.ProjectUserManagement.api.UserEntity;

public class UserExistException extends Exception{
    public UserExistException(String msg){
        super(msg);
    }
}
