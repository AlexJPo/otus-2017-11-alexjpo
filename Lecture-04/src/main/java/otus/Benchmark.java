package otus;

import java.util.LinkedList;

public class Benchmark implements BenchmarkMBean {

    public void run() {
        LinkedList<Object> array = new LinkedList();
        int size = 1_000_000;
        int outOfMemory = 25_000;

        try {
            while(true) {
                System.out.println("Array add: 1_000_000");
                for (int i = 0; i < size; i++) {
                    array.add(new String(""));
                }
                System.out.println("Array size: " + array.size());
                Thread.sleep(1000);

                System.out.println("Remove " + outOfMemory);
                for (int i = 0; i < outOfMemory; i++) {
                    array.remove(i);
                }
                System.out.println("Array size: " + array.size());
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            System.out.println("Array size: " + array.size());
            ex.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void setSize(int size) {

    }
}
