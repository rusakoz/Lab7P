package org.client.CommandManager.Commands;

import org.client.CommandManager.Command;
import lombok.NoArgsConstructor;
import org.client.InputOutput;
import org.client.SocketClient;
import org.server.ObjectToSend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс описывающий команду History
 */
@NoArgsConstructor
public class HistoryCommand implements Command {
    private static final List<String> history = new ArrayList<>();
    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "история последних 15 команд";
    }
    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException {
        history.add(args[0]);
        history.forEach(a -> new InputOutput().Output(a));
    }
    public void add(String str){
        history.add(str);
    }
    public void clear(){
        history.clear();
    }
}
