package com.farman.lmsApp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LmsApplication {
    // These lists will act as our in-memory database for the application's session.
    private static final List<Users> usersList = new ArrayList<>();
    private static final List<Courses> coursesList = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    public static void main(String[] args) {
        System.out.println("---------------------LMS Application Started--------------------------");

        // Main application loop. This will run until the user chooses to exit.
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create a New User");
            System.out.println("2. Login");
            System.out.println("3. Exit Application");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("\nThank you for using the LMS!");
                    ((ClassPathXmlApplicationContext) context).close(); // Close the context
                    sc.close();
                    return; // Exit the main method, terminating the application.
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // A dedicated method to handle user creation.
    private static void createUser() {
        System.out.println("\n--- Create New User ---");
        System.out.print("Enter Name: ");
        String userName = sc.nextLine();
        System.out.print("Enter Email: ");
        String mail = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        Users.Role roleEnum;
        while (true) {
            System.out.print("Enter Role (Learner/Instructor): ");
            String roleInput = sc.nextLine().toUpperCase();
            if (roleInput.equals("LEARNER") || roleInput.equals("INSTRUCTOR")) {
                roleEnum = Users.Role.valueOf(roleInput);
                break;
            } else {
                System.out.println("Invalid role. Please enter 'Learner' or 'Instructor'.");
            }
        }

        Users newUser = (Users) context.getBean("userBean", userName, mail, password, roleEnum);
        usersList.add(newUser);
        System.out.println("User '" + newUser.getName() + "' created successfully!");
    }

    // A simple login method that lets you select a created user.
    private static void login() {
        System.out.println("\n--- User Login ---");
        if (usersList.isEmpty()) {
            System.out.println("No users have been created yet. Please create a user first.");
            return;
        }

        System.out.println("Please select a user to login:");
        for (int i = 0; i < usersList.size(); i++) {
            Users user = usersList.get(i);
            System.out.println((i + 1) + ". " + user.getName() + " (" + user.getRole() + ")");
        }
        System.out.print("Enter your choice: ");
        int userChoice = getIntInput();

        if (userChoice > 0 && userChoice <= usersList.size()) {
            Users currentUser = usersList.get(userChoice - 1);
            System.out.println("\nWelcome, " + currentUser.getName() + "!");
            if (currentUser.getRole() == Users.Role.INSTRUCTOR) {
                showInstructorMenu(currentUser);
            } else {
                showLearnerMenu(currentUser);
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // The menu for logged-in instructors.
    private static void showInstructorMenu(Users instructor) {
        while (true) {
            System.out.println("\n--- Instructor Menu ---");
            System.out.println("1. Create a New Course");
            System.out.println("2. Add Lesson to a Course");
            System.out.println("3. View All Courses");
            System.out.println("4. Logout");
            System.out.print("Please enter your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Create New Course ---");
                    System.out.print("Enter Course ID: ");
                    int courseId = getIntInput();
                    System.out.print("Enter Course Title: ");
                    String courseTitle = sc.nextLine();
                    System.out.print("Enter Course Description: ");
                    String courseDesc = sc.nextLine();
                    Courses newCourse = (Courses) context.getBean("courseBean", courseId, courseTitle, courseDesc, instructor);
                    coursesList.add(newCourse);
                    System.out.println("Successfully created course: '" + newCourse.getCourseTitle() + "'");
                    break;
                case 2:
                    addLessonToCourse();
                    break;
                case 3:
                    viewAllCourses();
                    break;
                case 4:
                    return; // Return to the main menu
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // The menu for logged-in learners.
    private static void showLearnerMenu(Users learner) {
        while (true) {
            System.out.println("\n--- Learner Menu ---");
            System.out.println("1. View All Courses");
            System.out.println("2. Enroll in a Course");
            System.out.println("3. Logout");
            System.out.print("Please enter your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    viewAllCourses();
                    break;
                case 2:
                    enrollInCourse(learner);
                    break;
                case 3:
                    return; // Return to the main menu
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // New method to handle learner enrollment.
    private static void enrollInCourse(Users learner) {
        System.out.println("\n--- Enroll in a Course ---");
        if (coursesList.isEmpty()) {
            System.out.println("No courses available to enroll in.");
            return;
        }
        System.out.println("Select a course to enroll in:");
        for (int i = 0; i < coursesList.size(); i++) {
            System.out.println((i + 1) + ". " + coursesList.get(i).getCourseTitle());
        }
        System.out.print("Enter course number: ");
        int courseChoice = getIntInput();

        if (courseChoice > 0 && courseChoice <= coursesList.size()) {
            Courses selectedCourse = coursesList.get(courseChoice - 1);
            selectedCourse.enrollStudent(learner);
            System.out.println("Successfully enrolled in '" + selectedCourse.getCourseTitle() + "'!");
        } else {
            System.out.println("Invalid course selection.");
        }
    }

    private static void addLessonToCourse() {
        System.out.println("\n--- Add Lesson to Course ---");
        if (coursesList.isEmpty()) {
            System.out.println("No courses available. Please create a course first.");
            return;
        }
        System.out.println("Select a course to add a lesson to:");
        for (int i = 0; i < coursesList.size(); i++) {
            System.out.println((i + 1) + ". " + coursesList.get(i).getCourseTitle());
        }
        System.out.print("Enter course number: ");
        int courseChoice = getIntInput();

        if (courseChoice > 0 && courseChoice <= coursesList.size()) {
            Courses selectedCourse = coursesList.get(courseChoice - 1);
            System.out.print("Enter Lesson ID: ");
            int lessonId = getIntInput();
            System.out.print("Enter Lesson Title: ");
            String lessonTitle = sc.nextLine();
            System.out.print("Enter Lesson Content: ");
            String lessonContent = sc.nextLine();
            System.out.print("Enter Lesson Video URL: ");
            String lessonVideo = sc.nextLine();
            Lessons newLesson = (Lessons) context.getBean("lessonBean", lessonId, lessonTitle, lessonContent, lessonVideo);
            selectedCourse.addLesson(newLesson);
            System.out.println("Lesson '" + newLesson.getTitle() + "' added to course '" + selectedCourse.getCourseTitle() + "'");
        } else {
            System.out.println("Invalid course selection.");
        }
    }

    private static void viewAllCourses() {
        System.out.println("\n--- All Courses ---");
        if (coursesList.isEmpty()) {
            System.out.println("No courses have been created yet.");
        } else {
            for (Courses course : coursesList) {
                course.displayCourseDetails();
                System.out.println("--------------------");
            }
        }
    }

    // Helper method to safely read an integer and handle input mismatch errors.
    private static int getIntInput() {
        while (true) {
            try {
                int input = Integer.parseInt(sc.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}

