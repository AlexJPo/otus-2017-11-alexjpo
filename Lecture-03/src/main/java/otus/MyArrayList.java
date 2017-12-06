package otus;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyArrayList<T> implements List<T>, RandomAccess {
    transient T[] array;

    public int size() {
        return array.length;
    }

    public boolean isEmpty() {
        throw new RuntimeException();
    }

    public boolean contains(Object o) {
        throw new RuntimeException();
    }

    public Iterator iterator() {
        throw new RuntimeException();
    }

    public Object[] toArray() {
        throw new RuntimeException();
    }

    public boolean add(Object o) {
        if (array == null) {
            array = (T[]) new Object[0];
        }
        array  = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = (T)o;
        return true;
    }

    public boolean remove(Object o) {
        throw new RuntimeException();
    }

    public boolean addAll(Collection<? extends T> c) {
        try {
            if (array == null) {
                array = (T[]) new Object[0];
                array = (T[])c.toArray();
                return true;
            }

            T[] temp = (T[])c.toArray();
            for (int i = 0; i < temp.length; i++) {
                add(temp[i]);
            }
            return true;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean addAll(int index, Collection c) {
        throw new RuntimeException();
    }

    public void replaceAll(UnaryOperator operator) {
        throw new RuntimeException();
    }

    public void sort(Comparator c) {
        Arrays.sort(array, c);
    }

    public void clear() {
        throw new RuntimeException();
    }

    @Override
    public T get(int index) {
        if (index > array.length || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " +  array.length);
        }
        return array[index];
    }

    @Override
    public T set(int index, Object element) {
        if (index > array.length || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " +  array.length);
        }
        T old = array[index];
        array[index] = (T)element;
        return old;
    }

    public void add(int index, Object element) {
        throw new RuntimeException();
    }

    public T remove(int index) {
        throw new RuntimeException();
    }

    public int indexOf(Object o) {
        throw new RuntimeException();
    }

    public int lastIndexOf(Object o) {
        throw new RuntimeException();
    }

    public ListIterator listIterator() {
        return listIterator;
    }

    public ListIterator listIterator(int index) {
        throw new RuntimeException();
    }

    public List subList(int fromIndex, int toIndex) {
        throw new RuntimeException();
    }

    public boolean retainAll(Collection c) {
        throw new RuntimeException();
    }

    public boolean removeAll(Collection c) {
        throw new RuntimeException();
    }

    public boolean containsAll(Collection c) {
        throw new RuntimeException();
    }

    public Object[] toArray(Object[] a) {
        throw new RuntimeException();
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    ListIterator listIterator = new ListIterator() {
        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < array.length ? true : false;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                position++;
                return array[position-1];
            }
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {
            if (position < array.length) {
                array[position-1] = (T)o;
            }
        }

        @Override
        public void add(Object o) {

        }
    };
}
