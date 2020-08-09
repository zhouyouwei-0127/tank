package com.awei.dp.strategy;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Dog[] a = {new Dog("aa",3),new Dog("cc",8),new Dog("bb",5)};
        Sorter<Dog> sorter = new Sorter<>();
        sorter.sort(a, new DogComparator());
        System.out.println(Arrays.toString(a));
    }
}
