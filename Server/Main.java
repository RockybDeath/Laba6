import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static int online=0;
    public static void main(String []args) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Ну нинада это проверять");
                } catch (Exception e) {
                    System.err.println("Данные работы программы не сохранены");
                }
            }));
        List<Integer> l=new ArrayList<>();
        l.add(1); l.add(1488); l.add(1337);
        Stream<Integer> j=l.stream();
        j=j.filter(i->{
            System.out.println(i); return true;});
        System.out.println("Hello");
        l.add(12);
        j.forEach(System.out::println);
        System.out.println("dwdwdw");
//        System.out.println(j.count());
            try {
                String s = "";
                String ip = "";
                int port = 0;
                ServerSocket server = new ServerSocket(2224);
                System.out.println("Введите айпи и порт для сервера. Пример: 127.0.0.10:2223");
                while (true) {
                    try {
                        Scanner Input = new Scanner(System.in);
                        s = Input.nextLine();
                        s = s.trim();
                        if(!"exit".equals(s)) {
                            String[] ipport = s.split(":");
                            if (ipport.length == 2) {
                                ip = ipport[0].trim();
                                port = Integer.valueOf(ipport[1].trim());
                                server = new ServerSocket(port, 1000, Inet4Address.getByName(ip));
                                break;
                            } else System.out.println("Неверные данные");
                        }
                        else System.exit(0);
                    } catch (NullPointerException | StringIndexOutOfBoundsException | IllegalArgumentException | IOException e) {
                        System.out.println("Неверно введены данные или нет допуска в сеть");
                    } catch (NoSuchElementException e){
                    System.out.println("Вы вышли из системы айайайайа");
                    System.exit(0);
                }
                }
                System.out.println("Hello");
//                Socket client = server.accept();
                TreeCol mytree=new TreeCol();
                while (!server.isClosed()) {
                    Socket client = server.accept();
                    online++;
                    System.out.println("There are " + online + " clients online");
                    Runnable r = new ThreadClient(client,mytree);
                    Thread t = new Thread(r);
                    t.start();
                }
                server.close();
            } catch (IOException e) {
                System.out.println("Не могу взаимодейстовать, неверно");
            } catch (IllegalArgumentException e) {
                System.out.println("Неверные данные");
            }
    }
}
