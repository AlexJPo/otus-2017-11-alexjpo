package otus;

import otus.helper.MemorySize;

public class AppStart {
    public static void main(String... args) {
        MemorySize memorySize = new MemorySize();

        try {
            memorySize.run();

            System.out.println("Object size is: " + memorySize.getEmptyObjectSize() + " byte");
            System.out.println("Empty String size is: " + memorySize.getEmptyStringSize() + " byte");
            System.out.println("Empty array size is: " + memorySize.getEmptyArraySize() + " byte");

            memorySize.printMemoryInDynamic(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
