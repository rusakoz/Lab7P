package org.client.CommandManager.CreateObjectForCollection;

import com.opencsv.exceptions.CsvException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.client.CommandManager.AntiRecursionScript;
import org.client.CommandManager.Invoker;
import org.client.InputOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ExecuteScript {


    public static Map<String, Object> executeScript(String fileName) {

        File file2 = new File(fileName);
        Invoker invoker = new Invoker();

        try {
            if (!file2.canRead() || !file2.canWrite()) throw new SecurityException();
        } catch (SecurityException e) {
            System.err.println("Файл недоступен для чтения");
            return null;
        }

        try {
            if (file2.length() == 0) throw new CsvException();
        } catch (CsvException e) {
            System.err.println("Файл пуст");
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Scanner sc = new Scanner(new FileInputStream(Path.of(fileName).toFile()), StandardCharsets.UTF_8);
            AntiRecursionScript.add("execute_script " + fileName);
            int f = AntiRecursionScript.getSet().size();

            while (sc.hasNext()) {
                String scan = null;
                try {
                    scan = sc.nextLine();
                    if (scan.matches("execute_script .*")) {
                        AntiRecursionScript.add(scan);

                        if (f == AntiRecursionScript.getSet().size()) {
                            new InputOutput().Output("Был обнаружен зацикливающий скрипт, выполнение которого было пропущено");
                            return null;
                        } else f++;
                    }
                    if (scan.isEmpty()) continue;

                    String[] tokens = scan.split(" ");

                    if(invoker.getCommands().get(tokens[0]) != null){
                        if(tokens[0].equals("add") || tokens[0].equals("add_if_max") || tokens[0].equals("add_if_min")){
                            map.put(tokens[0], AddObject.newObjectFromScanner());
                        }
                        try{
                            map.put(tokens[0], Integer.parseInt(tokens[1]));
                        }catch (NumberFormatException e) {
                            new InputOutput().Output("Введенный аргумент команды не является целым числом" + scan);
                            return null;
                        }
                    }else System.err.println(scan + "эта команда введена неверно");


                } catch (NullPointerException e) {
                    new InputOutput().Output("Команда: '" + scan + "' введена неверно выполнение скрипта было остановлено");
                    return null;
                } catch (NoSuchElementException e) {
                    new InputOutput().Output("не-не");

                }
            }
        } catch (FileNotFoundException e) {
            new InputOutput().Output("Файл отсутствует");
        }
        new InputOutput().Output("Выполнение скрипта окончено");
        return map;
    }

}
