package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.Flat;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду CountLessThanHouse
 */
@NoArgsConstructor
public class CountLessThanHouseCommand implements Command {
    CollectionManager cm;
    public CountLessThanHouseCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "количество элементов, значение поля house которых меньше заданного";
    }
    @Override
    public ObjectToSend execute(ObjectToSend objectToSend) {
        //cm.add((Flat) objectToSend.getObject());
        return new ObjectToSend("Команда в разработке", null);
    }
}
