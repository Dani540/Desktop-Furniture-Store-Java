package com.customerrors;

import com.repository.DataBase;
import com.repository.Repository;

import java.sql.SQLException;

public class ConnectionError extends SQLException {
    public ConnectionError(String message){
        super(message);
    }

    public static void reconnect(DataBase dataBase) {
        System.out.println("Retry...");
        dataBase.getCONNECTION();
    }
}
