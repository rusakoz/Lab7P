package org.client.CommandManager;

import org.client.CommandManager.Commands.*;
import lombok.Getter;
import lombok.Setter;


import java.io.IOException;
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
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("exit", new ExitCommand());
        commands.put("add_if_max", new AddIfMaxCommand());
        commands.put("add_if_min", new AddIfMinCommand());
        commands.put("history", new HistoryCommand());
        commands.put("sum_of_time_to_metro_by_transport", new SumOfTimeToMetroByTransportCommand());
        commands.put("group_counting_by_creation_date", new GroupCountingByCreationDateCommand());

    }


    /**
     * Метод реализующий чтение команд из System.in
     */
    public void Invoke() throws IOException, ClassNotFoundException {

        System.out.println("invoker start"); // отладка


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
                } else {
                    if(a.isEmpty()) continue;
                    String[] tokens = a.split(" ");
                    Command command = commands.get(tokens[0]);
                    command.execute(tokens);
                }

            } catch (NullPointerException e) {
                System.out.println("Команда введена неверно, повторите попытку, список команд - 'help'");
                e.printStackTrace();
            } catch (NoSuchElementException e) {
                System.out.println("не-не");
            }
        }
    }
}
