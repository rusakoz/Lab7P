package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
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
