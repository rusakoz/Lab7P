package org.client.CommandManager.CreateObjectForCollection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.client.Collection.*;
import org.example.InputOutput;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

@NoArgsConstructor
@Getter
@Setter
public class AddObject implements Serializable {
    public static Flat newObjectFromScanner(){
        Scanner scanner = new Scanner(System.in);
        Date date = new Date();
        int id = 0;

        String name = null;
        //Coordinates
        Coordinates coordinates;
        Integer X;
        float Y;
        //Coordinates
        Long area = (long) -1;
        int numberOfRooms = -1;
        float timeToMetroByTransport = -1;
        View view = null;
        //House
        House house = null;
        String nameHouse;
        long yearHouse = -1;
        long numberOfFloorsHouse = -1;
        //House

        new InputOutput().Output("Введите имя");
        if (scanner.hasNext()) {
            while (!scanner.hasNext("([А-Я][а-я]+)|([A-Z][a-z]+)")) {
                new InputOutput().Output("Неправильно введено имя, проверьте формат: Aaaaaaa");
                scanner.nextLine();
            }
            name = scanner.nextLine();
            new InputOutput().Output("Success");
        }
        new InputOutput().Output("Введите координату X, пример: 12345");
        while (!scanner.hasNextInt()) {
            new InputOutput().Output("Неправильно введена координата X, проверьте формат: 12345");
            scanner.next();
        }
        X = scanner.nextInt();
        new InputOutput().Output("Success");


        new InputOutput().Output("Введите кординату Y, пример: 12345,1");
        while (!scanner.hasNextFloat()) {
            new InputOutput().Output("Неправильно введена координата Y, проверьте формат: 12345,1");
            scanner.next();
        }
        Y = scanner.nextFloat();
        new InputOutput().Output("Success");
        coordinates = new Coordinates(X, Y);


        new InputOutput().Output("Введите область(>нуля!), пример: 12345");
        while (!(area > 0)){
            while (!scanner.hasNextLong()){
                new InputOutput().Output("Область введена неправильно, проверьте формат: 12345(>нуля!)");
                scanner.next();
            }
            area = scanner.nextLong();
            if(area <= 0){
                new InputOutput().Output("Область введена неправильно, проверьте формат: 12345(>нуля!)");
            }else new InputOutput().Output("Success");
        }


        new InputOutput().Output("Введите кол-во комнат, пример: 12345(>нуля!)");
        while (!(numberOfRooms > 0)){
            while (!scanner.hasNextInt()){
                new InputOutput().Output("Кол-во комнат введено неправильно, проверьте формат: 12345(>нуля!)");
                scanner.next();
            }
            numberOfRooms = scanner.nextInt();
            if(numberOfRooms <= 0){
                new InputOutput().Output("Кол-во комнат введено неправильно, проверьте формат: 12345(>нуля!)");
            }else new InputOutput().Output("Success");
        }


        new InputOutput().Output("Введите время до метро, пример: 12345,1(>нуля!");
        while (!(timeToMetroByTransport > 0)){
            while (!scanner.hasNextFloat()){
                new InputOutput().Output("Время до метро введено неправильно, проверьте формат: 12345,1(>нуля!)");
                scanner.next();
            }
            timeToMetroByTransport = scanner.nextFloat();
            if(timeToMetroByTransport <= 0){
                new InputOutput().Output("Время до метро введено неправильно, проверьте формат: 12345,1(>нуля!)");
            }else new InputOutput().Output("Success");
        }


        new InputOutput().Output("Введите вид, список доступных видов: YARD, PARK, NORMAL");
        if (scanner.hasNext()) {
            String str = scanner.nextLine();
            while (view == null) {
                switch (str) {
                    case "YARD" -> {
                        view = View.YARD;
                        new InputOutput().Output("Success");
                    }
                    case "PARK" -> {
                        view = View.PARK;
                        new InputOutput().Output("Success");
                    }
                    case "NORMAL" -> {
                        view = View.NORMAL;
                        new InputOutput().Output("Success");
                    }
                    default -> {
                        if (scanner.hasNext())new InputOutput().Output("Вид введен неверно, проверьте формат: YARD, PARK, NORMAL");
                        str = scanner.nextLine();
                    }
                }
            }
        }

        new InputOutput().Output("Введите название дома, пример: Ааааа");
        while (!scanner.hasNext("([А-Я][а-я]+)|([A-Z][a-z]+)")) {
            new InputOutput().Output("Неправильно введено название дома, проверьте формат: Aaaaa");
            scanner.nextLine();
        }
        nameHouse = scanner.nextLine();
        new InputOutput().Output("Success");


        new InputOutput().Output("Введите возраст дома, пример: 12345(от 1 до 578)");
        while (!(yearHouse > 0 & yearHouse <= 578)){
            while (!scanner.hasNextLong()){
                new InputOutput().Output("Возраст дома введен неправильно, проверьте формат: 12345(от 1 до 578!)");
                scanner.next();
            }
            yearHouse = scanner.nextLong();
            if(yearHouse <= 0 | yearHouse > 578){
                new InputOutput().Output("Взраст дома введен неправильно, проверьте формат: 12345(от 1 до 578!)");
            }else new InputOutput().Output("Success");
        }


        new InputOutput().Output("Введите кол-во дверей дома, пример: 12345(>нуля!)");
        while (!(numberOfFloorsHouse > 0)){
            while (!scanner.hasNextLong()){
                new InputOutput().Output("Кол-во дверей дома введено неправильно, проверьте формат: 12345(>нуля!)");
                scanner.next();
            }
            numberOfFloorsHouse = scanner.nextLong();
            if(numberOfFloorsHouse <= 0){
                new InputOutput().Output("Кол-во дверей дома введено неправильно, проверьте формат: 12345(>нуля!)");
            }else new InputOutput().Output("Success");
        }

        house = new House(nameHouse, yearHouse, numberOfFloorsHouse);
        return new Flat(id, name, coordinates, date, area, numberOfRooms, timeToMetroByTransport, view, house);

    }
}
