package org.example.CommandManager;

import lombok.Getter;
import lombok.Setter;
import org.example.CollectionManager;
import org.example.CommandManager.Commands.*;

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
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("exit", new ExitCommand());
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
        commands.put("execute_script", new ExecuteScriptCommand(cm));
        commands.put("exit", new ExitCommand());
        commands.put("add_if_max", new AddIfMaxCommand(cm));
        commands.put("add_if_min", new AddIfMinCommand(cm));
        commands.put("history", new HistoryCommand(cm));
        commands.put("sum_of_time_to_metro_by_transport", new SumOfTimeToMetroByTransportCommand(cm));
        commands.put("group_counting_by_creation_date", new GroupCountingByCreationDateCommand(cm));

    }

    /**
     * Метод реализующий чтение команд из System.in
     */
    public void Invoke() {
        System.out.println("invoker start"); // отладка

        //ScannerSysIn scanner = new ScannerSysIn();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            try {
                String a = sc.nextLine(); // !!! nextLine !!!

                if(a.matches("execute_script .*")){
                    if(a.isEmpty()) continue;
                    String[] tokens = a.split(" ");
                    Command command = commands.get(tokens[0]);
                    command.execute(tokens);
                    AntiRecursionScript.clearSet();
                }else {
                    if(a.isEmpty()) continue;
                    String[] tokens = a.split(" ");
                    Command command = commands.get(tokens[0]);
                    command.execute(tokens);
                }

            } catch (NullPointerException e) {
                System.out.println("Команда введена неверно, повторите попытку, список команд - 'help'");
            } catch (NoSuchElementException e) {
                System.out.println("не-не");
            }
        }
    }
}
