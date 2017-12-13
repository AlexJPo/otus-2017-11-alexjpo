package otus;

import java.util.ArrayList;

public class Benchmark implements BenchmarkMBean {

    public void run() {

        int timeout = 1000;
        int size = 1_000_000;

        try {
            ArrayList<String> array = new ArrayList();
            while(true) {
                System.out.println("Array add");
                for (int i = 0; i < size; i++) {
                    array.add(new String(""));
                }
                System.out.println("Array size: " + array.size());

                System.out.println("Remove 1000");
                for (int i = 0; i < 1000; i++) {
                    array.remove(i);
                }
                System.out.println("Array size: " + array.size());

                Thread.sleep(timeout);
            }
        } catch (Exception ex) {
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
