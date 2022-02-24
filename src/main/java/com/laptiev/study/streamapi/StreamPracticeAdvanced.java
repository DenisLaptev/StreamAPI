package com.laptiev.study.streamapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Denys Laptiev
 * Date: 2/23/2022
 */
public class StreamPracticeAdvanced {


    //https://annimon.com/article/2778

    public static void main(String[] args) {

        task1();

        System.out.println("-------------");

        task2();

        System.out.println("-------------");

        task3();

        System.out.println("-------------");

        task4();

        System.out.println("-------------");

        task5();

        System.out.println("-------------");

        task6();

        System.out.println("-------------");

        task7();

        System.out.println("-------------");
    }

    //Given: array in format [key1,value1,key2,value2,...,keyN,valueN]. Need: to get Map in format {key=value}.
    public static void task1() {
        String[] array = new String[]{"key1", "value1", "key2", "value2", "key3", "value3", "key4", "value4"};

        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < array.length - 1; i += 2) {
            result.put(array[i], array[i + 1]);
        }

        result.entrySet().stream()
                .forEach(x -> System.out.println(x));
    }

    //Given: Map in format {key=value}. Need: to get array in format [key1,value1,key2,value2,...,keyN,valueN].
    public static void task2() {

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        //Solution 1
        List<String> result = new ArrayList<>();

        List<String> resultKeys = map.entrySet().stream()
                .map(x -> x.getKey())
                .collect(Collectors.toList());

        List<String> resultValues = map.entrySet().stream()
                .map(x -> x.getValue())
                .collect(Collectors.toList());


        for (int i = 0; i < resultKeys.size(); i++) {
            result.add(resultKeys.get(i));
            result.add(resultValues.get(i));
        }

        String[] result1 = result.stream()
                .toArray(x -> new String[x]);
        System.out.println(Arrays.toString(result1));


        //Solution 2
        String[] result2 = map.entrySet().stream()
                .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
                .toArray(x -> new String[x]);
        System.out.println(Arrays.toString(result2));
    }

    //Given: list of Students. Student class has name, Speciality, course.
    //Need: to get Map in format {course=List<Student> students}.
    public static void task3() {

        List<Student> students = initializeList();

        //Solution 1
        List<Integer> courses = students.stream()
                .map(x -> x.getCourse())
                .sorted()
                .distinct()
                .collect(Collectors.toList());

        Map<Integer, List<Student>> result = new HashMap<>();

        for (Integer course : courses) {
            List<Student> courseStudents = new ArrayList<>();
            for (Student student : students) {
                if (student.getCourse() == course) {
                    courseStudents.add(student);
                }
            }
            result.put(course, courseStudents);
        }

        System.out.println("courses=" + courses);
        System.out.println("result=" + result);


        //Solution 2
        students.stream()
                .collect(Collectors.groupingBy(x -> x.getCourse()))
                .forEach((key, value) -> System.out.println(key.toString() + '=' + value.toString()));

        Map<Integer, List<Student>> resultMap = students.stream()
                .collect(Collectors.groupingBy(x -> x.getCourse()));
        System.out.println(resultMap);

    }

    //Given: list of Students. Student class has name, Speciality, course.
    //Need: to get List of Specialities in alphabetic order.
    public static void task4() {

        List<Student> students = initializeList();

        List<String> specialities = students.stream()
                .map(x -> x.getSpeciality())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        specialities.stream()
                .forEach(x -> System.out.println(x));
    }

    //Given: list of Students. Student class has name, Speciality, course.
    //Need: to get Map. (key,value)=(speciality,numberOfStudents).
    public static void task5() {

        List<Student> students = initializeList();


        //Solution 1
        Map<String, List<Student>> map = students.stream()
                .collect(Collectors.groupingBy(x -> x.getSpeciality()));

        Map<String, Integer> result = map.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().toString(),
                        e -> e.getValue().size()
                ));

        System.out.println(result);


        //Solution 2
        students.stream()
                .collect(Collectors.groupingBy(x -> x.getSpeciality()))
                .forEach((key, value) -> System.out.println(key + ": " + value.size()));
    }

    //Given: list of Students. Student class has name, Speciality, course.
    //Need: to group students by speciality and then group students by course.
    public static void task6() {
        //TODO: This task is unfinished because of the high complexity
        List<Student> students = initializeList();

        Map<String, List<Student>> specialityMap = students.stream()
                .collect(Collectors.groupingBy(x -> x.getSpeciality()));

        System.out.println(specialityMap);

        Map<String, Map<Integer, List<String>>> result = new HashMap<>();
        for (Map.Entry<String, List<Student>> entry : specialityMap.entrySet()) {
            String speciality = entry.getKey();
            List<Student> specialityStudents = entry.getValue();


            Map<Integer, List<Student>> courseMap = specialityStudents.stream()
                    .collect(Collectors.groupingBy(x -> x.getCourse()));
            //TODO: hard task
        }
    }

    //Given: list of Students. Student class has name, Speciality, course.
    //Need: to check is there any student with course=3 for any speciality except 'Physics' && 'ComputerScience'.
    public static void task7() {

        List<Student> students = initializeList();

        Boolean result = students.stream()
                .anyMatch(x -> x.getCourse() == 3 && !("Physics".equals(x.getSpeciality())) && !("ComputerScience".equals(x.getSpeciality())));

        System.out.println("result=" + result);

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Student {
        private String name;
        private String speciality;
        private int course;
    }

    public static List<Student> initializeList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alex", "Physics", 1));
        students.add(new Student("Rika", "Biology", 4));
        students.add(new Student("Julia", "Biology", 2));
        students.add(new Student("Steve", "History", 4));
        students.add(new Student("Mike", "Finance", 1));
        students.add(new Student("Hinata", "Biology", 2));
        students.add(new Student("Richard", "History", 1));
        students.add(new Student("Kate", "Psychology", 2));
        students.add(new Student("Sergey", "ComputerScience", 4));
        students.add(new Student("Maximilian", "ComputerScience", 3));
        students.add(new Student("Tim", "ComputerScience", 5));
        students.add(new Student("Ann", "Psychology", 1));
        return students;
    }
}
