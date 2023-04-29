package org.example;

import com.opencsv.bean.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Flat implements Comparable<Flat> {

    @CsvBindByName
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull(message = "Имя не может быть null")
    @NotEmpty(message = "Имя не может быть пустым")
    @CsvBindByName
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull(message = "Объект класса Coordinates не может быть null")
    @CsvRecurse
    private Coordinates coordinates; //Поле не может быть null
    @NotNull(message = "Дата не может быть null")
    @CsvDate(value = "yyyy-MM-dd HH:mm:ss")
    @CsvBindByName
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @DecimalMin(message = "Область должна быть больше нуля", value = "0.00000000000000001")
    @CsvBindByName
    private Long area; //Значение поля должно быть больше 0
    @DecimalMin(message = "Кол-во комнат должно быть больше нуля", value = "0.00000000000000001")
    @CsvBindByName
    private int numberOfRooms; //Значение поля должно быть больше 0

    // wtf is this private boolean new;

    @DecimalMin(message = "Время до метро на транспорте должно быть больше нуля", value = "0.00000000000000001")
    @CsvBindByName
    private float timeToMetroByTransport; //Значение поля должно быть больше 0
    @NotNull(message = "Вид не может быть null")
    @CsvBindByName
    private View view; //Поле может быть null
    @NotNull(message = "Дом не может быть null")
    @CsvRecurse
    private House house; //Поле может быть null


    @Override
    public int compareTo(Flat o) {
        return this.numberOfRooms - o.numberOfRooms;
    }
}
