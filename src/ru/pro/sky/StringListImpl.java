package ru.pro.sky;
import java.util.Arrays;


public class StringListImpl implements StringList {

    private String[] list;
    private int size;
    private static final int CAPACITY = 10;

    public StringListImpl() {
        this(CAPACITY);
    }

    public StringListImpl(int capacity) {
        this.list = new String[capacity];
    }

    @Override
    public String add(String item) {
        isItemNotNull(item);
        isArrayNotFull();
        list[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        isArrayNotFull();
        isItemNotNull(item);
        isIndexValid(index);
        if (index < size) {
            for (int temp = size+1; temp > index; temp --) {
                list[temp] = list[temp - 1];
            } list [index] = item;
            size++;
        } else {
            list [size++] = item;
        } return item;
    }

    @Override
    public String set(int index, String item) {
        isItemNotNull(item);
        isIndexValid(index);
        list[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        isItemNotNull(item);
        doesItemExist(item);
        for (int i = 0; i < size; i++) {
            if (list [i]. equals(item)) {
                return remove(i);
            }
        }
        return item;
    }

    @Override
    public String remove(int index) {
        isIndexValid(index);
        String item = get(index);
        for (int i=index; i<size-1; i++) {
            list [i] = list [i+1];
        }
        list [size-1] = null;
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(String item) {
        isItemNotNull(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        isItemNotNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        isIndexValid(index);
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            return false;
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals((otherList.get(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        list = new String[CAPACITY];
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, list.length);
    }

    private void doesItemExist(String item) {
        if (indexOf(item) == -1) {
            throw new IllegalArgumentException("Объект не найден");
        }
    }

    private void isIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Неправильный индекс");
        }
    }

    private void isArrayNotFull() {
        if (size == list.length) {
            list = extend();
        }
    }

    private void isItemNotNull(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Объект равен null");
        }
    }
    private String[] extend() {
        return Arrays.copyOf(list, size * 2);
    }

    public void printStringList() {
        for (int i = 0; i < size; i++) {
            System.out.print(list[i] + ",");
        }
        System.out.println();

    }
}
