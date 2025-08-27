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

    public String getCourseTitle() {
        return courseTitle;
    }
    public List<Lessons> getLessons() {
        return lessons;
    }
}
