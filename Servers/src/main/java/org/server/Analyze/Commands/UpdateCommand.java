package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду update
 */
@NoArgsConstructor
public class UpdateCommand implements Command {
    CollectionManager cm;
    public UpdateCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg() {
        return " id";
    }

    @Override
    public String Descr() {
        return "обновить значения элемента коллекции по id";
    }

    @Override
    public ObjectToSend execute(ObjectToSend objectToSend) {
        return new ObjectToSend("Команда успешно выполнена", null);
    }
}
