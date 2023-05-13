package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;

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

    }
}
