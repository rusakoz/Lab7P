package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.client.CommandManager.CreateObjectForCollection.AddObject;
import org.client.SocketClient;
import org.server.ObjectToSend;

import java.io.IOException;

/**
 * Класс описывающий команду AddIfMax
 */
@NoArgsConstructor
public class AddIfMaxCommand implements Command {

    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "добавить новый объект, если он больше наибольшего";
    }
    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException {
        new HistoryCommand().add(args[0]);
        ObjectToSend objectToSend = new ObjectToSend(args[0], AddObject.newObjectFromScanner());
        new SocketClient().answer(objectToSend);
    }
}
