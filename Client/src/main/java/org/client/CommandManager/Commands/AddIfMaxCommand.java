package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;

/**
 * Класс описывающий команду AddIfMax
 */
@NoArgsConstructor
public class AddIfMaxCommand implements Command {

    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "добавить новый объект, если он больше наибольшего";
    }
    @Override
    public void execute(String[] args) {

    }
}
