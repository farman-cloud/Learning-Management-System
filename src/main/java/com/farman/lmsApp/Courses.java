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
            // To avoid duplicate enrollments, we check if the student is already enrolled.
            if (!isStudentEnrolled(student)) {
                enrolledStudents.add(student);
            } else {
                System.out.println("Student " + student.getName() + " is already enrolled in this course.");
            }
        }
    }

    // A helper method to check if a student is already in the course.
    private boolean isStudentEnrolled(Users student) {
        for (Users enrolledStudent : enrolledStudents) {
            if (enrolledStudent.getEmail().equals(student.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    // Updated to display the list of enrolled students.
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
        // New section to show enrolled students.
        if (enrolledStudents.isEmpty()) {
            System.out.println("Enrolled Students: None yet.");
        } else {
            System.out.println("Enrolled Students:");
            for (Users student : enrolledStudents) {
                System.out.println("  - " + student.getName());
            }
        }
    }
}

