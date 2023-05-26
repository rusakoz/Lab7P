package org.client.CommandManager.CreateObjectForCollection;

import com.opencsv.exceptions.CsvException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.client.CommandManager.AntiRecursionScript;
import org.client.CommandManager.Command;
import org.client.CommandManager.Invoker;
import org.client.InputOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ExecuteScript {

    //private Map<String, Object> maps = new LinkedHashMap<>();
    private Map<List<String>, List<Object>> map = new HashMap<>();
    private List<String> listStr = new LinkedList<>();
    private List<Object> listObj = new LinkedList<>();
    public boolean executeScript(String fileName) {

        File file2 = new File(fileName);
        Invoker invoker = new Invoker();
        System.out.println(fileName);

        try {
            if (!file2.canRead() || !file2.canWrite()) throw new SecurityException();
        } catch (SecurityException e) {
            System.err.println("Файл недоступен для чтения");
            //map.clear();
            return false;
        }

        try {
            if (file2.length() == 0) throw new CsvException();
        } catch (CsvException e) {
            System.err.println("Файл пуст");
            return false;
        }


        try {
            Scanner sc = new Scanner(new FileInputStream(Path.of(fileName).toFile()), StandardCharsets.UTF_8);
            AntiRecursionScript.add("execute_script " + fileName);
            int f = AntiRecursionScript.getSet().size();

            while (sc.hasNext()) {
                String scan = null;
                try {
                    scan = sc.nextLine();
                    String[] tokens = scan.split(" ");
                    if (scan.isEmpty()) continue;
                    if (scan.matches("execute_script .*")) {

                        // !!! Не разрываемый блок !!!
                        AntiRecursionScript.add(scan);
                        if (f == AntiRecursionScript.getSet().size()) {
                            new InputOutput().Output("Был обнаружен зацикливающий скрипт, выполнение которого было пропущено");
                            return false;
                        } else f++;
                        // !!! Не разрываемый блок !!!

                        String[] str = scan.split(" ");

                        executeScript(str[1]);

                    }

                    else if(invoker.getCommands().get(tokens[0]) != null){
                        if(tokens[0].equals("add") || tokens[0].equals("add_if_max") || tokens[0].equals("add_if_min")){
                            //map.put(tokens[0], AddObject.newObjectFromScanner());
                            listStr.add(tokens[0]);
                            listObj.add(AddObject.newObjectFromScanner());
                        }else{

                            try {
                                Command command = invoker.getCommands().get(tokens[0]);
                                if(!command.Arg().equals("")) {
                                    //map.put(tokens[0], Integer.parseInt(tokens[1]));
                                    listStr.add(tokens[0]);
                                    listObj.add(Integer.parseInt(tokens[1]));
                                }else{
                                    //map.put(tokens[0], null);
                                    listStr.add(tokens[0]);
                                    listObj.add(null);
                                }
                            } catch (NumberFormatException e) {
                                new InputOutput().Output("Введенный аргумент команды не является целым числом " + scan);
                                return false;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.err.println("неправильно введена команда(нет аргумента)");
                                return false;
                            }
                        }
                    }else System.err.println(scan + "эта команда введена неверно");


                } catch (NullPointerException e) {
                    e.printStackTrace();
                    new InputOutput().Output("Команда: '" + scan + "' введена неверно выполнение скрипта было остановлено");
                    return false;
                } catch (NoSuchElementException e) {
                    new InputOutput().Output("не-не");

                }
            }
        } catch (FileNotFoundException e) {
            new InputOutput().Output("Файл отсутствует");
        }
        new InputOutput().Output("Выполнение скрипта окончено");
        map.put(listStr, listObj);
        return true;
    }

}
