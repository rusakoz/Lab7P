package org.example.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.example.CollectionManager;
import org.example.CommandManager.Command;
/**
 * Класс описывающий команду GroupCountingByCreationDateCommand
 */
@NoArgsConstructor
public class GroupCountingByCreationDateCommand implements Command {
    CollectionManager cm;
    public GroupCountingByCreationDateCommand(CollectionManager cm){
        this.cm = cm;
    }
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
        cm.groupCountingByCreationDate();
    }
}
