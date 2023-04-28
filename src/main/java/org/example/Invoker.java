package org.example;


import org.example.Commands.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Invoker {
    public void invoke(CollectionManager cm) {
        Map<String, Command> commands = new HashMap<>();
        commands.put("remove_by_id", new Remove(cm));
        commands.put("add", new AddCommand(cm));
        System.out.println("запущен инвокер"); // отладка

        //ScannerSysIn scanner = new ScannerSysIn();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            //Pattern pattern = Pattern.compile("remove_by_id [0-9]*");
            //Pattern pattern = Pattern.compile("remove_by_id [0-9]*");
            //System.out.println(a + "1");
            //if(Pattern.matches("remove_by_id [0-9]*", a)) {
                //try {
                    //System.out.println(a + "2");
                    //String[] tokens = a.split(" ");
                    //Command command = commands.get(tokens[0]);
                    //System.out.println(tokens[1]);
                    //command.execute(tokens);
                //} catch (NullPointerException e) {
                    //System.out.println(e.getMessage());
                    //System.out.println("Команда введена неверно, повторите попытку, список команд - 'help'");
                //}
            //}



            try {
                String a = sc.nextLine();
                System.out.println(a);
                String[] tokens = a.split(" ");
                Command command = commands.get(tokens[0]);
                command.execute(tokens);

            } catch (NullPointerException e) {
                //System.out.println(e.getMessage());
                System.out.println("Команда введена неверно, повторите попытку, список команд - 'help'");
            }catch (NoSuchElementException e){
                System.out.println("не-не");

            }





            //try {
                //String line = scanner.getScanner().next();
                //System.out.println(line);
                //String[] tokens = line.split(" ");
                //Command command = commands.get(tokens[0]);
                //command.execute();
            //}catch (NullPointerException e){
                //System.out.println(e.getMessage());
                //System.out.println("Команда введена неверно, повторите попытку, список команд - 'help'");
            //}

        }
    }
}
