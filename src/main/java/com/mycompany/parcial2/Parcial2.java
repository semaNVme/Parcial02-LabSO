package com.mycompany.parcial2;

import Factory.Factory;
import Factory.IMessage;
import Decorador.EncryptMessage;
import Decorador.LogMessage;
import Decorador.ValidateMessage;

public class Parcial2 {

    public static void main(String[] args) {
        System.out.println("--- Demostracion de Patrones Factory y Decorator ---");

        Factory factory = Factory.getInstance();
        IMessage emailBase = factory.getMessage("email");
        IMessage smsBase = factory.getMessage("default"); // SMS es el default

        System.out.println("\n[Escenario 1: Email con validacion, log y encriptacion]");
        IMessage emailDecorado = new ValidateMessage(new LogMessage(new EncryptMessage(emailBase)));
        emailDecorado.procesar("Este es un mensaje secreto");

        System.out.println("\n[Escenario 2: SMS con validacion y log (sin encriptacion)]");
        IMessage smsDecorado = new LogMessage(new ValidateMessage(smsBase));
        smsDecorado.procesar("Hola, esto es un SMS normal");

        System.out.println("\n[Escenario 3: Prueba de validacion fallida]");
        smsDecorado.procesar("");
    }
}
