package org.server.Analyze;

import lombok.Getter;
import lombok.Setter;
import org.server.Analyze.Commands.*;
import org.server.ObjectToSend;


import java.util.*;
/**
 * Класс описывающий чтение из System.in
 */
@Getter
@Setter
public class Invoker {
    Map<String, Command> commands;

    /**
     * Конструктор класса
     */
    public Invoker() {
        this.commands = new LinkedHashMap<>();
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("update", new UpdateCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("add", new AddCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("add_if_max", new AddIfMaxCommand());
        commands.put("add_if_min", new AddIfMinCommand());
        commands.put("history", new HistoryCommand());
        commands.put("sum_of_time_to_metro_by_transport", new SumOfTimeToMetroByTransportCommand());
        commands.put("group_counting_by_creation_date", new GroupCountingByCreationDateCommand());

    }

    /**
     * Конструктор класса с параметром
     * @param cm параметр объекта класса CollectionManager
     */
    public Invoker(CollectionManager cm) {
        this.commands = new LinkedHashMap<>();
        commands.put("help", new HelpCommand(cm));
        commands.put("info", new InfoCommand(cm));
        commands.put("show", new ShowCommand(cm));
        commands.put("update", new UpdateCommand(cm));
        commands.put("remove_by_id", new RemoveByIdCommand(cm));
        commands.put("add", new AddCommand(cm));
        commands.put("clear", new ClearCommand(cm));
        commands.put("save", new SaveCommand(cm));
        commands.put("add_if_max", new AddIfMaxCommand(cm));
        commands.put("add_if_min", new AddIfMinCommand(cm));
        commands.put("history", new HistoryCommand(cm));
        commands.put("sum_of_time_to_metro_by_transport", new SumOfTimeToMetroByTransportCommand(cm));
        commands.put("group_counting_by_creation_date", new GroupCountingByCreationDateCommand(cm));

    }

    /**
     * Метод реализующий чтение команд из System.in
     */
    public ObjectToSend Invoke(ObjectToSend objectToSend) {

        if(objectToSend.getObject() != null) {
            StringBuilder str = new StringBuilder();
            if (objectToSend.getObject() instanceof Map<?,?>) {
                Map<List<String>, List<Object>> map = (Map<List<String>, List<Object>>) objectToSend.getObject();
                List<String> listStr = new LinkedList<>();
                List<Object> listObj = new LinkedList<>();
                for (Map.Entry<List<String>, List<Object>> entry : map.entrySet()) {
                    listStr = entry.getKey();
                    System.out.println(entry.getKey());
                    listObj = entry.getValue();
                    System.out.println(entry.getValue());
                }

                for (int i = 0; i < listStr.size(); i++) {
                    System.out.println(listStr.get(i));

                        System.out.println(listObj.get(i));
                        ObjectToSend obj = new ObjectToSend(listStr.get(i), listObj.get(i));
                        Command command = commands.get(obj.getNameCommand());
                        str.append("\n").append(command.execute(obj).getObject());


                }
//                map.forEach((key, value) -> {
//                    ObjectToSend obj = new ObjectToSend(key, value);
//                    Command command = commands.get(obj.getNameCommand());
//                    str.append("\n").append(command.execute(obj).getObject());
//                });
                return new ObjectToSend("Скрипт успешно выполнен", str);
            }
            return new ObjectToSend("Скрипт успешно выполнен", str);
        }else {
            Command command = commands.get(objectToSend.getNameCommand());
            return command.execute(objectToSend);
        }

    }
}
