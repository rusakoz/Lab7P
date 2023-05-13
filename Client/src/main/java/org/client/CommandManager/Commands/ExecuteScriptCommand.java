package org.client.CommandManager.Commands;

import org.client.CommandManager.Command;
import lombok.NoArgsConstructor;

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
    public void execute(String[] args) {

    }
}
