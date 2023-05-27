package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.Analyze.InputOutput;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду Remove
 */
@NoArgsConstructor
public class RemoveByIdCommand implements Command {
    CollectionManager cm;

    public RemoveByIdCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg(){
        return " id";
    }
    @Override
    public String Descr(){
        return "удалить объект по id";
    }
    @Override
    public ObjectToSend execute(ObjectToSend objectToSend) {

        return new ObjectToSend("Команда успешно выполнена", cm.remove((Integer) objectToSend.getObject()));
    }

}
