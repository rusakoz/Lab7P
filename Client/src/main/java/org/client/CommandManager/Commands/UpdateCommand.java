package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;


/**
 * Класс описывающий команду update
 */
@NoArgsConstructor
public class UpdateCommand implements Command {
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

    }
}
