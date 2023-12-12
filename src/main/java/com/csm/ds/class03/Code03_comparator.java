package com.csm.ds.class03;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Comparator：比较器
 * o1 - o2,
 * 对于所有的比较器而言，
 * 返回负数，第一个参数排前面
 * 返回正数，第二个参数排前面
 * 返回 0 ，两数相等无所谓前后
 */
public class Code03_comparator {
    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", age=" + age +
                    '}';
        }
    }

    //o1比o2小，o1在前升序
    public static class IdAscendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }

    }

    //o1比o2小，o2在前降序
    public static class IdDescendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o2.id - o1.id;
        }

    }

    public static void main(String[] args) {
        Student a = new Student("A", 2, 15);
        Student b = new Student("B", 5, 45);
        Student c = new Student("C", 8, 30);
        Student[] students = {a, b, c};
        Arrays.sort(students, new IdAscendingComparator());
        System.out.println(Arrays.toString(students));
        Arrays.sort(students, (x, y) -> Integer.compare(y.age, x.age));
        System.out.println(Arrays.toString(students));
    }


}
