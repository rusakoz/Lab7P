package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.Flat;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду AddIfMin
 */
@NoArgsConstructor
public class AddIfMinCommand implements Command {
    CollectionManager cm;
    public AddIfMinCommand(CollectionManager cm){
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
    public void execute(ObjectToSend objectToSend) {
        cm.addIfMin((Flat) objectToSend.getObject());
    }
}
