package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.example.CollectionManager;

/**
 * Класс описывающий команду Clear
 */
@NoArgsConstructor
public class ClearCommand implements Command {

    CollectionManager cm;
    public ClearCommand(CollectionManager cm){
        this.cm = cm;
    }
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
        cm.clear();
    }
}
