package com.customerrors;

public class InvalidUser extends RuntimeException {
    public InvalidUser(int state) {
        super(getReason(state));
    }

    private static String getReason(int state) {
        return state == 0 ? "User doesn't exists" : "Incorrect Password";
    }
}
