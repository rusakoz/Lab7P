package org.server;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Ktos implements Serializable {
    private String nameCommand;
    private Object object;
    public Ktos(String nameCommand, Object object){
        this.nameCommand = nameCommand;
        this.object = object;
    }
}
