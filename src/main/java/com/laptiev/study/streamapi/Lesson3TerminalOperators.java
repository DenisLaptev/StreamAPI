package com.laptiev.study.streamapi;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author Denys Laptiev
 * Date: 2/17/2022
 */
public class Lesson3TerminalOperators {

    public static void main(String[] args) {
        terminalOperators();
    }

    public static void terminalOperators() {

//-----------------------forEach()
        //method does some action for each element
        Stream.of("One", "Two", "Three").forEach(x -> System.out.println(x));


//-----------------------collect(Collector collector)
        //method collects elements into List, Set or other Collection
        List<String> list = Stream.of("One", "Two", "Three").collect(Collectors.toList());
        Set<String> set = Stream.of("One", "Two", "Three").collect(Collectors.toSet());

        Deque<Integer> deque = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toCollection(() -> new ArrayDeque<Integer>()));
        Set<Integer> linkedHashSet = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toCollection(() -> new LinkedHashSet<Integer>()));

//-----------------------collect(Collectors.toMap(Function keyMapper, Function valueMapper)

        //method collects stream into Map.Each elements transforms into key and value according to Function keyMapper, Function valueMapper
        //Function.identity() returns initial element
        //{1=1, 2=2, 3=3, 4=4, 5=5}
        Map<Integer, Integer> map = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toMap(
                        Function.identity(),
                        Function.identity()
                ));
        System.out.println(map);

        //{1=100, 2=200, 3=300, 4=400, 5=500}
        Map<Integer, Integer> map2 = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toMap(
                        x -> x,
                        x -> x * 100
                ));
        System.out.println(map2);


//-----------------------collect(Collectors.groupingBy(x->x.getCourse()))

        //method collects stream into Map (groups String elements by String element length).
        //Each elements transforms into (key,value)=(length,List<String>elements)
        Map<Integer,List<String>> resultMap = Stream.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")
                .collect(Collectors.groupingBy(x->x.length()));
        System.out.println(resultMap);

//-----------------------toArray(x -> new Integer[x]);

        //method collects stream into Array
        Integer[] resultArray = Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
                .toArray(x->new Integer[x]);
        System.out.println(Arrays.toString(resultArray));


//-----------------------count()
        //method returns the number of stream elements
        long elementsNumber = Stream.of("One", "Two", "Three").count();
        System.out.println(elementsNumber);

//-----------------------findFirst()
        //method returns the first element of stream
        Optional<String> firstElement = Stream.of("One", "Two", "Three").findFirst();
        System.out.println(firstElement.get());

//-----------------------allMatch(Predicate predicate)
        //method returns true if all elements match lambda condition
        Boolean allMatch = Stream.of(1, 2, 3, 4, 5).allMatch(x -> x <= 7);
        System.out.println(allMatch);

//-----------------------anyMatch(Predicate predicate)
        //method returns true if any of the elements match lambda condition
        Boolean anyMatch = Stream.of(1, 2, 3, 4, 5).anyMatch(x -> x <= 7);
        System.out.println(anyMatch);

//-----------------------noneMatch(Predicate predicate)
        //method returns true if none of the elements match lambda condition
        Boolean noneMatch = Stream.of(1, 2, 3, 4, 5).noneMatch(x -> x <= 7);
        System.out.println(noneMatch);

//-----------------------joining()
//-----------------------joining(CharSequence delimiter),
//-----------------------joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
        //method combines String elements into one String
        String a = Stream.of("s", "u", "p", "e", "r").collect(Collectors.joining());
        System.out.println(a); // super

        String b = Stream.of("s", "u", "p", "e", "r").collect(Collectors.joining("-"));
        System.out.println(b); // s-u-p-e-r

        String c = Stream.of("s", "u", "p", "e", "r").collect(Collectors.joining(" -> ", "[ ", " ]"));
        System.out.println(c);  // [ s -> u -> p -> e -> r ]


//-----------------------average()
//-----------------------getAsDouble()
        //method returns average of the stream elements
        double result = IntStream.range(2, 16)
                .average()
                .getAsDouble();
        System.out.println(result);


//-----------------------sum()
        //method returns sum of the stream elements
        long resultSum = LongStream.range(2, 16)
                .sum();
        System.out.println(resultSum);


//-----------------------summaryStatistics()
        //method returns statistics of the primitive stream elements
        LongSummaryStatistics stats = LongStream.range(2, 16)
                .summaryStatistics();

        System.out.format("  count: %d%n", stats.getCount());//14
        System.out.format("    sum: %d%n", stats.getSum());//119
        System.out.format("average: %.1f%n", stats.getAverage());//8.5
        System.out.format("    min: %d%n", stats.getMin());//2
        System.out.format("    max: %d%n", stats.getMax());//15

    }
}
