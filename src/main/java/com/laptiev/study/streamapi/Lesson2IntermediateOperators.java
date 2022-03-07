package com.laptiev.study.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Denys Laptiev
 * Date: 2/17/2022
 */
public class Lesson2IntermediateOperators {

    public static void main(String[] args) {
        intermediateOperators();
    }

    public static void intermediateOperators() {

//-----------------------filter(Predicate predicate)
        //method filters stream, it remains only those elements, for which lambda condition in predicate=true
        Stream.of("One", "Two", "Three")
                .filter(x -> x.toString().length() == 3)
                .forEach(x -> System.out.println(x));


        System.out.println("=================================");


        //.filter(x -> Collections.frequency(initialList, x) > 1) - filters if element is present more than ones
        List<Integer> initialList = Arrays.asList(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2);
        initialList.stream()
                .filter(x -> Collections.frequency(initialList, x) > 1)
                .sorted()
                .distinct()
                .forEach(x -> System.out.println(x));


//-----------------------map(Function mapper)
        //method transforms element using lambda (Function<T,R>). Function<T,R> - is a function transforming T object into R object
        Stream.of("One", "Two", "Three")
                .map(x -> x + "[modified]")
                .forEach(x -> System.out.println(x));


        System.out.println("=================================");


//-----------------------limit(long maxSize)
        //method remains only first maxSize elements of stream
        Stream.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")
                .limit(5)
                .forEach(x -> System.out.println(x));


        System.out.println("=================================");


//-----------------------skip(long n)
        //method skips first n elements of stream
        Stream.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")
                .skip(5)
                .forEach(x -> System.out.println(x));


        System.out.println("=================================");


//-----------------------distinct()
        //method removes duplicates from the stream
        Stream.of("One", "One", "Two", "Three", "Four", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Ten", "Ten")
                .distinct()
                .forEach(x -> System.out.println(x));


        System.out.println("=================================");

//-----------------------sorted()
        //method sorts elements of stream
        Stream.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")
                .sorted()
                .forEach(x -> System.out.println(x));


        System.out.println("=================================");

//-----------------------flatMapToInt(Function<T, Stream<R>> mapper)
        //method transforms each element into 0, one or many elements
        // 0, 1, 0, 1, 2, 0, 0, 1, 2
        Stream.of(2, 3, 0, 1, 3)
                .flatMapToInt(x -> IntStream.range(0, x))
                .forEach(x -> System.out.println(x));


        System.out.println("=================================");


//-----------------------peek(Consumer action)
        //method makes action on each element and returns initial element
        List<Integer> integerList = new ArrayList<>();
        IntStream.range(0, 10)
                .peek(x -> System.out.println("peek: " + x))
                .peek(x -> integerList.add(x))
                .map(x -> x * 100)
                .forEach(x -> System.out.println(x));

        System.out.println(integerList);

        System.out.println("=================================");


//-----------------------takeWhile(Predicate predicate) (Java9)
//-----------------------dropWhile(Predicate predicate) (Java9)



//-----------------------boxed()
        //method converts primitive type to object type
        DoubleStream.of(0.1, Math.PI)
                .boxed()
                .map(x -> x.getClass())
                .forEach(x -> System.out.println(x));
        // class java.lang.Double
        // class java.lang.Double





    }
}
