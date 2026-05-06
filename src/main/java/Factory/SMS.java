package Factory;

public class SMS implements IMessage{
    public void procesar(String mess){
        System.out.println("Enviando SMS: " + mess);
    }
}
