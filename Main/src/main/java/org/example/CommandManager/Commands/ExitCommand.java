package org.example.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.example.CommandManager.Command;
import org.example.InputOutput;
@NoArgsConstructor
public class ExitCommand implements Command {

    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "завершить работу программы(без сохранения)";
    }
    @Override
    public void execute(String[] args) {
        new InputOutput().OutputErr("Программа остановлена");
        System.exit(0);
    }
}
