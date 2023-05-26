package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду History
 */
@NoArgsConstructor
public class HistoryCommand implements Command {
    CollectionManager cm;

    public HistoryCommand(CollectionManager cm) {
        this.cm = cm;
    }

    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "история последних 15 команд";
    }

    @Override
    public ObjectToSend execute(ObjectToSend objectToSend) {
        return new ObjectToSend("Команда успешно выполнена", null);
    }
}