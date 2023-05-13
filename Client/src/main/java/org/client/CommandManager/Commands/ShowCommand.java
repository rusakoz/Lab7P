package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;


/**
 * Класс описывающий команду Show
 */
@NoArgsConstructor
public class ShowCommand implements Command {

    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "посмотреть все элементы коллекции";
    }

    @Override
    public void execute(String[] args) {

    }
}
