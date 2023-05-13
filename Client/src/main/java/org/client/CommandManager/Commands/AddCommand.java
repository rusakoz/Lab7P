package org.client.CommandManager.Commands;

import org.client.CommandManager.Command;
import org.client.CommandManager.CreateObjectForCollection.AddObject;
import org.client.SocketClient;
import lombok.NoArgsConstructor;
import org.client.CommandManager.CreateObjectForCollection.ObjectToSend;

import java.io.IOException;

/**
 * Класс описывающий команду AddCommand
 */
@NoArgsConstructor
public class AddCommand implements Command {

    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "добавить новый объект";
    }
    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException {
        ObjectToSend objectToSend = new ObjectToSend(args[0], AddObject.newObjectFromScanner());
        new SocketClient().answer(objectToSend);
    }
}
