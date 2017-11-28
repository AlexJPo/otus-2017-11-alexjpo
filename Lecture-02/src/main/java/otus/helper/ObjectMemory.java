package otus.helper;

import otus.interfaces.IObjectMemory;

import java.util.Locale;

public class ObjectMemory implements IObjectMemory {

    @Override
    public void print(int size) {
        System.out.println(String.format(Locale.US, "For collection size: %,d", size));

        try {
            System.gc();
            Thread.currentThread().sleep(10);

            long before = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            Object[] array = new Object[size];
            long after = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            for (int i = 0; i < size; i++) {
                array[i] = new Object();
            }

            long before2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            System.out.println("empty Object[]: " + (after - before) + " byte,");
            System.out.println("filled Object[]: " + ((before2 - after)) + " byte,");
            System.out.println("each Object(): " + ((before2 - after) / size) + " byte\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
