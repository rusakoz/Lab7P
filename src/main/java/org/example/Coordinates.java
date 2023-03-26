package org.example;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Coordinates {
    @NotNull(message = "Координата x не может быть null")
    private Integer x; //Поле не может быть null
    private float y;

    public Coordinates(Integer x, float y){
        this.x = x;
        this.y = y;
    }
}
