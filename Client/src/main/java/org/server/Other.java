package org.server;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Other implements Serializable {
    private int age;

    public Other(int age){
        this.age = age;
    }
}
