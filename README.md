# Learning Management System (LMS)

A simple Learning Management System (LMS) built with Java and the Spring Framework. 
This application demonstrates core Spring concepts like Dependency Injection (DI) and Inversion of Control (IoC) to manage application objects (beans) in a dynamic way.

## Overview

This project is a command-line application that simulates a basic LMS. It supports two types of users: Instructors and Learners.

* Instructors can create new courses and add lessons to them.

* Learners can view the list of available courses and enroll in them.

The application uses an in-memory storage approach, meaning all data (users, courses, enrollments) persists only for the duration of a single application run.

## Features

* User Management: Create multiple users with distinct roles (Instructor or Learner).

* Role-Based Access: A simple login system directs users to different menus based on their role.

* Course Creation: Instructors can create an unlimited number of courses.

* Lesson Management: Instructors can add multiple lessons to any course they've created.

* Student Enrollment: Learners can browse and enroll in available courses.

* Dynamic Object Creation: Utilizes Spring's "prototype" scope to dynamically create new instances of users, courses, and lessons based on user input.

## Technologies Used

* Java: The core programming language.

* Spring Framework: Used for Dependency Injection and managing the application's object lifecycle (beans).

* Gradle / Maven: For project building and managing dependencies.

## Project Structure

The project consists of three main parts:

### 1. POJOs (Plain Old Java Objects): These are the model classes that represent the application's data.

* Users.java: Represents a user with properties like name, email, and role.

* Courses.java: Represents a course with a title, instructor, lessons, and enrolled students.

* Lessons.java: Represents a single lesson within a course.

### 2. Spring Configuration:

* beans.xml: An XML configuration file that tells the Spring container how to create and manage the application's objects (beans). It defines the blueprints for Users, Courses, and Lessons and sets their scope to prototype.

### 3. Application Logic:

* LmsApplication.java: The main entry point of the application. It contains the logic for user interaction, menu navigation, and communication with the Spring container to get new object instances.

## Prerequisites

* Before you begin, ensure you have the following installed on your system:

* Java Development Kit (JDK): Version 17 or higher.

* An IDE: IntelliJ IDEA, Eclipse, or VS Code is recommended.

A Build Tool: Gradle or Maven, which is typically integrated with modern IDEs.

## How to Run the Project

1. Clone the Repository:

```
git clone <your-repository-url>
```
2. Open in Your IDE:

* Open your IDE (e.g., IntelliJ IDEA).

* Select "Open" and navigate to the cloned project's root directory.

* The IDE should automatically detect it as a Gradle or Maven project and download the necessary dependencies (like Spring).

3. Build the Project:

* Most IDEs will build the project automatically. If not, you can force a build using the build tool options (e.g., in IntelliJ, Build > Build Project).

4. Run the Application:

* Navigate to the LmsApplication.java file.

* Right-click on the file and select Run 'LmsApplication.main()'.

* The application will start running in your IDE's console.

## How to Use the Application

Once the application is running, you will be greeted with the main menu.

1. Create Users:

* Select option 1. Create a New User.

* Enter the required details (name, email, password).

* Specify the role as either Learner or Instructor (case-insensitive).

* You can create as many users as you like.

2. Login:

* Select option 2. Login.

* A list of all created users will be displayed.

* Enter the number corresponding to the user you wish to log in as.

3. Instructor Menu:

* If you log in as an Instructor, you will see options to:

    * Create a new course.

    * Add a lesson to an existing course.

    * View all courses and their details.

    * Logout to return to the main menu.

4. Learner Menu:

* If you log in as a Learner, you will see options to:

    * View all available courses.

    * Enroll in a course.

    * Logout to return to the main menu.

5. Exit:

* From the main menu, select 3. Exit Application to stop the program.
