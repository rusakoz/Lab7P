package org.client.CommandManager.Commands;

import org.client.CommandManager.Command;
import lombok.NoArgsConstructor;


/**
 * Класс описывающий команду Info
 */
@NoArgsConstructor
public class InfoCommand implements Command {

    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "информация о коллекции";
    }

    @Override
    public void execute(String[] args) {

    }
}