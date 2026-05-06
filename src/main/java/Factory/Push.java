package Factory;


public class Push implements IMessage{
    public void procesar(Message msg){
        System.out.println("Enviando Push a [" + msg.getRecipient() + "]: " + msg.getContenido());
    }
    
    
}
