package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Courses {
    private int courseId;
    private String courseTitle;
    private String courseDescription;
    private Users instructor;
    private List<Lessons> lessons;
    private List<Users> enrolledStudents;

    public Courses(int courseId, String courseTitle, String courseDescription, Users instructor) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.instructor = instructor;
        this.lessons = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
    }

    public void addLesson(Lessons lesson) {
        lessons.add(lesson);
    }

    public void enrollStudent(Users student) {
        if (student.getRole() == Users.Role.STUDENT) {
            enrolledStudents.add(student);
        }
    }

    public void listLessons() {
        System.out.println("\nLessons in course: " + courseTitle);
        for (Lessons lesson : lessons) {
            System.out.println(" - " + lesson);
        }
    }

    public void listEnrolledStudents() {
        System.out.println("\nStudents enrolled in: " + courseTitle);
        for (Users student : enrolledStudents) {
            System.out.println(" - " + student.getName());
        }
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + courseId + ", title='" + courseTitle + "', instructor=" + instructor.getName() + "}";
    }
}
