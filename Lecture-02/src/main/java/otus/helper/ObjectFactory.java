package otus.helper;

import otus.interfaces.IObjectMemory;

public class ObjectFactory {
    public IObjectMemory getObject(String type) {
        switch (type.toLowerCase()) {
            case "object":
                return new ObjectMemory();
            case "string":
                return new StringMemory();
            case "custom":
                return new CustomClassMemory();
        }

        return null;
    }
}
