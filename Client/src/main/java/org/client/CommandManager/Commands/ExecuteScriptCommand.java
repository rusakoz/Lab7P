package org.client.CommandManager.Commands;

import org.client.CommandManager.AntiRecursionScript;
import org.client.CommandManager.Command;
import lombok.NoArgsConstructor;
import org.client.CommandManager.CreateObjectForCollection.ExecuteScript;
import org.client.SocketClient;
import org.server.ObjectToSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Класс описывающий команду ExecuteScript
 */
@NoArgsConstructor
public class ExecuteScriptCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteScriptCommand.class);

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
        new HistoryCommand().add(args[0]);

        try {
            if (args[1] == null) throw new ArrayIndexOutOfBoundsException();
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("неправильно введена команда(нет аргумента)");
            return;
        }
        ExecuteScript executeScript = new ExecuteScript();

        if(executeScript.executeScript(args[1])){
            ObjectToSend objectToSend = new ObjectToSend(args[0], executeScript.getMap());
            logger.info("Скрипт отправлен на сервер");
            new SocketClient().answer(objectToSend);
        }else{
            System.out.println("Скрипт не был отправлен на сервер");
        }
        AntiRecursionScript.clearSet();

    }
}
