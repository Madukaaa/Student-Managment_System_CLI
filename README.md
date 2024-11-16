# Student Activity Management System
This project is a Java-based console application designed to manage student records and their academic performance across three modules during a university semester. The system aims to provide university staff with an intuitive interface to perform various operations such as registering students, managing their academic records, and generating performance reports.

## Features
1. Core Functionalities
Check Available Seats: Monitor the remaining capacity of the student intake (maximum 100 students).
Register Students: Add student records with unique IDs.
Delete Students: Remove a student's record from the system.
Search Students: Retrieve a student's details using their ID.
Save and Load Data:
Save all student records to a file.
Load saved student records back into the system.
View Students by Name: List all students alphabetically using a custom sort algorithm.

3. Enhanced Features with OOP
Introduced Student and Module classes to efficiently handle student data and module grades.
Additional options for:
Adding module marks
Calculating grades based on module averages:
Distinction: Average ≥ 80
Merit: Average ≥ 70
Pass: Average ≥ 40
Fail: Below 40

5. Reporting System
Summary Report: Provides an overview of:
Total student registrations.
Number of students who passed each module (scoring ≥ 40).
Detailed Report: A comprehensive report including:
Student details (ID, name, module marks, total, average, grade).
Sorted list based on average marks (highest to lowest).

7. Testing and Quality Assurance
Designed thorough test cases to validate each functionality.
Documented expected vs. actual outcomes for systematic debugging.
Comparative analysis of solutions (array-based vs. class-based) for better maintainability and readability.

## Technologies Used
Programming Language: Java

Development Tools: Text Editor/IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code)

File Handling: Save and retrieve student data using .txt files.

Sorting Algorithm: Bubble sort for student data ordering.

## How to Use
Clone the repository to your local machine.

Compile the Java program using a terminal or an IDE.

Run the program to access the menu-driven system.

The menu options guide users through functionalities such as managing student records, entering academic details, and generating reports.
