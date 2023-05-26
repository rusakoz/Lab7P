package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду Info
 */
@NoArgsConstructor
public class InfoCommand implements Command {
    CollectionManager cm;
    public InfoCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "информация о коллекции";
    }

    @Override
    public ObjectToSend execute(ObjectToSend objectToSend) {
        return new ObjectToSend("Команда успешно выполнена", cm.info());
    }
}
