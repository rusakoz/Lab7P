package org.server;

import com.opencsv.bean.CsvBindByName;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * Класс описывает координаты
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates implements Serializable {
    @NotNull(message = "Координата x не может быть null")
    @CsvBindByName(column = "Coordinates Integer x")
    private Integer x; //Поле не может быть null
    @CsvBindByName(column = "Coordinates Float y")
    private float y;

}
