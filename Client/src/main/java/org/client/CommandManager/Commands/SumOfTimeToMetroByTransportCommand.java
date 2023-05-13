package org.client.CommandManager.Commands;

import org.client.CommandManager.Command;
import lombok.NoArgsConstructor;

/**
 * Класс описывающий команду SumOfTimeToMetroByTransport
 */
@NoArgsConstructor
public class SumOfTimeToMetroByTransportCommand implements Command {

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
