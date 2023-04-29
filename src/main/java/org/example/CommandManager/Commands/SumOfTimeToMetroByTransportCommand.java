package org.example.CommandManager.Commands;

import lombok.NoArgsConstructor;
import org.example.CollectionManager;
import org.example.CommandManager.Command;
@NoArgsConstructor
public class SumOfTimeToMetroByTransportCommand implements Command {
    CollectionManager cm;
    public SumOfTimeToMetroByTransportCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public String Arg(){
        return "";
    }
    @Override
    public String Descr(){
        return "добавить новый объект, если он больше наименьшего";
    }
    @Override
    public void execute(String[] args) {
        cm.sumOfTimeToMetroByTransport();
    }
}
