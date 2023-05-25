package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.client.CommandManager.CreateObjectForCollection.AddObject;
import org.client.SocketClient;
import org.server.ObjectToSend;

import java.io.IOException;


/**
 * Класс описывающий команду Clear
 */
@NoArgsConstructor
public class ClearCommand implements Command {

    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "очистить коллекцию";
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException {
        ObjectToSend objectToSend = new ObjectToSend(args[0], null);
        new SocketClient().answer(objectToSend);
    }
}
