package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.client.CommandManager.CreateObjectForCollection.AddObject;
import org.client.SocketClient;
import org.server.ObjectToSend;


/**
 * Класс описывающий команду CountLessThanHouse
 */
@NoArgsConstructor
public class CountLessThanHouseCommand implements Command {

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

    }
}
