package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;


/**
 * Класс описывающий команду AddIfMin
 */
@NoArgsConstructor
public class AddIfMinCommand implements Command {


    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "добавить новый объект, если он больше наименьшего";
    }
    @Override
    public void execute(String[] args) {

    }
}
