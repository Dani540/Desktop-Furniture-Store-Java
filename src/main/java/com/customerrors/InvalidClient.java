package com.customerrors;

public class InvalidClient extends RuntimeException {
    public InvalidClient(int state) {
        super( getMessage(state) );
    }

    private static String getMessage(int state){
        String aux = "";
        switch (state){
            case 0 -> aux = "Name and Lastname are Empty!";
            case 1 -> aux = "Invalid Rut!";
        }
        return aux;
    }
}
