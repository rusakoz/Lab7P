package org.example;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.CommandManager.Command;
import org.example.CommandManager.Invoker;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@EqualsAndHashCode
@Setter
@Getter
public class CollectionManager {
    private Date date;
    private LinkedHashSet<Flat> collection;
    private List<Flat> beans = null;
    private File file;

    private final Dotenv dotenv = Dotenv.load();
    private final String path = dotenv.get("HELLO");
    private final String path2 = dotenv.get("HELLO2");

    public CollectionManager(){
        this.date = new Date();
        this.collection = new LinkedHashSet<>();
    }

    public void Read() {

        file = new File(path);
        try {
            if(!file.canRead() || !file.canWrite()) throw new SecurityException();
        } catch (SecurityException e) {
            System.err.println("Файл недоступен для чтения");
            return;
        }

        try {
            if(file.length() == 0) throw new CsvException();
        } catch (CsvException e) {
            System.err.println("Файл пуст");
            return;
        }

        try {
             beans = new CsvToBeanBuilder<Flat>(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))
                    .withType(Flat.class)
                    .withSeparator(',')
                     .withThrowExceptions(false) //если кол-во элементов строки не совпадает с кол-вом столбцов, то не выскочит CsvRequiredFieldEmptyException
                    .build()
                     .parse();
            //final List<Flat> users = beans.parse();//2
            //users.forEach((user) -> {
                //logger.info("Parsed data:" + user.toString());
            //});

            //beans.getCapturedExceptions().forEach((exception) -> { //3
                //logger.error("Inconsistent data:" +
                        //String.join("", exception.getLine()), exception);//4
            //});

        } catch (FileNotFoundException e) {
            System.err.println("Файл отсутствует, создайте файл в папке Lab5P с именем test.csv");
        }


        collection = new LinkedHashSet<>(beans);


        int count = 1;
        Set<Integer> set = new HashSet<>();
        for (Flat a: beans){
            if (beans.size() == 0) break;
            int id = a.getId();
            set.add(a.getId());
            System.out.println(set.size());
            System.out.println(count + " c");
            while (count > set.size()) {
                a.setId(++id);
                set.add(id);
            }
            count++;
        }
        set.clear();

        for (Flat a: beans) {
            new Validators().validatorFlat(a, "-----------------" + "\n" + "Ошибка ввода данных под id: " + a.getId() + "\n" + "-----------------");
        }

        beans.clear();

