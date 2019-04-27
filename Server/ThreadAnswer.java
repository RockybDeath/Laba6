import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ThreadAnswer implements Runnable {
    private String answer;
    private ObjectOutputStream out;
    public ThreadAnswer(String answer, ObjectOutputStream out){
        this.answer=answer;
        this.out=out;
    }
    public void run(){
        try{
            out.writeUTF(answer);
            out.flush();
        }
        catch (IOException e){
            System.out.println("Не могу отправить");
        }
    }
}
