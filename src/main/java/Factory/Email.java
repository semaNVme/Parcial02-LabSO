
package Factory;

public class Email implements IMessage{
    public void procesar(Message msg){
        System.out.println("Enviando Email a [" + msg.getRecipient() + "]: " + msg.getContenido());
    }
}
