package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.example.CollectionManager;

/**
 * Класс описывающий команду update
 */
@NoArgsConstructor
public class UpdateCommand implements Command {
    CollectionManager cm;
    public UpdateCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg() {
        return " id";
    }

    @Override
    public String Descr() {
        return "обновить значения элемента коллекции по id";
    }

    @Override
    public void execute(String[] args) {
        try {
            cm.update(Integer.parseInt(args[1]));
        }catch (NumberFormatException e){
            System.out.println("Введенный аргумент не является числом");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Не был введен аргумент, повторите попытку");
        }
    }
}
