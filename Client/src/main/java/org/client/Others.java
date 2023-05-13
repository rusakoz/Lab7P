package org.client;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Others implements Serializable {
    private int age;

    public Others(int age){
        this.age = age;
    }
}
