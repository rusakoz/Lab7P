package org.client.CommandManager.Commands;

import org.client.CommandManager.Command;
import lombok.NoArgsConstructor;
import org.client.CommandManager.CreateObjectForCollection.ExecuteScript;
import org.client.SocketClient;
import org.server.ObjectToSend;

import java.io.IOException;

/**
 * Класс описывающий команду ExecuteScript
 */
@NoArgsConstructor
public class ExecuteScriptCommand implements Command {

    @Override
    public String Arg() {
        return " путь_до_файла";
    }

    @Override
    public String Descr() {
        return "выполнить скрипт";
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException {
        ObjectToSend objectToSend = new ObjectToSend(args[0], ExecuteScript.executeScript(args[1]));
        new SocketClient().answer(objectToSend);
    }
}
