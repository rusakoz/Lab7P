package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;


/**
 * Класс описывающий команду Clear
 */
@NoArgsConstructor
public class ClearCommand implements Command {

    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "очистить коллекцию";
    }

    @Override
    public void execute(String[] args) {

    }
}
