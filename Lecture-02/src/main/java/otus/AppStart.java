package otus;

import otus.helper.ObjectFactory;
import otus.interfaces.IObjectMemory;

/*
For run jar:
    java -Xmx512m -Xms512m -XX:-UseTLAB -Djava.awt.headless=true -jar target\L02-memory.jar
*/
public class AppStart {
    private static int ONE_THOUSAND = 1_000;
    private static int TEN_THOUSAND = 10_000;
    private static int ONE_HUNDRED_THOUSAND = 100_000;
    private static int FIVE_HUNDRED_THOUSAND = 500_000;
    private static int ONE_MILLIONS = 1_000_000;
    private static int FIVE_MILLIONS = 5_000_000;

    public static void main(String... args) {
        ObjectFactory objectFactory = new ObjectFactory();

        IObjectMemory objectMemory = objectFactory.getObject("object");
        System.out.println("***** Change Object() size *****");
        objectMemory.print(ONE_THOUSAND);
        objectMemory.print(TEN_THOUSAND);
        objectMemory.print(ONE_HUNDRED_THOUSAND);
        objectMemory.print(FIVE_HUNDRED_THOUSAND);
        objectMemory.print(ONE_MILLIONS);
        objectMemory.print(FIVE_MILLIONS);

        IObjectMemory stringMemory = objectFactory.getObject("string");
        System.out.println("***** Change String() size *****");
        stringMemory.print(ONE_THOUSAND);
        stringMemory.print(TEN_THOUSAND);
        stringMemory.print(ONE_HUNDRED_THOUSAND);
        stringMemory.print(FIVE_HUNDRED_THOUSAND);
        stringMemory.print(ONE_MILLIONS);
        stringMemory.print(FIVE_MILLIONS);

        IObjectMemory customClassMemory = objectFactory.getObject("custom");
        System.out.println("***** Change MyClass() size *****");
        customClassMemory.print(ONE_THOUSAND);
        customClassMemory.print(TEN_THOUSAND);
        customClassMemory.print(ONE_HUNDRED_THOUSAND);
        customClassMemory.print(FIVE_HUNDRED_THOUSAND);
        customClassMemory.print(ONE_MILLIONS);
        customClassMemory.print(FIVE_MILLIONS);
    }
}
