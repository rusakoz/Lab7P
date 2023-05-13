package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.example.CollectionManager;

/**
 * Класс описывающий команду Save
 */
@NoArgsConstructor
public class SaveCommand implements Command {
    CollectionManager cm;
    public SaveCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "сохранить коллекцию";
    }

    @Override
    public void execute(String[] args) {
        cm.save();
    }
}
