package Factory;


public class Push implements IMessage{
    public void procesar(String mess){
        System.out.println("Enviando Push: " + mess);
    }
    
    
}
