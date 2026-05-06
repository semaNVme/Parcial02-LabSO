/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package access;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author valeb
 */
public class Factory {
    private Map<String, IMessage> message;

    /**
     * Singleton
     */
    private static Factory instance;

    private Factory() {
        message = new HashMap<>();
        message.put("default", new SMS());
        message.put("email", new Email());
        message.put("push", new Push());
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;

    }

    /**
     * Método que crea una instancia concreta de la jerarquia IProductRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IProductRepository
     */
    public IMessage getMessage(String type) {

        IMessage result = null;

        if (message.containsKey(type)) {
            result = message.get(type);
        }

        return result;
    }
}
