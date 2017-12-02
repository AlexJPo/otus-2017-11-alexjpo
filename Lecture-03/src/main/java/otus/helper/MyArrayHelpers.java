package otus.helper;

import java.util.List;

public class MyArrayHelpers {
    public <T> void setDefaultValues(List<T> array, T... args) {
        for (int i = 0; i < args.length; i++) {
            array.add(args[i]);
        }
    }
}
