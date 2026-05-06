package Factory;

import java.util.HashMap;
import java.util.Map;


public class Factory {
    private Map<String, IMessage> message;

   
    private static Factory instance;

    private Factory() {
        message = new HashMap<>();
        message.put("default", new SMS());
        message.put("email", new Email());
        message.put("push", new Push());
    }

    
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;

    }

    public IMessage getMessage(String type) {

        IMessage result = null;

        if (message.containsKey(type)) {
            result = message.get(type);
        }

        return result;
    }
}
