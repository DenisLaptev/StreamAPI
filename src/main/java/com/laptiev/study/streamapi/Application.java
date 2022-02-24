package com.laptiev.study.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Denys Laptiev
 * Date: 2/17/2022
 */

//https://javarush.ru/groups/posts/2203-stream-api

//https://tproger.ru/articles/chto-nuzhno-znat-o-java-stream-api/

public class Application {



    //1) No processing will start until the TERMINAL operator is called
    //2) Stream can`t be reused after processing


    public static void main(String[] args) {
        System.out.println("Hello, worlds!");

        testStream();
    }

    public static void testStream() {

        List<String> list = new ArrayList<String>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");
        list.add("Five");
        list.add("Six");
        list.add("Seven");
        list.add("Eight");
        list.add("Nine");
        list.add("Ten");
        Stream stream = list.stream();

        System.out.println("\n---------");

        IntStream
                .of(50, 60, 70, 80, 90, 100, 110, 120)
                .filter(x -> x < 90)
                .map(x -> x + 10)
                .limit(3)
                .forEach(System.out::print);

        System.out.println("\n---------");

        IntStream
                .of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(x -> x > 5)
                .map(x -> x * 100)
                .limit(3)
                //.forEach(System.out::print);
                .forEach(x -> System.out.println(x));

        System.out.println("\n---------");

        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(x -> System.out.println(x));
    }
}
