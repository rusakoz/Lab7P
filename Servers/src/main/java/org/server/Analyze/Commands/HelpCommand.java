package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.Analyze.Invoker;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду Help
 */
@NoArgsConstructor
public class HelpCommand implements Command {
    CollectionManager cm;
    public HelpCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "помощь";
    }
    @Override
    public ObjectToSend execute(ObjectToSend objectToSend) {
        return new ObjectToSend("Команда успешно выполнена", null);
    }
}
