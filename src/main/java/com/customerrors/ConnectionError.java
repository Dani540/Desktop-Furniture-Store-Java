package com.customerrors;

import java.sql.SQLException;

public class ConnectionError extends SQLException {
    public ConnectionError(String message){
        super(message);
    }
}
