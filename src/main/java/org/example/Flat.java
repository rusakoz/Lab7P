package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Flat implements Comparable<Flat> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Значение поля должно быть больше 0
    private int numberOfRooms; //Значение поля должно быть больше 0

    // wtf is this private boolean new;
    private float timeToMetroByTransport; //Значение поля должно быть больше 0
    private View view; //Поле может быть null
    private House house; //Поле может быть null

    @Override
    public int compareTo(Flat o) {
        return this.id - o.id;
    }
}
