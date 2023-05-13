package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;

/**
 * Класс описывающий команду Remove
 */
@NoArgsConstructor
public class RemoveByIdCommand implements Command {

    @Override
    public String Arg(){
        return " id";
    }
    @Override
    public String Descr(){
        return "удалить объект по id";
    }
    @Override
    public void execute(String[] args){

    }

}
