package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Platform {
    private List<Users> users;
    private List<Courses> courses;

    public Platform() {
        this.users = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void registerUser(Users user) {
        users.add(user);
        System.out.println("Registered: " + user);
    }

    public void createCourse(Courses course) {
        courses.add(course);
        System.out.println("Created course: " + course);
    }

    public void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Courses c : courses) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {
        Platform platform = new Platform();

        // Create users
        Users instructor = new Users(1, "Alice", "alice@mail.com", "pass123", Users.Role.INSTRUCTOR);
        Users student = new Users(2, "Bob", "bob@mail.com", "pass456", Users.Role.STUDENT);

        platform.registerUser(instructor);
        platform.registerUser(student);

        // Instructor creates a course
        Courses javaCourse = new Courses(101, "Java Basics", "Learn Java from scratch", instructor);
        platform.createCourse(javaCourse);

        // Add lesson to course
        Lessons lesson1 = new Lessons(1, "Intro to Java", "Basics of Java", "http://video1.com");
        javaCourse.addLesson(lesson1);

        // Student enrolls
        javaCourse.enrollStudent(student);

        // List courses
        platform.listCourses();
    }
}
