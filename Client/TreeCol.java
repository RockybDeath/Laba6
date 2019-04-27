import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class TreeCol {
    //private static TreeMap<String, Human> HumansTree = new TreeMap<>();
    private static ConcurrentSkipListMap<String,Human> HumansTree=new ConcurrentSkipListMap<>();
    private static Date date = new Date();
//    public static TreeMap<String, Human> get() {
//        return HumansTree;
//    }
//    public static void set(TreeMap<String, Human> Tree) {
//        HumansTree=Tree;
//    }
    public static ConcurrentSkipListMap<String,Human> get(){
        return HumansTree;
    }

    public static void set(ConcurrentSkipListMap<String, Human> humansTree) {
        HumansTree = humansTree;
    }
    public static void addset(ConcurrentSkipListMap<String,Human> humansTree){
        HumansTree.putAll(humansTree);
    }

    /**
     * <p>Показывает содержимое коллекции</p>
     */
    public static void show(){
        System.out.println(HumansTree.entrySet().toString());
    }
    /**
     * <p>список и описание команд</p>
     */
    public static void help1(){
        System.out.println("exit - выход из системы");
        System.out.println("connect{String} - подлюкчиться к серверу по айпи и порту. Пример : connect{127.0.0.9:2223}");
    }
    public static void help(){
        System.out.println("insert {String key} {element} - команда добавления нового элемента с заданным ключом. " +
                "Параметры key - String(имя человека). " +
                "Параметры element - передавать 5 значений: nickname,  surname, name, info, status. Пример : insert {\"nickname\":\"Red\"}" +
                "{\"nickname\":\"Red\", \"surname\":\"Blue\",\"Location\":{\"x\":\"72\", \"y\":\"65\"},\"age\":\"14\"}");
        System.out.println("show - вывод элементов в нашей коллекции");
//        System.out.println("save - сохранить измененную коллекцию в исходный файл");
        System.out.println("info - информация о коллекции, ее тип, дата создания и кол-во элементов");
        System.out.println("remove {String key} - удалить элемент коллекции по его ключу. Пример : remove {\"nickname\":\"Red\"}");
        System.out.println("add_if_min - добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции. Пример: add_if_min {\"nickname\":\"Red\", \"surname\":\"Blue\",\"Location\":{\"x\":\"72\", \"y\":\"65\"},\"age\":\"14\"}");
        System.out.println("import {String whereFile} - загрузить коллекцию из данного файла и загрузить на сервер. Пример : import {C:\\\\Users\\\\Киря\\\\IdeaProjects\\\\Client\\\\src\\\\HumansTree.xml}(Примечание: для клиента) ");
        System.out.println("add_if_max - добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции. Пример: add_if_max {\"nickname\":\"Red\", \"surname\":\"Blue\",\"Location\":{\"x\":\"72\", \"y\":\"65\"},\"age\":\"14\"}");
        System.out.println("load {String whereFile} - загрузить коллекцию из данного файла. Пример : load {C:\\\\Users\\\\Киря\\\\IdeaProjects\\\\Client\\\\src\\\\HumansTree.xml} ");
        System.out.println("save {String whereFile} - сохранить коллекцию в данный файл. Пример : save {C:\\\\Users\\\\Киря\\\\IdeaProjects\\\\Client\\\\src\\\\HumansTree.xml} ");
        System.out.println("disconnect - отключиться от сервера");
    }
    public static void Import(String where){
       try {
           FileInputStream Try = new FileInputStream(where);
           BufferedInputStream MyTree = new BufferedInputStream(Try);
           if (MyTree.available() > 0) {
               byte[] b = new byte[MyTree.available()];
               MyTree.read(b);
               String XmlTree = new String(b);
               TreeCol.set(FileOper.fromXml(XmlTree));
           } else System.out.println("Документ пуст");
       }
       catch (FileNotFoundException e){
           System.out.println("По указанному пути нет файла или нет прав доступа к нему");
       }
       catch (IOException e) {
           System.out.println("Файл пуст или нет прав для доступа к файлу");
       }
    }
    /**
     *<p>Сохраняет коллекцию в файл</p>
     */
    public static void save(){
        FileOper.XmlSaveFile(HumansTree);
    }
    /**
     *<p>Выводит информацию о коллекции</p>
     */
    public static void info(){
        System.out.println("Тип коллекции - TreeMap");
        System.out.println("Дата инициализации - "+date.toString() );
        System.out.println("Количество элементов - "+HumansTree.size());
        System.out.println("Значение объектов коллекции определяется алфавитом");
    }
    /**
     * <p>Удаляет элемент из коллекции по его ключу</p>
     * @param key String
     * @throws NullPointerException
     */
    public static void remove(String key){
        try {
            if (HumansTree.containsKey(key)) {
                HumansTree.remove(key);
                System.out.println("Элемент успешно удален");
            } else {
                System.out.println("Такого элемента нет");
            }
        }
        catch (NullPointerException|StringIndexOutOfBoundsException e){
            System.out.println("Неверно введены данные3");
        }
    }
    /**
     * <p>Добавляет элемент в коллекцию</p>
     * @param human Human
     * @throws NullPointerException
     */
    public static void insert(Human human){
        try {
            if (!HumansTree.containsKey(human.getNickname())) {
                HumansTree.put(human.getNickname(), human);
                System.out.println("Элемент успешно добавлен");
            } else {
                System.out.println("Такой ключ уже существует");
            }
        }
        catch (NullPointerException|StringIndexOutOfBoundsException|IllegalArgumentException e){
            System.out.println("Неверный ввод");
        }
    }
    /**
     * <p>Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции, или же,добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции</p>
     * @param human Human
     * @param comand String
     * @throws NullPointerException,IllegalArgumentException
     */
    public static void add_if(Human human,String comand){
        try {
            if ("add_if_max".equals(comand)) {
                if (human.compareTo(Collections.max(HumansTree.values())) == 1) {
                    HumansTree.put(human.getNickname(), human);
                    System.out.println("Элемент успешно добавлен");
                }
                else System.out.println("Элемент не добавлен");
            }
            if ("add_if_min".equals(comand)) {
                if (human.compareTo(Collections.min(HumansTree.values())) == -1) {
                    HumansTree.put(human.getNickname(), human);
                    System.out.println("Элемент успешно добавлен");
                }
                else System.out.println("Элемент не добавлен");
            }
        }
        catch(NullPointerException|IllegalArgumentException e){
            System.out.println("Неверный ввод");
        }
    }
}
