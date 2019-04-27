import sun.reflect.generics.tree.Tree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class ThreadClient implements Runnable {
    private Socket client=null;
    private TreeCol mytree;
    public ThreadClient(Socket socket,TreeCol mytree){
        client=socket;
        this.mytree=mytree;
    }
    public void run(){
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            try {
//                System.out.println("dwdwdw");
//            } catch (Exception e) {
//                System.err.println("Данные работы программы не сохранены");
//            }
//        }));
        try(ObjectOutputStream out=new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in=new ObjectInputStream(client.getInputStream())){
            String what="";
            String answer="";
            Human human=new Human();
            while(!client.isClosed()&&!"disconnect".equals(what)){
                if(in.available()>0){
                    int size = client.getReceiveBufferSize();
                    byte[] lol = new byte[size];
                    in.read(lol);
                    what=((new String(lol))).trim();
                    System.out.println("Read from client message - " + what);
                    answer=Commands.Activate(what,in,mytree);
                    if (answer==null) answer="чаво";
                    Runnable r = new ThreadAnswer(answer, out);
                    Thread t = new Thread(r);
                    t.start();
                    t.interrupt();
                }
            }
            Thread.sleep(100);
            System.out.println("Disconnect");
            Main.online--;
            System.out.println("There are "+Main.online+" clients online");
            out.close();
            in.close();
            client.close();
            client=null;
        }
        catch (IOException e){
            System.out.println("Не могу отправить или принять");
        }
        catch (InterruptedException e){
            System.out.println("пизда");
        }
    }
}
