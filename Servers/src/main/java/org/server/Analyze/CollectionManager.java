package org.server.Analyze;

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



import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import org.server.*;
import org.slf4j.*;

@EqualsAndHashCode
@Setter
@Getter
public class CollectionManager {
    private Date date;
    private LinkedHashSet<Flat> collection;
    private List<Flat> beans = null;
    private File file;
    private List<String> history;

    private static String path = System.getenv("lab");
    private Dotenv dotenv = Dotenv.load();
    private String path2 = dotenv.get("HELLO");

    private static final Logger log = LoggerFactory.getLogger(CollectionManager.class);


    public CollectionManager(){
        this.history = new ArrayList<>();
        this.date = new Date();
        this.collection = new LinkedHashSet<>();
    }

    public void Read() {

        if (path == null){
            new InputOutput().Output("Переменная окружения отсутствует или не определена, дальнейшая работа приложения невозможна\nЗадайте переменную окружения 'lab' с путем до файла");
            System.exit(-1);
        }
        //Path paths;
        //{
            //try {
                //paths = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
                //paths = Path.of(paths.toAbsolutePath() + dotenv.get("HEL"));
            //} catch (URISyntaxException e) {
                //throw new RuntimeException(e);
            //}
        //}
        //System.out.println(paths);
        //File.createNewFile();


        file = new File(path);
        try {
            if(!file.canRead() || !file.canWrite()) throw new SecurityException();
        } catch (SecurityException e) {
            new InputOutput().Output("Файл отсутствует или недоступен для чтения и записи\nХотите повторить попытку, иначе остановится программа?(Y/N)");
            Scanner scanner = new Scanner(System.in);
            String str = null;
            while(!scanner.hasNext("[YyNn]")){
                new InputOutput().Output("Введите Y или N");
                scanner.nextLine();
            }
            str = scanner.nextLine();
            if (Objects.equals(str, "Y") | Objects.equals(str, "y")){
                path = System.getenv("lab");
                Read();
                return;
            }
            else if(Objects.equals(str, "N") | Objects.equals(str, "n")){
                new InputOutput().Output("Программа остановлена");
                System.exit(0);
            }

        }

        try {
            if(file.length() == 0) throw new CsvException();
        } catch (CsvException e) {
            new InputOutput().Output("Файл пустой");
            return;
        }

        try {

            /*
            URL u = getClass().getClassLoader().getResource(path);

            if (u == null){
                System.out.println("Не был найден необходимый файл, дальнейшая работа приложения невозможна");
                System.exit(-1);
            }

            InputStream r = getClass().getClassLoader().getResourceAsStream(path);
            InputStreamReader rr = new InputStreamReader(r, StandardCharsets.UTF_8);
            System.out.println(r);
            */

             beans = new CsvToBeanBuilder<Flat>(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))
                    .withType(Flat.class)
                    .withSeparator(',')
                     .withThrowExceptions(false) //если кол-во элементов строки не совпадает с кол-вом столбцов, то не выскочит CsvRequiredFieldEmptyException
                    .build()
                     .parse();

             /*
            final List<Flat> users = beans.parse();//2
            users.forEach((user) -> {
                logger.info("Parsed data:" + user.toString());
            });

            beans.getCapturedExceptions().forEach((exception) -> { //3
                logger.error("Inconsistent data:" +
                        String.join("", exception.getLine()), exception);//4
            });
             */

        } catch (Exception e) {
            System.out.println(e.getMessage());
            new InputOutput().OutputErr("Файл отсутствует");
        }

        collection = new LinkedHashSet<>(beans);

        int count = 1;
        Set<Integer> set = new HashSet<>();
        for (Flat a: beans){
            if (beans.size() == 0) break;
            int id = a.getId();
            set.add(a.getId());
            while (count > set.size()) {
                a.setId(++id);
                set.add(id);
            }
            count++;
        }
        set.clear();


        new InputOutput().Output("Коллекция успешно загружена");
        beans.clear();

