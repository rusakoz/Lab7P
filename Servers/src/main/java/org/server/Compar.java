package org.server;

import java.util.Comparator;

public class Compar implements Comparator<Flat> {
    @Override
    public int compare(Flat flat1, Flat flat2){
        return flat1.getName().compareTo(flat2.getName());
    }
}
