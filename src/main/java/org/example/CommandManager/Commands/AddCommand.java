package org.example.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.example.CollectionManager;
import org.example.CommandManager.Command;
@NoArgsConstructor
public class AddCommand implements Command {
    CollectionManager cm;
    public AddCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "добавить новый объект";
    }
    @Override
    public void execute(String[] args) {
        cm.add();
    }
}
