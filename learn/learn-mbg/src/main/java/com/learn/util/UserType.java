package com.learn.util;


public enum UserType {
    BACKEND(0),
    FRONT(1);
    private int userType;

    UserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }
}
