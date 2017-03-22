package com.hackerrank.java.datastruct;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * https://www.hackerrank.com/challenges/java-priority-queue
 */
public class JavaPriorityQueue {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalEvents = Integer.parseInt(in.nextLine());
        PriorityQueue<Student> queue = new PriorityQueue<Student>(
                Comparator.comparingDouble(Student::getCgpa).reversed()
                .thenComparing(Student::getFname)
                .thenComparingInt(Student::getToken));

        while(totalEvents > 0) {
            String event = in.next();
            switch (event) {
                case "ENTER":
                    String name = in.next();
                    double cgpa = in.nextDouble();
                    int token = in.nextInt();
                    queue.add(new Student(token, name, cgpa));
                    break;
                case "SERVED":
                    queue.poll();
                    break;
            }

            totalEvents--;
        }
        in.close();

        if(queue.isEmpty()) {
            System.out.println("EMPTY");
        }
        while(!queue.isEmpty()) {
            System.out.println(queue.poll().getFname()); /* use poll method to get the element based on priority */
        }
    }
}

class Student {
    private int token;
    private String fname;
    private double cgpa;

    public Student(int id, String fname, double cgpa) {
        super();
        this.token = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }

    public int getToken() {
        return token;
    }

    public String getFname() {
        return fname;
    }

    public double getCgpa() {
        return cgpa;
    }
}
