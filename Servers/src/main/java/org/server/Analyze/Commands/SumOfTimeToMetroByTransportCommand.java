package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
/**
 * Класс описывающий команду SumOfTimeToMetroByTransport
 */
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
