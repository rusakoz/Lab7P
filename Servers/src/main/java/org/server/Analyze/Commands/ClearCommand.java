package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду Clear
 */
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
    public ObjectToSend execute(ObjectToSend objectToSend) {
        cm.clear();
        return new ObjectToSend("Команда успешно выполнена", null);
    }
}
