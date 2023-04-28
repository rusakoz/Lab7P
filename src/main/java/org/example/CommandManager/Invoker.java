package org.example.CommandManager;


import lombok.Getter;
import lombok.Setter;
import org.example.CollectionManager;
import org.example.CommandManager.Commands.*;

import java.util.*;

@Getter
@Setter
public class Invoker {
    Map<String, Command> commands;

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
    }

    public Invoker(CollectionManager cm) {
        this.commands = new LinkedHashMap<>();
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(cm));
        commands.put("show", new ShowCommand(cm));
        commands.put("update", new UpdateCommand(cm));
        commands.put("remove_by_id", new RemoveByIdCommand(cm));
        commands.put("add", new AddCommand(cm));
        commands.put("clear", new ClearCommand(cm));
        commands.put("save", new SaveCommand(cm));
        commands.put("execute_script", new ExecuteScriptCommand(cm));
    }


    public void Invoke() {
        System.out.println("запущен инвокер"); // отладка

        //ScannerSysIn scanner = new ScannerSysIn();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            try {
                String a = sc.nextLine(); // !!! nextLine !!!
                String[] tokens = a.split(" ");
                Command command = commands.get(tokens[0]);
                command.execute(tokens);

            } catch (NullPointerException e) {
                System.out.println("Команда введена неверно, повторите попытку, список команд - 'help'");
            } catch (NoSuchElementException e) {
                System.out.println("не-не");

            }
        }
    }
}
