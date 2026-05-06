
package Decorador;

import Factory.Factory;
import Factory.IMessage;
import Factory.Message;

/**
 * PATRÓN DECORATOR: Esta es la clase base para los decoradores. 
 * Permite añadir funcionalidades extra (encriptación, log, etc.) 
 * a un objeto de tipo IMessage sin modificar su código original.
 */
public class MessageDecorador implements IMessage{
    protected IMessage message;
    public MessageDecorador(IMessage message) {
        super();
        this.message = message;
    }

    public void procesar(Message msg){
        message.procesar(msg);
    }
    
}
