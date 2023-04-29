package org.example.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.example.CollectionManager;
import org.example.CommandManager.Command;
import org.example.InputOutput;

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
    public void execute(String[] args) {
        try {
            cm.executeScript(args[1]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            new InputOutput().Output("Не был введен аргумент, повторите попытку");
        }
    }
}
