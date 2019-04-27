import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

public class TreeCol {
    private String l="";
    //private static TreeMap<String, Human> HumansTree = new TreeMap<>();
    private ConcurrentSkipListMap<String,Human> HumansTree;
    private Date date;
//    public static TreeMap<String, Human> get() {
//        return HumansTree;
//    }
//    public static void set(TreeMap<String, Human> Tree) {
//        HumansTree=Tree;
//    }
    public TreeCol(){
        date=new Date();
        HumansTree=new ConcurrentSkipListMap<>();
    }

    public void setL(String l) {
        this.l =l;
    }

    public String getL() {
        return l;
    }

    public    ConcurrentSkipListMap<String,Human> get(){
        return HumansTree;
    }

    public    void set(ConcurrentSkipListMap<String, Human> humansTree) {
        HumansTree = humansTree;
    }

    /**
     * <p>Показывает содержимое коллекции</p>
     */
//    public static void show(){
//        System.out.println(HumansTree.entrySet().toString());
//    }
    public void sendMessage(Human human){
        this.l=this.l+human.toString()+"\n";
    }
    public     String show(){
//        try {
             HumansTree.entrySet().stream().forEach((human)->sendMessage(human.getValue()));
             return getL();
//        }
//        catch(IOException e){
//            System.out.println("Клиенг обнаглел, отлючился");
//        }
    }
    /**
     * <p>список и описание команд</p>
     */
//    public static String help(){
//        return "insert {String key} {element} - команда добавления нового элемента с заданным ключом. " +
//                "Параметры key - String(имя человека). " +
//                "Параметры element - передавать 5 значений: nickname,  surname, name, info, status. Пример : insert {\"nickname\":\"Red\"}" +
//                "{\"nickname\":\"Red\", \"surname\":\"Blue\",\"Location\":{\"x\":\"72\", \"y\":\"65\"},\"age\":\"14\"}"+
//        "show - вывод элементов в нашей коллекции"+
//        "save - сохранить измененную коллекцию в исходный файл"+
//        "info - информация о коллекции, ее тип, дата создания и кол-во элементов"+
//        "remove {String key} - удалить элемент коллекции по его ключу. Пример : remove {\"nickname\":\"Red\"}"+
//        "add_if_min - добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции. Пример: add_if_min {\"nickname\":\"Red\", \"surname\":\"Blue\",\"Skill\":{\"name\":\"Убийство\", \"info\":\"Смертельное повреждение объекту\", \"status\":\"Мертв\"}}"+
//        "add_if_max - добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции. Пример: add_if_max {\"nickname\":\"Red\", \"surname\":\"Blue\",\"Skill\":{\"name\":\"Убийство\", \"info\":\"Смертельное повреждение объекту\", \"status\":\"Мертв\"}}";
//    }
    public  void addset(ConcurrentSkipListMap<String,Human> humansTree){
            HumansTree.putAll(humansTree);
    }
    public  String load(String where){
       try {
           FileInputStream Try = new FileInputStream(where);
           BufferedInputStream MyTree = new BufferedInputStream(Try);
           if (MyTree.available() > 0) {
               byte[] b = new byte[MyTree.available()];
               MyTree.read(b);
               String XmlTree = new String(b);
               addset(FileOper.fromXml(XmlTree));
               return "Данные загружены";
           } else return "Документ пуст";
       }
       catch (FileNotFoundException e){
           return "По указанному пути нет файла или нет прав доступа к нему";
       }
       catch (IOException e) {
           return "Файл пуст или нет прав для доступа к файлу";
       }
//       return "По указанному пути нет файла, он пуст или нет прав для доступа к файлу";
    }
//    /**
//     *<p>Сохраняет коллекцию в файл</p>
//     */
//    public static void save(){
//        FileOper.XmlSaveFile(HumansTree);
//    }
    public  String autosave(){
        try {
            File autosave = new File("autosave.xml");
            autosave.createNewFile();
            return FileOper.XmlSaveFile(HumansTree,autosave.getAbsolutePath());
        }
        catch (IOException e){
            return "Недостаточно прав или чего-нибудь еще для создания файла(((, работа не сохранена((";
        }
    }
        public  String save(String out) {
//            try {
               return FileOper.XmlSaveFile(HumansTree, out);
//                return "Коллекция сохранена";
//            }
//            catch(IOException e){
//                System.out.println("Клиенг обнаглел, отлючился");
//            }
        }
    /**
     *<p>Выводит информацию о коллекции</p>
     */
    public  String info(){
//        try {
            return "Тип коллекции - TreeMap. " + "Дата инициализации - " + date.toString() + ". Количество элементов - " + HumansTree.size() + ". Значение объектов коллекции определяется алфавитом.";
//        }
//        catch(IOException e){
//            System.out.println("Клиенг обнаглел, отлючился");
//        }
    }
    /**
     * <p>Удаляет элемент из коллекции по его ключу</p>
     * @param key String
     * @throws NullPointerException
     */
    public  String remove(String key){
//        try {
            if (HumansTree.containsKey(key)) {
                HumansTree.remove(key);
                return "Элемент успешно удален";
            } else
                return "Такого элемента нет";
//        }
//        catch (NullPointerException|StringIndexOutOfBoundsException e){
//            System.out.println("Неверно введены данные");
//        }
//        catch(IOException e){
//            System.out.println("Клиенг обнаглел, отлючился");
//        }
    }
    /**
     * <p>Добавляет элемент в коллекцию</p>
     * @param human Human
     * @throws NullPointerException
     */
    public String insert(Human human){
//        try {
            if (!HumansTree.containsKey(human.getNickname())) {
                HumansTree.put(human.getNickname(), human);
                return "Элемент успешно добавлен";
            } else {
                return "Такой ключ уже существует";
            }
//        }
//        catch (NullPointerException|StringIndexOutOfBoundsException|IllegalArgumentException e){
//            System.out.println("Неверный ввод");
//        }
//        catch(IOException e){
//            System.out.println("Клиенг обнаглел, отлючился");
//        }
    }
    /**
     * <p>Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции, или же,добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции</p>
     * @param human Human
     * @param comand String
     * @throws NullPointerException,IllegalArgumentException
     */
    public String add_if(Human human,String comand){
//        try {
        if ("add_if_max".equals(comand)&&!HumansTree.isEmpty()) {
            if (human.compareTo(HumansTree.entrySet().stream().max((o1, o2) -> {if (o1.getValue().getNickname().toLowerCase().compareTo(o2.getValue().getNickname().toLowerCase())<0) return 1;
            else if (o1.getValue().getNickname().toLowerCase().compareTo(o2.getValue().getNickname().toLowerCase())>0) return -1;
            else if (o1.getValue().getSurName().toLowerCase().compareTo(o2.getValue().getSurName().toLowerCase())<0) return 1;
            else if (o1.getValue().getSurName().toLowerCase().compareTo(o2.getValue().getSurName().toLowerCase())>0) return -1;
                return 0;} ).get().getValue()) == 1) {
                HumansTree.put(human.getNickname(), human);
                return "Элемент успешно добавлен";
            }
            else return "Элемент не добавлен";
        }
        if ("add_if_min".equals(comand)&&!HumansTree.isEmpty()) {
            if (human.compareTo(HumansTree.entrySet().stream().max((o1, o2) -> {if (o1.getValue().getNickname().toLowerCase().compareTo(o2.getValue().getNickname().toLowerCase())<0) return 1;
            else if (o1.getValue().getNickname().toLowerCase().compareTo(o2.getValue().getNickname().toLowerCase())>0) return -1;
            else if (o1.getValue().getSurName().toLowerCase().compareTo(o2.getValue().getSurName().toLowerCase())<0) return 1;
            else if (o1.getValue().getSurName().toLowerCase().compareTo(o2.getValue().getSurName().toLowerCase())>0) return -1;
                return 0;} ).get().getValue()) == -1) {
                HumansTree.put(human.getNickname(), human);
                return "Элемент успешно добавлен";
            }
            else return "Элемент не добавлен";
        }
        else return "Элемент не добавлен, можат пустая каллекция";
//            if ("add_if_max".equals(comand)) {
//                if (human.compareTo(Collections.max(HumansTree.values())) == 1) {
//                    HumansTree.put(human.getNickname(), human);
//                    return "Элемент успешно добавлен";
//                }
//                else return "Элемент не добавлен";
//            }
//            if ("add_if_min".equals(comand)) {
//                if (human.compareTo(Collections.min(HumansTree.values())) == -1) {
//                    HumansTree.put(human.getNickname(), human);
//                   return "Элемент успешно добавлен";
//                }
//                else return "Элемент не добавлен";
//            }
//            else return "Элемент не добавленннн";
        }
//        catch(NullPointerException|IllegalArgumentException e){
//            System.out.println("Неверный ввод");
//        }
//            catch(IOException e){
//                System.out.println("Клиенг обнаглел, отлючился");
//            }
}
