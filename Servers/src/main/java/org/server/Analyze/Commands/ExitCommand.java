package org.server.Analyze.Commands;

import lombok.NoArgsConstructor;
import org.server.Analyze.CollectionManager;
import org.server.Analyze.Command;
import org.server.Analyze.InputOutput;
import org.server.ObjectToSend;

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
    public void execute(ObjectToSend objectToSend) {

    }
}
