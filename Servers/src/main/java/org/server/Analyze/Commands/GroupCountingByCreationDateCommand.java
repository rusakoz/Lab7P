package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.ObjectToSend;

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
    public ObjectToSend execute(ObjectToSend objectToSend) {
        return new ObjectToSend("Команда успешно выполнена", cm.groupCountingByCreationDate());
    }
}
