package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.client.InputOutput;
import org.client.SocketClient;
import org.server.ObjectToSend;

import java.io.IOException;


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
        try {
            ObjectToSend objectToSend = new ObjectToSend(args[0], Integer.parseInt(args[1]));
            new SocketClient().answer(objectToSend);
        }catch (NumberFormatException e){
            new InputOutput().Output("Введенный аргумент не является целым числом");
        }catch (ArrayIndexOutOfBoundsException e){
            new InputOutput().Output("Не был введен аргумент, повторите попытку");
        }
    }
}
