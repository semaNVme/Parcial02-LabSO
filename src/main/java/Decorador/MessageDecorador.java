/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Decorador;

import Factory.Factory;
import Factory.IMessage;
import Factory.Message;

/**
 *
 * @author valeb
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
