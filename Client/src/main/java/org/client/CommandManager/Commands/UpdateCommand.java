package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.client.CommandManager.CreateObjectForCollection.AddObject;
import org.client.InputOutput;
import org.client.SocketClient;
import org.server.ObjectToSend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Класс описывающий команду update
 */
@NoArgsConstructor
public class UpdateCommand implements Command {
    @Override
    public String Arg() {
        return " id";
    }

    @Override
    public String Descr() {
        return "обновить значения элемента коллекции по id";
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException {
        new HistoryCommand().add(args[0]);
        try {
            Map<Integer, Object> map = new HashMap<>();
            map.put(Integer.parseInt(args[1]), AddObject.newObjectFromScanner());
            ObjectToSend objectToSend = new ObjectToSend(args[0], map);
            new SocketClient().answer(objectToSend);
        }catch (NumberFormatException e){
            new InputOutput().Output("Введенный аргумент не является целым числом");
        }catch (ArrayIndexOutOfBoundsException e){
            new InputOutput().Output("Не был введен аргумент, повторите попытку");
        }
    }
}
