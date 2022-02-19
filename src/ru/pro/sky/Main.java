package ru.pro.sky;



public class Main {

    public static Integer[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        Integer[] array = new Integer[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10)+1000;
        }
        return array;}

    public static void swapElements(Integer[] data, int indexA, int indexB) {
        int tmp = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = tmp;
    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }


    public static void main(String[] args) {

        StringListImpl stringList = new StringListImpl();
        IntListImpl intList = new IntListImpl();

        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(3, 25);
        intList.print();
        System.out.println();

        intList.remove(2);
        intList.remove(0);
        intList.set(3, 14);
        intList.print();

        System.out.println(intList.indexOf(25));
        System.out.println(intList.lastIndexOf(4));
        System.out.println(intList.get(3));




        Integer arr1 []= generateRandomArray();
       //Integer arr2 [] = Arrays.copyOf(arr1, arr1.length);
       //Integer arr3[] = Arrays.copyOf(arr1, arr1.length);


       //long start1 = System.currentTimeMillis(); //~54264ms-55470ms
       //sortBubble(arr1);
       //System.out.println(System.currentTimeMillis() - start1);
       //System.out.println();

       //long start2 = System.currentTimeMillis(); //~6906ms
       //sortSelection(arr2);
       //System.out.println(System.currentTimeMillis() - start2);

       //long start3 = System.currentTimeMillis(); //~30357ms
       //sortInsertion(arr3);
       //System.out.println(System.currentTimeMillis() - start2);

       // intList.sortSelection(arr1);
       // intList.print();

        intList.addArray(arr1); // добавляю сгенерированные числа в лист
        intList.print(); // что получилось
        System.out.println();
        System.out.println(intList.contains(25));
        intList.clear();
}
}