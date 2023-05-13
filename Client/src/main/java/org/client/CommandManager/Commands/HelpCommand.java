package org.client.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.client.CommandManager.Command;
import org.client.CommandManager.Invoker;
import org.example.CollectionManager;

/**
 * Класс описывающий команду Help
 */
@NoArgsConstructor
public class HelpCommand implements Command {
    CollectionManager cm;
    public HelpCommand(CollectionManager cm){
        this.cm = cm;
    }
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
        cm.help();
        Invoker invoker = new Invoker();
        invoker.getCommands().forEach((name, Command) -> System.out.println(name + Command.Arg() +" - " + Command.Descr()));
    }
}
