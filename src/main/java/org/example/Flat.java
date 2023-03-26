package org.example;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Flat implements Comparable<Flat> {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull(message = "Имя не может быть null")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull(message = "Объект класса Coordinates не может быть null")
    private Coordinates coordinates; //Поле не может быть null
    @NotNull(message = "Дата не может быть null")
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Min(message = "Область должна быть больше нуля", value = 0)
    private Long area; //Значение поля должно быть больше 0
    @Min(message = "Кол-во комнат должно быть больше нуля", value = 0)
    private int numberOfRooms; //Значение поля должно быть больше 0

    // wtf is this private boolean new;

    @DecimalMin(message = "Время до метро на транспорте должно быть больше нуля", value = "0.00000000000000001")
    private float timeToMetroByTransport; //Значение поля должно быть больше 0
    @NotNull(message = "Вид не может быть null")
    private View view; //Поле может быть null
    @NotNull(message = "Дом не может быть null")
    private House house; //Поле может быть null


    @Override
    public int compareTo(Flat o) {
        return this.id - o.id;
    }
}
