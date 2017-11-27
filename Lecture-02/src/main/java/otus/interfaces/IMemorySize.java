package otus.interfaces;

public interface IMemorySize {
    long getEmptyObjectSize();

    long getEmptyStringSize();

    long getEmptyArraySize();

    void printMemoryInDynamic(int workTime);
}
