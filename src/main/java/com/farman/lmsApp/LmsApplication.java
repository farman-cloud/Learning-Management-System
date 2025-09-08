package com.farman.lmsApp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LmsApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // Store the courses created in a list to access them later.
        List<Courses> coursesList = new ArrayList<>();

        System.out.println("---------------------LmsApplication started--------------------------");

        // === Step 1: User creation ===
        System.out.println("First, let's create a user profile.");
        System.out.print("Enter Name: ");
        String userName = sc.nextLine();
        System.out.print("Enter Email: ");
        String mail = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        Users.Role roleEnum;
        while (true) {
            System.out.print("Enter Role (Learner/Instructor): ");
            String roleInput = sc.next().toUpperCase();
            if (roleInput.equals("LEARNER") || roleInput.equals("INSTRUCTOR")) {
                roleEnum = Users.Role.valueOf(roleInput);
                break;
            } else {
                System.out.println("Invalid role. Please enter either 'Learner' or 'Instructor'.");
            }
        }
        sc.nextLine(); // Consume the leftover newline

        // Get a new user bean from Spring, passing constructor arguments.
        // This is a cleaner way than setting system properties.
        Users currentUser = (Users) context.getBean("userBean", userName, mail, password, roleEnum);
        System.out.println("\nWelcome, " + currentUser.getName() + "!");


        // === Step 2: Main application loop based on role ===
        if (currentUser.getRole() == Users.Role.INSTRUCTOR) {
            while (true) {
                System.out.println("\n--- Instructor Menu ---");
                System.out.println("1. Create a New Course");
                System.out.println("2. Add Lesson to a Course");
                System.out.println("3. View All Courses");
                System.out.println("4. Exit");
                System.out.print("Please enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (choice == 1) {
                    System.out.println("\n--- Create New Course ---");
                    System.out.print("Enter Course ID: ");
                    int courseId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Course Title: ");
                    String courseTitle = sc.nextLine();
                    System.out.print("Enter Course Description: ");
                    String courseDesc = sc.nextLine();

                    // Get a new course bean. Notice we pass arguments directly.
                    // The current user (instructor) is passed as a reference.
                    Courses newCourse = (Courses) context.getBean("courseBean", courseId, courseTitle, courseDesc, currentUser);
                    coursesList.add(newCourse);
                    System.out.println("Successfully created course: '" + newCourse.getCourseTitle() + "'");

                } else if (choice == 2) {
                    System.out.println("\n--- Add Lesson to Course ---");
                    if (coursesList.isEmpty()) {
                        System.out.println("No courses available. Please create a course first.");
                        continue;
                    }
                    System.out.println("Select a course to add a lesson to:");
                    for (int i = 0; i < coursesList.size(); i++) {
                        System.out.println((i + 1) + ". " + coursesList.get(i).getCourseTitle());
                    }
                    System.out.print("Enter course number: ");
                    int courseChoice = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    if (courseChoice > 0 && courseChoice <= coursesList.size()) {
                        Courses selectedCourse = coursesList.get(courseChoice - 1);

                        System.out.print("Enter Lesson ID: ");
                        int lessonId = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        System.out.print("Enter Lesson Title: ");
                        String lessonTitle = sc.nextLine();
                        System.out.print("Enter Lesson Content: ");
                        String lessonContent = sc.nextLine();
                        System.out.print("Enter Lesson Video URL: ");
                        String lessonVideo = sc.nextLine();

                        // Get a new lesson bean from the context
                        Lessons newLesson = (Lessons) context.getBean("lessonBean", lessonId, lessonTitle, lessonContent, lessonVideo);
                        selectedCourse.addLesson(newLesson);
                        System.out.println("Lesson '" + newLesson.getTitle() + "' added to course '" + selectedCourse.getCourseTitle() + "'");
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                } else if (choice == 3) {
                    System.out.println("\n--- All Courses ---");
                    if (coursesList.isEmpty()) {
                        System.out.println("No courses have been created yet.");
                    } else {
                        for(Courses course : coursesList) {
                            course.displayCourseDetails();
                            System.out.println("--------------------");
                        }
                    }
                } else if (choice == 4) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        } else { // Role is LEARNER
            System.out.println("\nHello Learner " + currentUser.getName() + "!");
            System.out.println("As a learner, you can view the list of available courses.");
            // In a real application, you would fetch courses from a database.
            // For now, this role has limited functionality.
            if(coursesList.isEmpty()){
                System.out.println("No courses available yet.");
            } else {
                for(Courses course : coursesList){
                    course.displayCourseDetails();
                }
            }
        }

        sc.close();
        System.out.println("\nThank you for using the LMS!");
        ((ClassPathXmlApplicationContext) context).close(); // Close the context
    }
}
