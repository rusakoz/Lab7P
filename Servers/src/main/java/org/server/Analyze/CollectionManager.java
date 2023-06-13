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
import java.util.*;

import org.server.*;
import org.slf4j.*;

@EqualsAndHashCode
@Setter
@Getter
public class CollectionManager {
    private static final Logger logger = LoggerFactory.getLogger(CollectionManager.class);
    private static final Date date = new Date();
    private static LinkedHashSet<Flat> collection = new LinkedHashSet<>();
    private List<Flat> beans = null;
    private File file;
    private static List<String> history = new ArrayList<>();

    private static String path = System.getenv("lab");
    private Dotenv dotenv = Dotenv.load();
    private String path2 = dotenv.get("HELLO");

    private static final Logger log = LoggerFactory.getLogger(CollectionManager.class);


    public CollectionManager(){

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


        beans = new ArrayList<>(collection);

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

    public StringBuilder add(Flat a){
        collection.add(FlatWithID(a));
        logger.info("Новый элемент был успешно добавлен");
        return new StringBuilder("Новый элемент был успешно добавлен");
    }

    public Flat FlatWithID(Flat a){
        a.setId(newId());
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


    public StringBuilder remove(int id){
        history("remove");
        if (!collection.removeIf(a -> a.getId() == id)) {
            return new StringBuilder("Элемента под id = " + id + " нет в коллекции");
        }else return new StringBuilder("Элемент коллекции под id = " + id + " был успешно удален");
    }

    public StringBuilder info(){
        history("info");
        return new StringBuilder("Тип коллекции - 'LinkedHashSet' | Дата инициализации - ").append(date).append(" | Кол-во элементов - ").append(collection.size());
    }

    public StringBuilder show(){
        StringBuilder stringBuilder = new StringBuilder();
        history("show");
        if (collection.isEmpty()) {
            return stringBuilder.append("Коллекция пустая");
        }else {
//            collection.stream().sorted(new Compar()).forEach((a)-> System.out.println(a.getName()));
            collection.stream().sorted(new Compar()).forEach((a) -> {
                stringBuilder.append("\nid: ").append(a.getId()).append("\nname: ").append(a.getName()).append(" | кол-во комнат: ").append(a.getNumberOfRooms()).append(" | время до метро: ").append(a.getTimeToMetroByTransport()).append(" | область: ").append(a.getArea()).append(" | дата создания элемента: ").append(a.getCreationDate()).append(" | координата X: ").append(a.getCoordinates().getX()).append(" | координата Y: ").append(a.getCoordinates().getY()).append(" | название дома: ").append(a.getHouse().getName()).append(" | возраст дома: ").append(a.getHouse().getYear()).append(" | кол-во этажей: ").append(a.getHouse().getNumberOfFloors()).append(" | вид: ").append(a.getView());});
            return stringBuilder;
        }
    }

    public StringBuilder update(Integer id, Object obj){
        history("update");
        if(collection.removeIf(a -> a.getId() == id)){
            add((Flat) obj);
            return new StringBuilder("Объект успешно добавлен");
        }else return new StringBuilder("Элемента под id = " + id + " нет в коллекции");
    }

    public StringBuilder clear(){
        collection.clear();
        history("clear");
        return new StringBuilder("Коллекция успешно очищена");
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

    public StringBuilder addIfMax(Flat flat){
        Flat fl = FlatWithID(flat);
        history("add_if_max");
        if (fl.compareTo(Collections.max(collection)) > 0){
            add(fl);
            return new StringBuilder("Объект успешно добавлен");
        }else return new StringBuilder("Объект не больше максимального, поэтому не был добавлен в коллекцию");
    }

    public StringBuilder addIfMin(Flat flat){
        Flat fl = FlatWithID(flat);
        history("add_if_min");
        if (fl.compareTo(Collections.min(collection)) > 0){
            add(fl);
            return new StringBuilder("Объект успешно добавлен");
        }else return new StringBuilder("Объект не больше минимального, поэтому не был добавлен в коллекцию");
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

    public StringBuilder sumOfTimeToMetroByTransport(){

        history("sum_of_time_to_metro_by_transport");
        float sum = 0;
        for (Flat a:collection) {
            sum += a.getTimeToMetroByTransport();
        }
        return new StringBuilder(String.valueOf(sum));
    }

    public StringBuilder groupCountingByCreationDate(){
        HashMap<Date, List<Flat>> hashMap = new HashMap<>();
        StringBuilder str = new StringBuilder();

        for (Flat a:collection) {
            if (!hashMap.containsKey(a.getCreationDate())){
                List<Flat> list = new ArrayList<>();
                list.add(a);
                hashMap.put(a.getCreationDate(), list);
            }else{
                hashMap.get(a.getCreationDate()).add(a);
            }
        }
        hashMap.forEach((a, b)-> str.append(a).append(" ").append(b));
        history("groupCountingByCreationDate");
        return str;
    }

    public void help(){
        history("help");
    }

    //public void countLessThanHouse(){
    //}
}
