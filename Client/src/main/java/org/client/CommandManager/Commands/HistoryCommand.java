package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.example.CollectionManager;

/**
 * Класс описывающий команду History
 */
@NoArgsConstructor
public class HistoryCommand implements Command {
    CollectionManager cm;
    public HistoryCommand(CollectionManager cm){
        this.cm = cm;
    }
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
        cm.historyOutput();
    }
}
