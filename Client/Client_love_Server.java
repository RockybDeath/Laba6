import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client_love_Server {
//    private static ByteArrayOutputStream Second;
//    private static ObjectOutputStream First;
//    private static ByteBuffer Third;
//    private static ObjectInputStream rr;
//    private static ByteArrayInputStream tt;
//    private static ByteBuffer yyy=ByteBuffer.allocate(64000);
//    static {
//        try {
//            Second = new ByteArrayOutputStream(65536);
//            First = new ObjectOutputStream(Second);
//        }
//        catch (IOException e){
//            System.out.println("Нельзя подключиться");
//        }
//    }
    public static void write(Object a, ObjectOutputStream First,ByteArrayOutputStream Second,SocketChannel privet,ByteBuffer Third) {
        try {
           if (a instanceof java.lang.String) First.write(((String) a).getBytes(StandardCharsets.UTF_8)); else
            First.writeObject(a);
            First.flush();
            privet.write(Third.wrap(Second.toByteArray()));
            Second.reset();
//            if (a instanceof java.lang.String) out.write(((String)a).getBytes(StandardCharsets.UTF_8));
//            else out.writeObject(a);
//            out.flush();
        } catch (IOException e) {
//            try {
//                First.reset();
//                Second.reset();
//            } catch (IOException ex) {
//                System.out.println("невозмонжо закрыть");
//            }
            System.out.println("Невозможно отправить, сервер миртв");
        }
    }
    public static String read(ObjectInputStream read) throws IOException{
////        return "dwdwd";
//        try {
//            byte [] cccc=new byte[64000];
//            privet.read(yyy);
//            tt=new ByteArrayInputStream(yyy.array());
//            rr=new ObjectInputStream(tt);
//            rr.read(cccc);
//            tt.reset();
//            rr.reset();
//            return new String(cccc);
//        }
//        catch (IOException e){
//            e.printStackTrace();
//            System.out.println("Ошибка получения");
//        }
        return read.readUTF();
    }
}
