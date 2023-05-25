package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.Flat;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду AddIfMax
 */
@NoArgsConstructor
public class AddIfMaxCommand implements Command {
    CollectionManager cm;
    public AddIfMaxCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "добавить новый объект, если он больше наибольшего";
    }
    @Override
    public void execute(ObjectToSend objectToSend) {
        cm.addIfMax((Flat) objectToSend.getObject());
    }
}
