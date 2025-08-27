package com.example.demo;

public class Platform {
    public static void main(String[] args) {
        // Create users
        Users instructor = new Users(1, "Alice", "alice@mail.com", "pass123", Users.Role.INSTRUCTOR);
        Users student1 = new Users(2, "Bob", "bob@mail.com", "pass456", Users.Role.STUDENT);
        Users student2 = new Users(3, "Charlie", "charlie@mail.com", "pass789", Users.Role.STUDENT);

        // Instructor creates a course
        Courses javaCourse = new Courses(101, "Java Basics", "Learn Java from scratch", instructor);

        // Add lessons
        Lessons lesson1 = new Lessons(1, "Intro to Java", "Basics of Java", "http://video1.com");
        Lessons lesson2 = new Lessons(2, "OOP Concepts", "Learn about classes and objects", "http://video2.com");
        javaCourse.addLesson(lesson1);
        javaCourse.addLesson(lesson2);

        // Students enroll
        javaCourse.enrollStudent(student1);
        javaCourse.enrollStudent(student2);

        // Display details
        System.out.println(javaCourse);
        javaCourse.listLessons();
        javaCourse.listEnrolledStudents();
    }
}
