package ru.pro.sky;

import java.util.Arrays;

import static ru.pro.sky.Main.swapElements;

public class IntListImpl implements IntList{

    private Integer[] intList;
    private int size;
    private static final int CAPACITY = 7;

    public IntListImpl(){
        this(CAPACITY);
    }
    public IntListImpl(int capacity) {
        this.intList = new Integer[capacity];
    }

    @Override
    public Integer add(Integer item) {
        isItemNotNull(item);
        isArrayNotFull();
        intList[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        isItemNotNull(item);
        isArrayNotFull();
        isIndexValid(index);
        if (index < size) {
            for (int temp = size + 1; temp > index; temp--) {
                intList[temp] = intList[temp - 1];
            }
            intList[index] = item;
            size++;
        } else {
            intList[size++] = item;
        }
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        isItemNotNull(item);
        isIndexValid(index);
        intList[index+1] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        isItemNotNull(item);
        doesItemExist(item);
        for (int i = 0; i < size; i++) {
            if (intList[i].equals(item)) {
                return remove(i);
            }
        }
        return item;
    }

    @Override
    public Integer remove(int index) {
        isIndexValid(index);
        Integer item = get(index);
        for (int i = index; i < size - 1; i++) {
            intList[i] = intList[i + 1];
        }
        intList[size - 1] = null;
        size--;
        return item;
    }

    @Override
    public int indexOf(Integer item) {
        isItemNotNull(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(intList[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        isItemNotNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(intList[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        isIndexValid(index);
        return intList[index];
    }

    @Override
    public boolean equals(IntList otherList) {
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
        intList = new Integer[CAPACITY];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(intList, intList.length);
    }


    private void sortSelection() {
        for (int i = 0; i < size - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (intList[j] < intList[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            int tmp = intList[i];
            intList[i] = intList[minElementIndex];
            intList[minElementIndex] = tmp;
        }
    }

    private boolean binarySearch(Integer item) {
        int min = 0;
        int max = intList.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item == intList[mid]) {
                return true;
            }
            if (item < intList[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Integer item) {
        sortSelection();
        if (binarySearch(item)) {
            return true;
        }
        return false;
    }

    private void doesItemExist(Integer item) {
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
        if (size == intList.length) {
            intList = extend();
        }
    }

    private void isItemNotNull(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Объект равен null");
        }
    }

    private Integer[] extend() {
        return Arrays.copyOf(intList, size * 2);
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(intList[i] + " ");
        }
        System.out.println();
    }

    public void addArray(Integer array []){
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }
}
