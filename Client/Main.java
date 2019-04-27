import javafx.beans.binding.ObjectExpression;

import javax.naming.ldap.SortKey;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
    public static void main(String[] args) {
//        try {
//            Scanner klava=new Scanner(System.in);
//            SocketAddress socket=new InetSocketAddress(Inet4Address.getLocalHost(),2222);
//            SocketChannel privet=SocketChannel.open(socket);
//            ByteBuffer ll=ByteBuffer.wrap(klava.nextLine().getBytes(StandardCharsets.UTF_8));
//            privet.write(ll);
//            Thread.sleep(4000);
//        }
//        catch (UnknownHostException|SocketException e){
//            System.out.println("dwdw");
//        }
//        catch (InterruptedException e){
//            System.out.println("12");
//        }
//        catch (IOException e){
//            System.out.println("dwdwdwwww");
//        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("Я умир");
            } catch (Exception e) {
                System.err.println("Данные работы программы не сохранены");
            }
        }));
        String s = "";
        try {
            if (args.length > 1 | args.length == 0) {
                System.out.println("Нужно ввести название одного файла, данные не загружены");
            } else {
                FileInputStream Try = new FileInputStream(args[0]);
                BufferedInputStream MyTree = new BufferedInputStream(Try);
                if (MyTree.available() > 0) {
                    byte[] b = new byte[MyTree.available()];
                    MyTree.read(b);
                    String XmlTree = new String(b);
                    TreeCol.set(FileOper.fromXml(XmlTree));
                } else System.out.println("Документ пуст");
            }
        } catch (FileNotFoundException e) {
            System.out.println("По указанному пути нет файла или нет прав доступа к нему");
        } catch (IOException e) {
            System.out.println("Файл пуст или нет прав для доступа к файлу");
        }
        System.out.print("Enter your command, please without bugs: ");
           try {
            while (true) {
                Scanner Input = new Scanner(System.in);
                s = Input.nextLine();
                s = s.trim();
                ParserJson MyString = new ParserJson(s);
                MyString.parser();
                if("connect".equals(MyString.getFirstValue())){
                    String host=MyString.getInput().substring(0, MyString.getInput().length() - 1);
                    try{
                    if(host!=null&&host.contains(":")) {
                            String[] ipport = host.split(":");
                            if (ipport.length == 2) {
                                SocketAddress socket1 = new InetSocketAddress(ipport[0].trim(), Integer.valueOf(ipport[1].trim()));
                                try (SocketChannel privet = SocketChannel.open(socket1)) {
                                    ClientCreate clientCreate = new ClientCreate(privet);
                                    clientCreate.CreateClient();
                                } catch (SocketException e) {
                                    System.out.println("Сервер не доступен");
                                }
                            } else System.out.println("Неверные данные");
                        } else System.out.println("Неверные данные");
                    }catch (NullPointerException | StringIndexOutOfBoundsException | IllegalArgumentException e) {
                        System.out.println("Неверно введены данные2");
                    }

                }
                else if("help".equals(s)){
                    TreeCol.help1();
                }
                else if("exit".equals(s)){
                    System.out.println("Выход из программы");
                    System.exit(0);
                }
                else System.out.println("Неверные данные, введите help");
            }
            }
            catch (NullPointerException | StringIndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Неверно введены данные1");
            }
//        catch (ClassNotFoundException e){
//            System.out.println("Непонятный объект с сервера");
//        }
            catch (NoSuchElementException e) {
                System.out.println("Оно вам надо было(((");
                System.exit(0);
            } catch (UnknownHostException e) {
                System.out.println("Неверный адрес");
            } catch (SocketException e) {
                System.out.println("Сервер не доступен");
            } catch (IOException e) {
                System.out.println("Нельзя связаться");
            }
            //System.out.println(Client_love_Server.read(privet));
//                ByteBuffer ff=ByteBuffer.allocate(65443);
//                privet.read(ff);
//                byte [] tea=ff.array();
//                System.out.println(new String(tea));
//                ByteArrayInputStream ggg=new ByteArrayInputStream(tea);
////                System.out.println((char)ggg.read());
//                    ObjectInputStream ggg1 = new ObjectInputStream(ggg);
//                    System.out.println(ggg1.readObject());
        }
    }
