package org.example.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.example.CollectionManager;
import org.example.CommandManager.Command;
/**
 * Класс описывающий команду Show
 */
@NoArgsConstructor
public class ShowCommand implements Command {
    CollectionManager cm;
    public ShowCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "посмотреть все элементы коллекции";
    }

    @Override
    public void execute(String[] args) {
        cm.show();
    }
}
