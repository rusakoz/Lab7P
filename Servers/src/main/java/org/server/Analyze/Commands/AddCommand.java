package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.Flat;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду AddCommand
 */
@NoArgsConstructor
public class AddCommand implements Command {
    CollectionManager cm;
    public AddCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "добавить новый объект";
    }
    @Override
    public ObjectToSend execute(ObjectToSend objectToSend) {
        return new ObjectToSend("Команда успешно выполнена", cm.add((Flat) objectToSend.getObject()));
    }
}
