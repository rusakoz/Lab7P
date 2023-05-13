package org.client.Collection;

import com.opencsv.bean.CsvBindByName;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * Класс описывает объект Дом
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class House implements Serializable {
    @NotNull(message = "Название дома не может быть null")
    @CsvBindByName(column = "House name")
    private String name; //Поле может быть null
    @Max(message = "Максимальный возраст здания 578 лет", value = 578)
    @DecimalMin(message = "Минимальный возраст здания 0 лет", value = "0.00000000000000001")
    @CsvBindByName(column = "House year")
    private long year; //Максимальное значение поля: 578, Значение поля должно быть больше 0
    @DecimalMin(message = "Минимальное кол-во дверей в здании 0", value = "0.00000000000000001")
    @CsvBindByName(column = "House numberOfFloors")
    private long numberOfFloors; //Значение поля должно быть больше 0

}
