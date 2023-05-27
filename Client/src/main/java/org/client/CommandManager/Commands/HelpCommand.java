package org.client.CommandManager.Commands;

import org.client.CommandManager.Invoker;
import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.client.SocketClient;
import org.server.ObjectToSend;

import java.io.IOException;


/**
 * Класс описывающий команду Help
 */
@NoArgsConstructor
public class HelpCommand implements Command {
    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "помощь";
    }
    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException {
        new HistoryCommand().add(args[0]);
        ObjectToSend objectToSend = new ObjectToSend(args[0], null);
        new SocketClient().answer(objectToSend);
    }
}
