package Decorador;

import Factory.IMessage;
import Factory.Message;

public class EncryptMessage extends MessageDecorador{
    
    public EncryptMessage(IMessage message) {
        super(message);
    }
    
    public void procesar(Message msg){
        String encrypted = encrypt(msg.getContenido());
        msg.setContenido(encrypted);
        message.procesar(msg);
    }
    
    public String encrypt(String mess) {
        mess = mess.substring(mess.length() - 1) + mess.substring(0, mess.length() - 1);
        return mess;
    }
}
