package org.example.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.example.CollectionManager;
import org.example.CommandManager.Command;
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
    public void execute(String[] args) {
        cm.addNewElement();
    }
}
