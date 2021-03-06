package otus;

import otus.helper.MyArrayHelpers;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class AppStart {
    private static final String[] stringValues = new String[] {"1", "2", "%", "Hello", "My", "ArrayList"};
    private static final Integer[] numberValues = new Integer[] {1, 2, 5, 15, 0, -8};

    private static String someText = "I hope my work will be done the first time";

    public static void main(String[] args) {
        MyArrayHelpers myArrayHelpers = new MyArrayHelpers();

        List<List<String>> myNestedStringList = new MyArrayList<>();
        List<String> myStringList = new MyArrayList<>();
        List<String> stringArr = new ArrayList<>();

        myArrayHelpers.setDefaultValues(stringArr, stringValues);
        System.out.println("'stringArr' values: " + stringArr.toString());

        myStringList.addAll(stringArr);
        myStringList.addAll(stringArr);
        System.out.println("'myStringList' values: " + myStringList.toString());

        List<String> splittedString = new ArrayList<>();
        String[] arr = someText.split(" ");
        stringArr.clear();
        for (int i = 0; i < arr.length; i++) {
            splittedString.add(arr[i]);
        }
        Collections.addAll(myNestedStringList, splittedString);
        System.out.println("Collections.addAll() 'myNestedStringList': " + myNestedStringList);

        Collections.copy(myStringList, splittedString);
        System.out.println("The first Collections.copy() 'myStringList': " + myStringList);

        Collections.copy(myStringList, splittedString);
        System.out.println("The second Collections.copy() 'myStringList': " + myStringList);

        List<String> splittedStringErr = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            splittedStringErr.add("void:" + i);
        }

        Collections.copy(myStringList, splittedStringErr);
        System.out.println("The third Collections.copy() 'myStringList': " + myStringList);

        Collections.sort(myStringList, (o1, o2) -> o1.compareToIgnoreCase(o2));
        System.out.println("Collections.sort() 'myStringList' values: " + myStringList);

        myStringList.remove(5);
        System.out.println("remove() 5 index 'myStringList' values: " + myStringList);

        myStringList.remove(0);
        System.out.println("remove() 0 index 'myStringList' values: " + myStringList + "\n");


        List<List<Integer>> myNestedIntegerList = new MyArrayList<>();
        List<Integer> myNumberList = new MyArrayList<>();
        List<Integer> integerArr = new ArrayList<>();

        myArrayHelpers.setDefaultValues(integerArr, numberValues);
        System.out.println("'integerArr' values: " + integerArr.toString());

        myNumberList.addAll(integerArr);
        myNumberList.addAll(integerArr);
        System.out.println("'myNumberList' values: " + myNumberList.toString());

        List<Integer> integerLis = new ArrayList<>();
        for (int i = 0; i < numberValues.length; i++) {
            integerLis.add(numberValues[i] * 5);
        }
        Collections.addAll(myNestedIntegerList, integerLis);
        System.out.println("'myNestedIntegerList' values: " + myNestedIntegerList);

        Collections.copy(myNumberList, integerLis);
        System.out.println("The first Collections.copy() 'myNumberList': " + myNumberList);

        Collections.copy(myNumberList, integerLis);
        System.out.println("The second Collections.copy() 'myNumberList': " + myNumberList);

        integerLis = new ArrayList<>();
        for (int i = 0; i < numberValues.length; i++) {
            integerLis.add(numberValues[i] * 0);
        }
        Collections.copy(myNumberList, integerLis);
        System.out.println("The third Collections.copy() 'myNumberList': " + myNumberList);

        Collections.sort(myNumberList, (o1, o2) -> o1 < o2 ? -1 : o1 == o2 ? 0 : 1);
        System.out.println("Collections.sort() 'myNumberList': " + myNumberList);

        myNumberList.remove(5);
        System.out.println("remove() 5 index 'myNumberList' values: " + myNumberList);

        myNumberList.remove(0);
        System.out.println("remove() 0 index 'myNumberList' values: " + myNumberList);
    }

}
