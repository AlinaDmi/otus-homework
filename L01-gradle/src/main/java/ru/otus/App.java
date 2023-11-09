package ru.otus;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S106")
public class App {
    public static void main(String... args) {

        List<Integer> result = new ArrayList<>();
        int min = 0;
        int max = 10;
        for (int i = min; i < max; i++) {
            result.add(i);
        }

        System.out.println(Lists.reverse(result));
    }
}
