package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class House {
    private String name; //Поле может быть null
    private long year; //Максимальное значение поля: 578, Значение поля должно быть больше 0
    private long numberOfFloors; //Значение поля должно быть больше 0
}
