
package Factory;

public class Email implements IMessage{
    public void procesar(String mess){
        System.out.println("Enviando Email: " + mess);
    }
}
