package org.example.CommandManager.Commands;

import org.example.CommandManager.Command;
import org.example.CommandManager.Invoker;

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
    public void execute(String[] args){
        Invoker invoker = new Invoker();
        invoker.getCommands().forEach((name, Command) -> System.out.println(name + Command.Arg() +" - " + Command.Descr()));
    }
}
