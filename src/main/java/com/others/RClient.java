package com.others;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RClient {
    private String rut, name, lastname;
    private int age;

    public RClient(String rut){
        this.rut = rut;
    }
}
