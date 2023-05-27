package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду Save
 */
@NoArgsConstructor
public class SaveCommand implements Command {
    CollectionManager cm;
    public SaveCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "сохранить коллекцию";
    }

    @Override
    public ObjectToSend execute(ObjectToSend objectToSend) {

        return new ObjectToSend("Команда успешно выполнена", null);
    }
}
