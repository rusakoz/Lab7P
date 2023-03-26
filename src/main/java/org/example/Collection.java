package org.example;

import java.util.LinkedHashSet;

public class Collection {
    LinkedHashSet<Flat> collection = new LinkedHashSet<>();
    public void collectionFill(){

        House house = new House("das", 312, 31);
        Coordinates coordinates = new Coordinates(535, 3.3F);
        String name = null;
        int r = 234;
        collection.add(new Flat(r, name, coordinates, null, 0L, 321, 0.1F, null, house));
        for (Flat a : collection) {
            System.out.print(a.getName() + " " + a.getNumberOfRooms());

            new Validators().validatorFlat(a);
            new Validators().validatorCoordinates(a.getCoordinates());
            new Validators().validatorHouse(a.getHouse());
        }

    }



}
