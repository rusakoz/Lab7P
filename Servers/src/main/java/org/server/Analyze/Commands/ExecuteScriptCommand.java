package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.Analyze.InputOutput;
import org.server.ObjectToSend;

/**
 * Класс описывающий команду ExecuteScript
 */
@NoArgsConstructor
public class ExecuteScriptCommand implements Command {
    CollectionManager cm;
    public ExecuteScriptCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg() {
        return " путь_до_файла";
    }

    @Override
    public String Descr() {
        return "выполнить скрипт";
    }

    @Override
    public void execute(ObjectToSend objectToSend) {
        // что-то будет TODO

    }
}
