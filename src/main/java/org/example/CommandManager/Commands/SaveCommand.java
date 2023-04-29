package org.example.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.example.CollectionManager;
import org.example.CommandManager.Command;
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
