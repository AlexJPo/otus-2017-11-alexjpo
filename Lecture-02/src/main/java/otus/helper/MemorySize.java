package otus.helper;

import otus.model.MyClass;

public class MemorySize {
    private static long SLEEP_INTERVAL = 10;

    public void run() throws InterruptedException {
        System.gc();
        Thread.sleep(SLEEP_INTERVAL);
        Runtime runtime = Runtime.getRuntime();

        System.out.println("***** Heap utilization statistics [Byte] *****\n");
        System.out.println("Total Memory: " + runtime.totalMemory());
        System.out.println("Free Memory: " + runtime.freeMemory());
        System.out.println("Used Memory: " + (runtime.totalMemory() - runtime.freeMemory()));
        System.out.println("Max Memory: " + runtime.maxMemory());
        System.out.println("\n");
    }

    public long getEmptyObjectSize() {
        long beforeMemory = getMemoryUse();
        Object emptyString = new Object();

        long afterMemory = getMemoryUse();
        long size = (afterMemory - beforeMemory);
        return size;
    }

    public long getEmptyStringSize() {
        long beforeMemory = getMemoryUse();
        String emptyString = new String("");

        long afterMemory = getMemoryUse();
        long size = (afterMemory - beforeMemory);
        return size;
    }

    public long getEmptyArraySize() {
        long beforeMemory = getMemoryUse();
        Object[] array = new Object[0];

        long afterMemory = getMemoryUse();
        long size = (afterMemory - beforeMemory);
        return size;
    }

    public void printMemoryInDynamic(int workTime) {
        long workTimeInSeconds = workTime * 1000;
        int sleepTimeout = 1000;
        int arraySize = 5_000_000;

        System.out.println("\n");
        System.out.println("*** Change memory in dynamic ***");
        while (workTime < workTimeInSeconds) {
            freeMemory();
            long totalMemory = Runtime.getRuntime().totalMemory();
            Object[] array = new Object[arraySize];

            long freeMemory = Runtime.getRuntime().freeMemory();
            long size = (totalMemory - freeMemory);
            System.out.println("Array empty: " + (size / arraySize) + " byte");

            for (int i = 0; i < arraySize; i++) {
                array[i] = new MyClass();
            }

            size = (freeMemory - size);
            System.out.println("Array filled: " + (size / arraySize) + " byte");

            try {
                workTime += sleepTimeout;
                Thread.sleep(sleepTimeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long getMemoryUse(){
        freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        freeMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        return (totalMemory - freeMemory);
    }

    private void freeMemory() {
        try {
            System.gc();
            Thread.currentThread().sleep(SLEEP_INTERVAL);
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
