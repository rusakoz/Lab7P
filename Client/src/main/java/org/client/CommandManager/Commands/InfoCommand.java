package org.client.CommandManager.Commands;

import org.client.CommandManager.Command;
import lombok.NoArgsConstructor;
import org.client.SocketClient;
import org.server.ObjectToSend;

import java.io.IOException;


/**
 * Класс описывающий команду Info
 */
@NoArgsConstructor
public class InfoCommand implements Command {

    @Override
    public String Arg() {
        return "";
    }

    @Override
    public String Descr() {
        return "информация о коллекции";
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException {
        ObjectToSend objectToSend = new ObjectToSend(args[0], null);
        new SocketClient().answer(objectToSend);
    }
}
