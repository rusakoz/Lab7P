package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду Show
 */
@NoArgsConstructor
public class ShowCommand implements Command {
    CollectionManager cm;
    public ShowCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "посмотреть все элементы коллекции";
    }

    @Override
    public void execute(ObjectToSend objectToSend) {

    }
}
