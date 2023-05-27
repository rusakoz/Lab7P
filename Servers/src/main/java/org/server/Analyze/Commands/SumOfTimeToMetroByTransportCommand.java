package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду SumOfTimeToMetroByTransport
 */
@NoArgsConstructor
public class SumOfTimeToMetroByTransportCommand implements Command {
    CollectionManager cm;
    public SumOfTimeToMetroByTransportCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "добавить новый объект, если он больше наименьшего";
    }
    @Override
    public ObjectToSend execute(ObjectToSend objectToSend) {
        return new ObjectToSend("Команда успешно выполнена", cm.sumOfTimeToMetroByTransport());
    }
}
