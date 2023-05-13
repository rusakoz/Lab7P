package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.example.CollectionManager;

/**
 * Класс описывающий команду Info
 */
@NoArgsConstructor
public class InfoCommand implements Command {
    CollectionManager cm;
    public InfoCommand(CollectionManager cm){
        this.cm = cm;
    }
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
        cm.info();
    }
}
