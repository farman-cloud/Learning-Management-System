package com.farman.lmsApp;

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
        if (student.getRole() == Users.Role.LEARNER) {
            enrolledStudents.add(student);
        }
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    // Added a method to display course details for better output
    public void displayCourseDetails() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Title: " + courseTitle);
        System.out.println("Description: " + courseDescription);
        System.out.println("Instructor: " + instructor.getName());
        if (lessons.isEmpty()) {
            System.out.println("Lessons: No lessons yet.");
        } else {
            System.out.println("Lessons:");
            for (Lessons lesson : lessons) {
                System.out.println("  - " + lesson.getTitle());
            }
        }
    }
}
