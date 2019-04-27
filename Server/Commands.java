import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentSkipListMap;

public class Commands {
    public static String Activate(String command,ObjectInputStream in,TreeCol mytree){
        try {
            byte [] examaple=new byte[55700000];
            if ("info".equals(command)) return mytree.info();
            else if ("show".equals(command)) {
                mytree.setL("");
                return mytree.show();
            }
            else if ("save".equals(command)) {
                in.read(examaple);
                return mytree.save(new String(examaple).trim());
            }
            else if ("remove".equals(command)) {
                in.read(examaple);
                return mytree.remove(new String(examaple).trim());
            }
            else if("import".equals(command)){
                mytree.addset((ConcurrentSkipListMap<String,Human>)in.readObject());
                return "Данные добавлены";
            }
            else if ("insert".equals(command)) return mytree.insert((Human)in.readObject());
            else if ("add_if_max".equals(command)||"add_if_min".equals(command)) return mytree.add_if((Human)in.readObject(),command);
            else if ("load".equals(command)) {
                in.read(examaple);
                return mytree.load(new String(examaple).trim());
            }
            else if("autosave".equals(command)) return mytree.autosave();
            else if("disconnect".equals(command)){
                return "Вы отключились";
            }

        }
        catch(ClassNotFoundException e){
            System.out.println("Такого класса нет");
        }
        catch(IOException e){
            System.out.println("Не могу передать");
        }
        return null;
    }
}
