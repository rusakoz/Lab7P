package org.client.CommandManager.Commands;

import org.client.CommandManager.Command;
import lombok.NoArgsConstructor;

/**
 * Класс описывающий команду Save
 */
@NoArgsConstructor
public class SaveCommand implements Command {

    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "сохранить коллекцию";
    }

    @Override
    public void execute(String[] args) {

    }
}