        for (Flat a : collection) {
            System.out.print( a.getId() + " | " + a.getName() + " | " + a.getCoordinates().getX() + " | " + a.getCoordinates().getY() + " | " +
                     a.getCreationDate() + " | " + a.getArea() + " | " + a.getNumberOfRooms() + " | " + a.getTimeToMetroByTransport() + " | " +
                     a.getView() + " | " + a.getHouse().getName() + " | " + a.getHouse().getYear() + " | " + a.getHouse().getNumberOfFloors()
                            +"\n" );
        }
    }

    public void Write() {

        file = new File(path2);

        try {
            if(!file.canRead() || !file.canWrite()) throw new SecurityException();
        } catch (SecurityException e) {
            System.err.println("Файл недоступен для записи");
        }

        if(!file.isFile()) {
            try {
                Files.createFile(Path.of("test2.csv"));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        beans = new ArrayList<>(getCollection());

        try (PrintWriter writer = new PrintWriter(path2, StandardCharsets.UTF_8)){
            StatefulBeanToCsv<Flat> beanToCsv = new StatefulBeanToCsvBuilder<Flat>(writer)
                    .withSeparator(',')
                    .build();
            beanToCsv.write(beans);
            beans.clear();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.err.println("При записи коллекции в файл произошла ошибка");
        }
    }

    public void add(){

        Set<Integer> set = new HashSet<>();
        int id;
        for (Flat a: collection){
            set.add(a.getId());
        }
        int size = set.size();
        if (collection.size() + 1 < Integer.MAX_VALUE) {
            id = collection.size() + 1;
            set.add(id);
        }else {
            id = 0;
            set.add(id);
        }
        while(size == set.size()){
            if(id+1 < Integer.MAX_VALUE) {
                set.add(++id);
            }else {
                id = 0;
                set.add(id);
            }
        }
        set.clear();

        addNewElementFromScanner(id);
        new InputOutput().Output("Новый элемент был успешно добавлен");
    }

    public void addNewElementFromScanner(int id){
        ScannerSysIn scanner = new ScannerSysIn();
        Date date = new Date();

        String name = null;
        //Coordinates
        Coordinates coordinates;
        Integer X;
        float Y;
        //Coordinates
        Long area = (long) -1;
        int numberOfRooms = -1;
        float timeToMetroByTransport = -1;
        View view = null;
        //House
        House house = null;
        String nameHouse;
        long yearHouse = -1;
        long numberOfFloorsHouse = -1;
        //House

        new InputOutput().Output("Введите имя");
        if (scanner.getScanner().hasNext()) {
            while (!scanner.getScanner().hasNext("([А-Я][а-я]+)|([A-Z][a-z]+)")) {
                new InputOutput().Output("Неправильно введено имя, проверьте формат: Aaaaaaa");
                scanner.getScanner().nextLine();
            }
            name = scanner.getScanner().nextLine();
            new InputOutput().Output("Success");
        }
        new InputOutput().Output("Введите координату X, пример: 12345");
        while (!scanner.getScanner().hasNextInt()) {
            new InputOutput().Output("Неправильно введена координата X, проверьте формат: 12345");
            scanner.getScanner().next();
        }
        X = scanner.getScanner().nextInt();
        new InputOutput().Output("Success");


        new InputOutput().Output("Введите кординату Y, пример: 12345,1");
        while (!scanner.getScanner().hasNextFloat()) {
            new InputOutput().Output("Неправильно введена координата Y, проверьте формат: 12345,1");
            scanner.getScanner().next();
        }
        Y = scanner.getScanner().nextFloat();
        new InputOutput().Output("Success");
        coordinates = new Coordinates(X, Y);


        new InputOutput().Output("Введите область(>нуля!), пример: 12345");
        while (!(area > 0)){
            while (!scanner.getScanner().hasNextLong()){
                new InputOutput().Output("Область введена неправильно, проверьте формат: 12345(>нуля!)");
                scanner.getScanner().next();
            }
            area = scanner.getScanner().nextLong();
            if(area <= 0){
                new InputOutput().Output("Область введена неправильно, проверьте формат: 12345(>нуля!)");
            }else new InputOutput().Output("Success");
        }


        new InputOutput().Output("Введите кол-во комнат, пример: 12345(>нуля!)");
        while (!(numberOfRooms > 0)){
            while (!scanner.getScanner().hasNextInt()){
                new InputOutput().Output("Кол-во комнат введено неправильно, проверьте формат: 12345(>нуля!)");
                scanner.getScanner().next();
            }
            numberOfRooms = scanner.getScanner().nextInt();
            if(numberOfRooms <= 0){
                new InputOutput().Output("Кол-во комнат введено неправильно, проверьте формат: 12345(>нуля!)");
            }else new InputOutput().Output("Success");
        }


        new InputOutput().Output("Введите время до метро, пример: 12345,1(>нуля!");
        while (!(timeToMetroByTransport > 0)){
            while (!scanner.getScanner().hasNextFloat()){
                new InputOutput().Output("Время до метро введено неправильно, проверьте формат: 12345,1(>нуля!)");
                scanner.getScanner().next();
            }
            timeToMetroByTransport = scanner.getScanner().nextFloat();
            if(timeToMetroByTransport <= 0){
                new InputOutput().Output("Время до метро введено неправильно, проверьте формат: 12345,1(>нуля!)");
            }else new InputOutput().Output("Success");
        }


        new InputOutput().Output("Введите вид, список доступных видов: YARD, PARK, NORMAL");
        if (scanner.getScanner().hasNext()) {
            String str = scanner.getScanner().nextLine();
            while (view == null) {
                switch (str) {
                    case "YARD" -> {
                        view = View.YARD;
                        new InputOutput().Output("Success");
                    }
                    case "PARK" -> {
                        view = View.PARK;
                        new InputOutput().Output("Success");
                    }
                    case "NORMAL" -> {
                        view = View.NORMAL;
                        new InputOutput().Output("Success");
                    }
                    default -> {
                        if (scanner.getScanner().hasNext())new InputOutput().Output("Вид введен неверно, проверьте формат: YARD, PARK, NORMAL");
                        str = scanner.getScanner().nextLine();
                    }
                }
            }
        }

        new InputOutput().Output("Введите название дома, пример: Ааааа");
        while (!scanner.getScanner().hasNext("([А-Я][а-я]+)|([A-Z][a-z]+)")) {
            new InputOutput().Output("Неправильно введено название дома, проверьте формат: Aaaaa");
            scanner.getScanner().nextLine();
        }
        nameHouse = scanner.getScanner().nextLine();
        new InputOutput().Output("Success");


        new InputOutput().Output("Введите возраст дома, пример: 12345(от 1 до 578)");
        while (!(yearHouse > 0 & yearHouse <= 578)){
            while (!scanner.getScanner().hasNextLong()){
                new InputOutput().Output("Возраст дома введен неправильно, проверьте формат: 12345(от 1 до 578!)");
                scanner.getScanner().next();
            }
            yearHouse = scanner.getScanner().nextLong();
            if(yearHouse <= 0 | yearHouse > 578){
                new InputOutput().Output("Взраст дома введен неправильно, проверьте формат: 12345(от 1 до 578!)");
            }else new InputOutput().Output("Success");
        }


        new InputOutput().Output("Введите кол-во дверей дома, пример: 12345(>нуля!)");
        while (!(numberOfFloorsHouse > 0)){
            while (!scanner.getScanner().hasNextLong()){
                new InputOutput().Output("Кол-во дверей дома введено неправильно, проверьте формат: 12345(>нуля!)");
                scanner.getScanner().next();
            }
            numberOfFloorsHouse = scanner.getScanner().nextLong();
            if(numberOfFloorsHouse <= 0){
                new InputOutput().Output("Кол-во дверей дома введено неправильно, проверьте формат: 12345(>нуля!)");
            }else new InputOutput().Output("Success");
        }

        house = new House(nameHouse, yearHouse, numberOfFloorsHouse);
        Flat f = new Flat(id, name, coordinates, date, area, numberOfRooms, timeToMetroByTransport, view, house);
        collection.add(f);
    }

    public void remove(int id){
        if (!collection.removeIf(a -> a.getId() == id)) {
            new InputOutput().Output("Элемента под id = " + id + " нет в коллекции");
        }else new InputOutput().Output("Элемент коллекции под id = " + id + " был успешно удален");
    }

    public void info(){
        new InputOutput().Output("Тип коллекции - 'LinkedHashSet' | Дата инициализации - " + date + " | Кол-во элементов - " + collection.size());
    }

    public void show(){
        collection.forEach(a -> new InputOutput().Output("id: " + a.getId() + " | name: " + a.getName() +
                " | кол-во комнат: " + a.getNumberOfRooms() + " | время до метро: " + a.getTimeToMetroByTransport() +
                " | область: " + a.getArea() + " | дата создания элемента: " + a.getCreationDate() +
                " | координата X: " + a.getCoordinates().getX() + " | координата Y: " + a.getCoordinates().getY() +
                " | название дома: " + a.getHouse().getName() + " | возраст дома: " + a.getHouse().getYear() +
                " | кол-во этажей: " + a.getHouse().getNumberOfFloors() + " | вид: " + a.getView()));
    }

    public void update(int id){
        if(collection.removeIf(a -> a.getId() == id)){
            addNewElementFromScanner(id);
            new InputOutput().Output("Элемент успешно обновлен");
        }else new InputOutput().Output("Элемента под id = " + id + " нет в коллекции");

    }

    public void clear(){
        collection.clear();
        new InputOutput().Output("Коллекция успешно очищена");
    }

    public void save(){
        Write();
        new InputOutput().Output("Коллекция успешно сохранена");
    }

    public void executeScript(String fileName){
        File file2 = new File(fileName);
        Invoker invoker = new Invoker(this);
        Set<String> set = new HashSet<>();

        try {
            if(!file2.canRead() || !file2.canWrite()) throw new SecurityException();
        } catch (SecurityException e) {
            System.err.println("Файл недоступен для чтения");
            return;
        }

        try {
            if(file2.length() == 0) throw new CsvException();
        } catch (CsvException e) {
            System.err.println("Файл пуст");
            return;
        }

        try {
            Scanner sc = new Scanner(new FileInputStream(Path.of(fileName).toFile()), StandardCharsets.UTF_8);
            set.add("execute_script "+fileName);
            int f = set.size();
            while (sc.hasNext()) {

                String a = null;
                try {
                    a = sc.nextLine();
                    if (a.matches("execute_script .*")){
                        set.add(a);

                        if (f == set.size()){
                            System.out.println("Был обнаружен цикл в скриптах, выполнение скриптов остановлено");
                            return;
                        }else f++;
                    }


                    String[] tokens = a.split(" ");
                    Command command = invoker.getCommands().get(tokens[0]);
                    command.execute(tokens);

                } catch (NullPointerException e) {
                    System.out.println("Команда: '" + a + "' введена неверно выполнение скрипта было остановлено");
                    return;
                } catch (NoSuchElementException e) {
                    System.out.println("не-не");

                }
            }
            } catch(FileNotFoundException e){
                new InputOutput().Output("Файл отсутствует");
            }

        new InputOutput().Output("Скрипт успешно выполнен");
    }
}
