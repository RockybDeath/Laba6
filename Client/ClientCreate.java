import com.sun.javafx.image.impl.ByteRgb;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientCreate {
    private boolean p=false;
    private SocketChannel privet;
    private ByteArrayOutputStream write1;
    private ObjectInputStream read;
    private ObjectOutputStream write;
    private ByteBuffer write2;
    public ClientCreate(SocketChannel privet){
        this.privet=privet;
    }
    public void CreateClient(){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Client_love_Server.write("autosave", write, write1, privet, write2);
                System.out.println(Client_love_Server.read(read));
                Client_love_Server.write("disconnect", write, write1, privet, write2);
                System.out.println(Client_love_Server.read(read));
                p=true;
            } catch (Exception e) {
                if (p=false)System.err.println("Данные работы программы не сохранены");
            }
        }));
        try{
            Human human=new Human();
            System.out.println("Вы успешно подключились");
            this.read=new ObjectInputStream(privet.socket().getInputStream());
//            this.write=new ObjectOutputStream(privet.socket().getOutputStream());
            write1=new ByteArrayOutputStream(65351600);
            write=new ObjectOutputStream(write1);
            String s="";
        while (!"disconnect".equals(s)) {
            Scanner Input = new Scanner(System.in);
            s = Input.nextLine();
            s = s.trim();
            ParserJson MyString = new ParserJson(s);
            MyString.parser();
            String command=MyString.getFirstValue();
            if ("show".equals(command) || "info".equals(command)|| "help".equals(command) || "disconnect".equals(command)) {
                if ("help".equals(command)) TreeCol.help();
                else {Client_love_Server.write(command,write,write1,privet,write2);
                    System.out.println(Client_love_Server.read
                            (read));}
            } else if ("import".equals(command)) {
                TreeCol.Import(MyString.getInput().substring(0, MyString.getInput().length() - 1));
                Client_love_Server.write(command,write,write1,privet,write2);
                Client_love_Server.write(TreeCol.get(),write,write1,privet,write2);
                System.out.println(Client_love_Server.read(read));
            }else if ("load".equals(command)||"save".equals(command)) {
                String key=MyString.getInput().substring(0, MyString.getInput().length() - 1);
                Client_love_Server.write(command,write,write1,privet,write2);
                Client_love_Server.write(key,write,write1,privet,write2);
                System.out.println(Client_love_Server.read(read));
            }
            else if ("insert".equals(command)) {
                try {
                    MyString.element(MyString.StringKey(MyString.getInput()));
                    if (MyString.getHowmany() == 2 && MyString.getSecondValue() != null && MyString.getThirdValue() != null && MyString.getFourthValue() != null && MyString.getFifthValue() != null && MyString.getSixthValue() != null) {
                        human = new Human(MyString.getSecondValue(), MyString.getThirdValue(), new Location(MyString.getFourthValue(), MyString.getFifthValue()), MyString.getSixthValue());
                        Client_love_Server.write(command, write, write1, privet, write2);
                        Client_love_Server.write(human, write, write1, privet, write2);
                        System.out.println(Client_love_Server.read(read));
                    } else System.out.println("Неверное введены данные");
                }
                catch (NullPointerException|IllegalArgumentException|StringIndexOutOfBoundsException e){
                    System.out.println("Неверное введено");
                }
            }
            else if ("remove".equals(command)){
                try {
                    MyString.StringKey(MyString.getInput());
                    if (MyString.getSecondValue() == null) System.out.println("Неверное введены данные");
                    else {
                        Client_love_Server.write(command, write, write1, privet, write2);
                        Client_love_Server.write(MyString.getSecondValue(), write, write1, privet, write2);
                        System.out.println(Client_love_Server.read(read));
                    }
                }catch (NullPointerException|IllegalArgumentException|StringIndexOutOfBoundsException e){
                    System.out.println("Неверное введено");
                }
            }
            else if ("add_if_max".equals(command)||"add_if_min".equals(command)){
                try {
                    MyString.element("{" + MyString.getInput());
                    if (MyString.getSecondValue() != null && MyString.getThirdValue() != null && MyString.getFourthValue() != null && MyString.getFifthValue() != null && MyString.getSixthValue() != null) {
                        human = new Human(MyString.getSecondValue(), MyString.getThirdValue(), new Location(MyString.getFourthValue(), MyString.getFifthValue()), MyString.getSixthValue());
                        Client_love_Server.write(command, write, write1, privet, write2);
                        Client_love_Server.write(human, write, write1, privet, write2);
                        System.out.println(Client_love_Server.read(read));
                    } else System.out.println("Неверное введены данные");
                }catch (NullPointerException|IllegalArgumentException|StringIndexOutOfBoundsException e){
                    System.out.println("Неверно введено");
                }
            }
            else System.out.println("Неверные данные, введите help");
            Thread.sleep(100);
        }
            read.close();
            privet.close();
    }
        catch (InterruptedException e){
            System.out.println("Клиент умер");
        }
        catch (IOException e){
//            try {
//                privet.close();
//                read.close();
//            } catch (IOException ex) {
//                System.out.println("Незя закрыть");
//            }
            System.out.println("Не могу отправить или принять");
        }
        catch (NoSuchElementException e) {
            try {
                Client_love_Server.write("autosave", write, write1, privet, write2);
                System.out.println(Client_love_Server.read(read));
                Client_love_Server.write("disconnect", write, write1, privet, write2);
                System.out.println(Client_love_Server.read(read));
                System.exit(0);
                p=true;
            }
            catch (IOException e1){
                System.out.println("Не могу отправить или получить");
            }
        }
    }
}
