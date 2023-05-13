package org.client.CommandManager.Commands;

import org.client.CommandManager.Command;
import lombok.NoArgsConstructor;


/**
 * Класс описывающий команду History
 */
@NoArgsConstructor
public class HistoryCommand implements Command {
    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "история последних 15 команд";
    }
    @Override
    public void execute(String[] args) {

    }
}
