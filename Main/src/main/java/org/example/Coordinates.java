package org.example;

import com.opencsv.bean.CsvBindByName;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Класс описывает координаты
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    @NotNull(message = "Координата x не может быть null")
    @CsvBindByName(column = "Coordinates Integer x")
    private Integer x; //Поле не может быть null
    @CsvBindByName(column = "Coordinates Float y")
    private float y;

}
