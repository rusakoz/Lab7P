package org.example;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class House {
    @NotNull(message = "Название дома не может быть null")
    private String name; //Поле может быть null
    @Max(message = "Максимальный возраст здания 578 лет", value = 578)
    @Min(message = "Минимальный возраст здания 0 лет", value = 0)
    private long year; //Максимальное значение поля: 578, Значение поля должно быть больше 0
    @Min(message = "Минимальное кол-во дверей в здании 0", value = 0)
    private long numberOfFloors; //Значение поля должно быть больше 0

}
