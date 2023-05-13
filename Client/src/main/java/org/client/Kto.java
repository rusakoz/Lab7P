package org.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@Getter
@Setter
public class Kto implements Serializable {
    private String name;
    public Kto(String name){
        this.name = name;
    }
}
