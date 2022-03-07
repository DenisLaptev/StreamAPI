package com.laptiev.study.streamapi;

import java.util.*;
import java.util.stream.*;

/**
 * @author Denys Laptiev
 * Date: 2/17/2022
 */
public class Lesson1StreamInitialization {

    public static void main(String[] args) {
        streamInitialization();
    }

    public static void streamInitialization() {

//-------------------------------------stream empty-----------------------------------
        Stream stream1 = Stream.empty();


//-------------------------------------stream from list-----------------------------------
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Stream<Integer> stream2 = list.stream();


//-------------------------------------stream from map-----------------------------------
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);

        Stream<Map.Entry<String, Integer>> stream3 = map.entrySet().stream();


//-------------------------------------stream from array primitives-----------------------------------
        //from primitives array we get streams: IntStream, LongStream, DoubleStream (this increases PERFORMANCE)
        int[] intArray = new int[]{1, 2, 3, 4, 5};
        IntStream stream41 = Arrays.stream(intArray);


        long[] longArray = new long[]{1L, 2L, 3L, 4L, 5L};
        LongStream stream42 = Arrays.stream(longArray);


        //stream with float doesn`t work
        float[] floatArray = new float[]{1.0f, 2.0f, 3.0f, 4.0f, 5.0f};
        System.out.println(Arrays.toString(floatArray));
        //Stream stream43 = Arrays.stream(floatArray);


        double[] doubleArray = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
        System.out.println(Arrays.toString(doubleArray));
        DoubleStream stream44 = Arrays.stream(doubleArray);


        //stream with byte doesn`t work
        byte[] byteArray = new byte[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(byteArray));
        //Stream stream45 = Arrays.stream(byteArray);


        //stream with short doesn`t work
        short[] shortArray = new short[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(shortArray));
        //Stream stream46 = Arrays.stream(shortArray);


        //stream with char doesn`t work
        char[] charArray = new char[]{'a', 'b', 'c', 'd', 'e'};
        System.out.println(Arrays.toString(charArray));
        //Stream stream47 = Arrays.stream(charArray);


        //stream with boolean doesn`t work
        boolean[] booleanArray = new boolean[]{true, false, true, true, true};
        System.out.println(Arrays.toString(booleanArray));
        //Stream stream48 = Arrays.stream(booleanArray);

//-------------------------------------stream from array containers/objects-----------------------------------

        Integer[] intContainerArray = new Integer[]{1, 2, 3, 4, 5};
        Stream<Integer> stream412 = Arrays.stream(intContainerArray);


        Long[] longContainerArray = new Long[]{1L, 2L, 3L, 4L, 5L};
        Stream<Long> stream422 = Arrays.stream(longContainerArray);


        Float[] floatContainerArray = new Float[]{1.0f, 2.0f, 3.0f, 4.0f, 5.0f};
        System.out.println(Arrays.toString(floatContainerArray));
        Stream<Float> stream432 = Arrays.stream(floatContainerArray);


        Double[] doubleContainerArray = new Double[]{1.0, 2.0, 3.0, 4.0, 5.0};
        System.out.println(Arrays.toString(doubleContainerArray));
        Stream<Double> stream442 = Arrays.stream(doubleContainerArray);


        Byte[] byteContainerArray = new Byte[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(byteContainerArray));
        Stream<Byte> stream452 = Arrays.stream(byteContainerArray);


        Short[] shortContainerArray = new Short[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(shortContainerArray));
        Stream<Short> stream462 = Arrays.stream(shortContainerArray);


        Character[] charContainerArray = new Character[]{'a', 'b', 'c', 'd', 'e'};
        System.out.println(Arrays.toString(charContainerArray));
        Stream<Character> stream472 = Arrays.stream(charContainerArray);


        Boolean[] booleanContainerArray = new Boolean[]{true, false, true, true, true};
        System.out.println(Arrays.toString(booleanContainerArray));
        Stream<Boolean> stream482 = Arrays.stream(booleanContainerArray);


        String[] stringArray = new String[]{"one", "two", "three", "four", "five"};
        Stream<String> stream49 = Arrays.stream(stringArray);


//-------------------------------------stream from elements-----------------------------------

        Stream<Integer> stream51 = Stream.of(1, 2, 3, 4, 5);

        Stream<Long> stream52 = Stream.of(1L, 2L, 3L, 4L, 5L);

        Stream<Float> stream53 = Stream.of(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);

        Stream<Double> stream54 = Stream.of(1.0, 2.0, 3.0, 4.0, 5.0);

        Stream<Byte> stream55 = Stream.of((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);

        Stream<Short> stream56 = Stream.of((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);

        Stream<Character> stream57 = Stream.of('a', 'b', 'c', 'd', 'e');

        Stream<Boolean> stream58 = Stream.of(true, false, true, true, true);

        Stream<String> stream59 = Stream.of("one", "two", "three", "four", "five");


//-------------------------------------stream parallel-----------------------------------
//to get parallel stream one needs
//1)to use parallelStream() method instead stream()
//2)or to use intermediate method parallel()

        //1)
        List<Integer> list2 = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list2.parallelStream()
                .filter(x -> x > 3)
                .map(x -> x * 2)
                .collect(Collectors.toList());


        //2)
        IntStream.range(0, 10)
                .parallel()
                .map(x -> x * 10)
                .sum();


//-------------------------------------stream concat(Stream a, Stream b)-----------------------------------
        //method concats two streams.At first Stream a elements go, then Stream b elements go.
        // 1, 2, 3, 4, 5, 6
        Stream.concat(
                Stream.of(1, 2, 3),
                Stream.of(4, 5, 6))
                .forEach(System.out::println);


//-------------------------------------IntStream.range(int startInclusive, int endExclusive)-----------------------------------
//-------------------------------------LongStream.range(long startInclusive, long endExclusive)--------------------------------

        // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        IntStream.range(0, 10)
                .forEach(x -> System.out.println(x));


        // -10, -9, -8, -7, -6
        LongStream.range(-10L, -5L)
                .forEach(x -> System.out.println(x));


//-------------------------------------IntStream.rangeClosed(int startInclusive, int endInclusive)-----------------------------------
//-------------------------------------LongStream.rangeClosed(long startInclusive, long endInclusive)--------------------------------

        // 0, 1, 2, 3, 4, 5
        IntStream.rangeClosed(0, 5)
                .forEach(x -> System.out.println(x));


        // -8, -7, -6, -5
        LongStream.rangeClosed(-8L, -5L)
                .forEach(x -> System.out.println(x));



//-------------------------------------stream generator-----------------------------------
        //method generates stream with 10 random numbers elements and prints it.
        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(x -> System.out.println(x));

        //method generates stream with 10 elements=2 and prints it.
        Stream.generate(() -> 2)
                .limit(10)
                .forEach(x -> System.out.println(x));


//-------------------------------------stream iterate(T seed, UnaryOperator f)-----------------------------------
        //method generates stream with 6 elements and prints it. First element = seed. Second element = f(seed). Third element = f(f(seed))
        //3,6,12,24,48,96
        Stream.iterate(3, x -> x * 2)
                .limit(6)
                .forEach(System.out::println);






    }
}
