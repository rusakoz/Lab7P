package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.ObjectToSend;

import java.util.List;
import java.util.Map;

/**
 * Класс описывающий команду update
 */
@NoArgsConstructor
public class UpdateCommand implements Command {
    CollectionManager cm;
    public UpdateCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg() {
        return " id";
    }

    @Override
    public String Descr() {
        return "обновить значения элемента коллекции по id";
    }

    @Override
    public ObjectToSend execute(ObjectToSend objectToSend) {
        Map<Integer, Object> map = (Map<Integer, Object>) objectToSend.getObject();
        Integer integer = null;
        Object obj = null;
        for (Map.Entry<Integer, Object> entry : map.entrySet()) {
            integer = entry.getKey();
            obj = entry.getValue();
        }
        return new ObjectToSend("Команда успешно выполнена", cm.update(integer, obj));
    }
}
