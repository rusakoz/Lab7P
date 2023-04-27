package org.example.Commands;

import org.example.CollectionManager;
import org.example.Command;

public class Remove implements Command {
    CollectionManager cm;
    public Remove(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args){
        try {
            cm.remove(Integer.parseInt(args[1]));
        }catch (NumberFormatException e){
            System.out.println("Введенный аргумент не является числом");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Не был введен аргумент, повторите попытку");
        }
    }

}
