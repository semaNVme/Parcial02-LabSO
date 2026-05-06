package com.mycompany.parcial2;

import Factory.Factory;
import Factory.IMessage;
import Factory.Message;
import Decorador.*;
import Observer.*;

import Views.Principal;

public class Parcial2 {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE MENSAJERIA INTEGRADO (Factory + Decorator + Observer) ===");
        System.out.println("Lanzando interfaz gráfica desde Views.Principal...");
        Principal.main(args);
    }
}
