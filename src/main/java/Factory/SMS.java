package Factory;

public class SMS implements IMessage{
    public void procesar(Message msg){
        System.out.println("Enviando SMS a [" + msg.getRecipient() + "]: " + msg.getContenido());
    }
}