        /*
        for (Flat a : collection) {
            System.out.print( a.getId() + " | " + a.getName() + " | " + a.getCoordinates().getX() + " | " + a.getCoordinates().getY() + " | " +
                     a.getCreationDate() + " | " + a.getArea() + " | " + a.getNumberOfRooms() + " | " + a.getTimeToMetroByTransport() + " | " +
                     a.getView() + " | " + a.getHouse().getName() + " | " + a.getHouse().getYear() + " | " + a.getHouse().getNumberOfFloors()
                            +"\n" );
        }
        */
    }

    public boolean Write() {

        file = new File(path);

        try {
            if(!file.canRead() || !file.canWrite()) throw new SecurityException();
        } catch (SecurityException e) {
            new InputOutput().OutputErr("Файл недоступен для записи");
            return false;
        }

        try {
            if(!file.isFile()) throw new IOException();
        } catch (IOException e) {
            new InputOutput().OutputErr("Файл для сохранения коллекции не является файлом");
            return false;
        }


        beans = new ArrayList<>(getCollection());

        try (PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8)){
            StatefulBeanToCsv<Flat> beanToCsv = new StatefulBeanToCsvBuilder<Flat>(writer)
                    .withSeparator(',')
                    .build();
            beanToCsv.write(beans);
            beans.clear();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            new InputOutput().OutputErr("При записи коллекции в файл произошла ошибка");
            return false;
        }
        return true;
    }

    public Flat add(Flat a){
        a.setId(newId());
        collection.add(a);
        new InputOutput().Output("Новый элемент был успешно добавлен");
        return a;
    }

    public int newId(){

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
        return id;
    }


    public void remove(int id){
        if (!collection.removeIf(a -> a.getId() == id)) {
            new InputOutput().Output("Элемента под id = " + id + " нет в коллекции");
        }else new InputOutput().Output("Элемент коллекции под id = " + id + " был успешно удален");
        history("remove");
    }

    public void info(){
        new InputOutput().Output("Тип коллекции - 'LinkedHashSet' | Дата инициализации - " + date + " | Кол-во элементов - " + collection.size());
        history("info");
    }

    public void show(){
        if (collection.isEmpty()) {
            new InputOutput().Output("Коллекция пустая");
        }else {
            collection.forEach(a -> new InputOutput().Output("id: " + a.getId() + "\nname: " + a.getName() +
                    " | кол-во комнат: " + a.getNumberOfRooms() + " | время до метро: " + a.getTimeToMetroByTransport() +
                    " | область: " + a.getArea() + " | дата создания элемента: " + a.getCreationDate() +
                    " | координата X: " + a.getCoordinates().getX() + " | координата Y: " + a.getCoordinates().getY() +
                    " | название дома: " + a.getHouse().getName() + " | возраст дома: " + a.getHouse().getYear() +
                    " | кол-во этажей: " + a.getHouse().getNumberOfFloors() + " | вид: " + a.getView()));
        }
        history("show");
    }

    public void update(int id){
        if(collection.removeIf(a -> a.getId() == id)){

        }else new InputOutput().Output("Элемента под id = " + id + " нет в коллекции");
        history("update");
    }

    public void clear(){
        collection.clear();
        new InputOutput().Output("Коллекция успешно очищена");
        history("clear");
    }

    public void save(){
        if(Write()) {
            new InputOutput().Output("Коллекция успешно сохранена");
        }else new InputOutput().Output("Коллекция не была сохранена");
        history("save");
    }

    public void executeScript(String fileName) {
        history("execute_script");

        new InputOutput().Output("Выполнение скрипта окончено");
    }

    public void addIfMax(Flat flat){
        Flat fl = add(flat);
        if (fl.compareTo(Collections.max(collection)) > 0){
            add(fl);
        }else new InputOutput().Output("Объект не больше максимального, поэтому не был добавлен в коллекцию");
        history("add_if_max");
    }

    public void addIfMin(Flat flat){
        Flat fl = add(flat);
        if (fl.compareTo(Collections.min(collection)) > 0){
            add(fl);
        }else new InputOutput().Output("Объект не больше минимального, поэтому не был добавлен в коллекцию");
        history("add_if_min");
    }

    public void history(String commandName){
        if (history.size() < 15) {
            history.add(commandName);
        }else {
            history.remove(0);
            history.add(commandName);
        }
    }
    public void historyOutput(){
        history("history");
        history.forEach(a -> new InputOutput().Output(a));
    }

    public void sumOfTimeToMetroByTransport(){
        float sum = 0;
        for (Flat a:collection) {
            sum += a.getTimeToMetroByTransport();
        }
        new InputOutput().Output(String.valueOf(sum));
        history("sum_of_time_to_metro_by_transport");
    }

    public void groupCountingByCreationDate(){
        HashMap<Date, List<Flat>> hashMap = new HashMap<>();

        for (Flat a:collection) {
            if (!hashMap.containsKey(a.getCreationDate())){
                List<Flat> list = new ArrayList<>();
                list.add(a);
                hashMap.put(a.getCreationDate(), list);
            }else{
                hashMap.get(a.getCreationDate()).add(a);
            }
        }
        hashMap.forEach((a, b)-> System.out.println(a +" "+ b));
        history("groupCountingByCreationDate");
    }

    public void help(){
        history("help");
    }

    //public void countLessThanHouse(){
    //}
}
