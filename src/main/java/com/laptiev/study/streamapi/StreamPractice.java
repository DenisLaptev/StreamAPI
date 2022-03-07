package com.laptiev.study.streamapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Denys Laptiev
 * Date: 2/18/2022
 */
public class StreamPractice {

    public static void main(String[] args) {
        task1();

        task2();

        task3();

        task4();
    }


    public static void task1() {
        //take elements (employees) from list
        //select those, who is younger than 40,
        //sort by last name
        //and put into new list

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(41, "FN1", "LN1"));
        employees.add(new Employee(50, "FN5", "LN5"));
        employees.add(new Employee(25, "FN2", "LN2"));
        employees.add(new Employee(40, "FN4", "LN4"));
        employees.add(new Employee(35, "FN3", "LN3"));

        List<Employee> result = employees.stream()
                .filter(x -> x.getAge() < 40)
                .sorted(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o1.getLastName().compareTo(o2.getLastName());
                    }
                })
                .collect(Collectors.toList());

        result.stream().forEach(x -> System.out.println(x));
    }

    public static void task2() {
        //method should take HashMap of Strings
        //select elements, that contain 'a' character in value,
        //and return the sorted list of these elements

        Map<String, String> map = new HashMap<>();
        map.put("11111", "aaaaaaaaa");
        map.put("22222", "bbbbbbbbb");
        map.put("33333", "cccccccc");
        map.put("44444", "abc");
        map.put("555555", "asdfg");
        map.put("6666666", "qwewre");
        map.put("7777777", "qazwsx");

        List<String> result = map
                .entrySet()
                .stream()
                .map(x -> x.getValue())
                .filter(x -> x.contains("a"))
                .sorted()
                .collect(Collectors.toList());

        result.stream().forEach(x -> System.out.println(x));
    }

    public static void task3() {
        //method should take list of ints
        //and 1)return list of unique elements in the initial order,
        //and 2)return list of unique elements in sorted order

        List<Integer> result1 = Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(result1);

        List<Integer> result2 = Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(result2);

        List<Integer> result3 = Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println(result3);
    }


    public static void task4() {
        //method should take list of ints
        //and return map, that has 2 entrySets:
        //{
        // "unique"=List<Integer> uniqueElements,
        // "duplicates"=List<Integer> duplicateElements
        // }
        //lists should be sorted

        //List<Integer> initialList = Arrays.asList(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2);

        List<Integer> initialList = new ArrayList<>();
        initialList.add(1);
        initialList.add(2);
        initialList.add(3);
        initialList.add(1);
        initialList.add(9);
        initialList.add(2);
        initialList.add(5);
        initialList.add(3);
        initialList.add(4);
        initialList.add(8);
        initialList.add(2);
        System.out.println("initialList=" + initialList);

        List<Integer> uniqueElements = initialList.stream()
                .filter(x -> Collections.frequency(initialList, x) == 1)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("uniqueElements=" + uniqueElements);


        List<Integer> duplicateElements = initialList.stream()
                .filter(x -> Collections.frequency(initialList, x) > 1)
                .sorted()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("duplicateElements=" + duplicateElements);


        Map<String, List<Integer>> result = new HashMap<>();
        result.put("unique", uniqueElements);
        result.put("duplicates", duplicateElements);

        System.out.println("result=" + result);

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class Employee {
        private int age;
        private String firstName;
        private String lastName;
    }
}
