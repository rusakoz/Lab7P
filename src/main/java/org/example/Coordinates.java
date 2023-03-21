package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Coordinates {
    private Integer x; //Поле не может быть null
    private float y;
}
