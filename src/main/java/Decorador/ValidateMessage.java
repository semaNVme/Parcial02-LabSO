package Decorador;

/**
 *
 * @author valeb
 */
import Factory.IMessage;

public class ValidateMessage extends MessageDecorador {

    public ValidateMessage(IMessage message) {
        super(message);
    }

    @Override
    public void procesar(String mess) {
        if (mess == null || mess.trim().isEmpty()) {
            System.out.println("Validacion Fallida: El mensaje esta vacio.");
        } else {
            System.out.println("Validacion Exitosa: Mensaje valido.");
            super.procesar(mess);
        }
    }
}
