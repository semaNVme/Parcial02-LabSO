package Decorador;

import Factory.IMessage;

public class EncryptMessage extends MessageDecorador{
    
    public EncryptMessage(IMessage message) {
        super(message);
    }
    
    public void procesar(String mess){
        mess=encrypt(mess);
        message.procesar(mess);
    }
    
    public String encrypt(String mess) {
        mess = mess.substring(mess.length() - 1) + mess.substring(0, mess.length() - 1);
        return mess;
    }
}
