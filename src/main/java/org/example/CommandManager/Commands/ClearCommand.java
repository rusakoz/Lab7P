package org.example.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.example.CollectionManager;
import org.example.CommandManager.Command;
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
