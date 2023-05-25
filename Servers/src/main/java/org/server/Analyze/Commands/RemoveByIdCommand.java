package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.Analyze.InputOutput;

/**
 * Класс описывающий команду Remove
 */
@NoArgsConstructor
public class RemoveByIdCommand implements Command {
    CollectionManager cm;

    public RemoveByIdCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg(){
        return " id";
    }
    @Override
    public String Descr(){
        return "удалить объект по id";
    }
    @Override
    public void execute(String[] args){
        try {
            cm.remove(Integer.parseInt(args[1]));
        }catch (NumberFormatException e){
            new InputOutput().Output("Введенный аргумент не является числом");
        }catch (ArrayIndexOutOfBoundsException e){
            new InputOutput().Output("Не был введен аргумент, повторите попытку");
        }
    }

}
