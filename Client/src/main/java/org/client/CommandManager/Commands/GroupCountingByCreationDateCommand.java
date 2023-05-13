package org.client.CommandManager.Commands;

import org.client.CommandManager.Command;
import lombok.NoArgsConstructor;


/**
 * Класс описывающий команду GroupCountingByCreationDateCommand
 */
@NoArgsConstructor
public class GroupCountingByCreationDateCommand implements Command {

    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "сгруппировать элементы по дате создания";
    }
    @Override
    public void execute(String[] args) {

    }
}
