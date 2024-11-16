//Import all the packages
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    static final int maxStudents = 100;
    static Student[] students = new Student[maxStudents]; //Initializing the student object list
    static int studentCount = 0;

    public static void main(String[] args) { // Calling the main class
        Scanner input = new Scanner(System.in); //Creating the scanner object
        System.out.println("Welcome to the student management system");
        while (true) {
            mainMenu();
            System.out.print("\nEnter your choice: ");
            while (!input.hasNextInt()) {
                System.out.println("Invalid input! Enter a given value."); //Input validation
                input.next();
                System.out.print("\nEnter your choice: ");
            }
            int choice = input.nextInt(); // Getting the input
            switch (choice) { //Creating the options
                case 1:
                    checkAvailableSeats();
                    break;
                case 2:
                    registerStudent(input);
                    break;
                case 3:
                    deleteStudent(input);
                    break;
                case 4:
                    findStudent(input);
                    break;
                case 5:
                    storeStudent(input);
                    break;
                case 6:
                    loadStudent(input);
                    break;
                case 7:
                    viewStudent();
                    break;
                case 8:
                    additionalFeatures(input);
                    break;
                case 9:
                    System.out.println("Exiting the program....");
                    System.exit(0); //exit the system
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //creating the main menu method
    static void mainMenu() {
        System.out.println("\n** MENU **");
        System.out.println("1. Check available seats");
        System.out.println("2. Register student (with ID)");
        System.out.println("3. Delete student");
        System.out.println("4. Find student (with student ID)");
        System.out.println("5. Store student details into a file");
        System.out.println("6. Load student details from the file to the system");
        System.out.println("7. View the list of students based on their names");
        System.out.println("8. Additional features");
        System.out.println("9. Exit");
    }

    //Creating the additional features method
    static void additionalFeatures(Scanner input) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n** ADDITIONAL FEATURES **");
            System.out.println("a. Add student name");
            System.out.println("b. Add module marks");
            System.out.println("c. Generate a summary");
            System.out.println("d. Generate complete report");
            System.out.println("e. back to the main menu");
            System.out.print("Enter your choice: ");
            String choice = input.next().toLowerCase();

            switch (choice) {
                case "a":
                    addStudentName(input);
                    break;
                case "b":
                    addModuleMarks(input);
                    break;
                case "c":
                    generateSummary();
                    break;
                case "d":
                    generateReport();
                    break;
                case "e":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    //Creating the add student name method
    static void addStudentName(Scanner input) {
        System.out.print("Enter student ID: ");
        String id = input.next().toUpperCase();
        Student student = findStudentById(id); //Call the findStudentById method to check if the id is already exists
        if (student != null) {
            System.out.print("Enter student name: ");
            input.nextLine(); //Consume a new line
            String name = input.nextLine();
            student.setName(name); //Call the setName method in the student class
            System.out.println("Student name added successfully.");
        } else {
            System.out.println("Student with entered id not found.");
        }
    }

    //Creating the add module marks method
    static void addModuleMarks(Scanner input) {
        System.out.print("Enter student ID: ");
        String id = input.next().toUpperCase();
        Student student = findStudentById(id); //Call the findStudentById method to check if the id is already exists
        if (student != null) {
            for (int i = 0; i < 3; i++) { //Loop the entering mark part three times
                boolean validInput = false; //Input validation
                while (!validInput) {
                    System.out.print("Enter marks for Module " + (i + 1) + ": ");
                    if (input.hasNextInt()) {
                        int marks = input.nextInt();
                        if (marks >= 0 && marks <= 100) { //Check if the marks are in the correct range
                            student.setModuleMarks(i, marks); //Set module marks according to the module index
                            validInput = true;
                        } else {
                                System.out.println("Marks must be between 0 and 100. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        input.next();
                    }
                }
            }
            System.out.println("Module marks added successfully.");
        } else {
            System.out.println("Student with entered id not found.");
        }
    }

    static void generateSummary() {
        System.out.println("\nSummary of the student details:");
        System.out.println("\nTotal Student registrations: " + studentCount); //Display the student count

        int countMarks1 = 0;
        int countMarks2 = 0;
        int countMarks3 = 0;
        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            Module[] modules = student.getModules(); //Creating module object list and initializing the list by get modules method from module class

            //Get module marks of each and every module
            int marks1 = modules[0].getMarks();
            int marks2 = modules[1].getMarks();
            int marks3 = modules[2].getMarks();

            if (marks1 > 40) {
                countMarks1++; //Increase the counter
            }
            if (marks2 > 40) {
                countMarks2++; //Increase the counter
            }
            if (marks3 > 40) {
                countMarks3++; //Increase the counter
            }
        }
        System.out.println("Total no of students who are scored more than 40 marks in Module 1, 2 and 3: ");
        System.out.println("\nModule 1: " + countMarks1 + "\nModule 2: " + countMarks2 + "\nModule 3: " + countMarks3); //Print the counters
    }

    // Creating the Generate report method
    static void generateReport() {
        if (studentCount == 0) {
            System.out.println("No students registered. Please register a student first.");
            return;
        }

        bubbleSortByAverage(); //Calling the bubble sort by average method

        System.out.println("\nComplete student report:");

        for (int i = 0; i < studentCount; i++) { //Loop through the student count
            Student student = students[i];
            Module[] modules = student.getModules(); //Creating module object list and initializing the list by get modules method from module class
            double average = calculateAverage(student); //Calling the calculate average method
            String overallGrade = Module.calculateGrade(average); //Calling the calculate grade method from the module class
            double total = modules[0].getMarks()+modules[1].getMarks()+modules[2].getMarks(); //calculate the total marks

            System.out.print("Student " + (i+1) + " details:\n\n" + // Displaying the report of each student
                    "Student ID: " + student.getId() +
                    "\nStudent Name: " + student.getName() +
                    "\nModule 1 Marks: " + modules[0].getMarks() +
                    "\nModule 2 marks: " + modules[1].getMarks() +
                    "\nModule 3 marks: " + modules[2].getMarks() +
                    "\nTotal Marks: " + total +
                    "\nAverage: " + (average) +
                    "\nOverall grade: " + overallGrade);
            System.out.println("\n--------------------------------------------------");
        }
    }

    //Creating the find student by id method
    static Student findStudentById(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId().equals(id)) { //Check if the entered id is equal to one of the id from the list
                return students[i];
            }
        }
        return null;
    }

    //Creating the calculate average method
    static double calculateAverage(Student student) {
        Module[] modules = student.getModules();
        return (modules[0].getMarks() + modules[1].getMarks() + modules[2].getMarks()) / 3.0; //Get module marks from the module class and calculate the average
    }

    //Creating the bubble sort by average method
    static void bubbleSortByAverage() {
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (calculateAverage(students[j]) < calculateAverage(students[j + 1])) {

                    //creating temp variable
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    //Creating the check available seats method
    static void checkAvailableSeats() {
        System.out.println("Available seats: " + (maxStudents - studentCount));
    }

    //Creating register student method
    static void registerStudent(Scanner input) {
        if (studentCount >= maxStudents) {
            System.out.println("\nNo seats available");
        } else {
            String format = "^W\\d{7}$"; //regex format for the correct string of an id
            while (true) {
                System.out.print("Enter student ID (Ex: W1234567) : ");
                String ID = input.next().toUpperCase();
                if (ID.matches(format)) { //Check if the id matches to the format
                    if (findStudentById(ID) != null) {
                        System.out.println("Error! Student ID already exists.");
                        continue;
                    }
                    students[studentCount] = new Student(ID); //add student id to the students object list
                    studentCount++;
                    System.out.println("Student - " + ID + " has been successfully registered.");
                    break;
                } else {
                    System.out.println("Error! Check the length and the format of the ID.");
                }
            }
        }
    }

    //Creating the delete student method
    static void deleteStudent(Scanner input) {
        if (studentCount == 0) {
            System.out.println("No students registered, Please register a student");
        } else {
            boolean studentDeleted = false;
            while (!studentDeleted) {
                System.out.print("Enter the ID of the student to delete: ");
                String ID = input.next().toUpperCase();
                int index = -1;
                for (int i = 0; i < studentCount; i++) {
                    if (ID.equals(students[i].getId())) { //Check if the id equals
                        index = i;
                        break;
                    }
                }
                if (index == -1) {
                    System.out.println("Error! Student " + ID + " does not exist.");
                } else {
                    for (int i = index; i < studentCount - 1; i++) {
                        students[i] = students[i + 1];
                    }

                    students[studentCount - 1] = null;
                    studentCount--;
                    System.out.println("Student with ID " + ID + " has been successfully deleted.");
                    studentDeleted = true;
                }
            }
        }
    }

    //Creating the find student method
    static void findStudent(Scanner input) {
        System.out.print("Enter the ID of the student to find: ");
        String ID = input.next().toUpperCase();
        Student student = findStudentById(ID);
        if (student == null) {
            System.out.println("Error! Student " + ID + " does not exist.");
        } else {
            System.out.println("Student with ID " + ID + " has been found.");
            System.out.println("Student ID: " + student.getId()); //call the getId method from the student class
            System.out.println("Student Name: " + student.getName()); //call the getName method from the student class
        }
    }

    //Creating the store student method
    static void storeStudent(Scanner input) {
        boolean detailsFound = false;
        while (!detailsFound) {
            System.out.print("Do you want to store student details into a file? (Y/N): ");
            String choice = input.next().toUpperCase();
            if (choice.equals("Y")) {
                try (FileWriter fw = new FileWriter("Student details.txt")) {
                    for (int i = 0; i < studentCount; i++) {
                        Student student = students[i];

                        //Writing the data to the text file
                        fw.write(student.getId() + "," + student.getName());
                        for (Module module : student.getModules()) {
                            fw.write("," + module.getMarks());
                        }
                        fw.write("\n");
                    }
                    System.out.println("Student details have been successfully stored.");
                    detailsFound = true;
                } catch (IOException e) {
                    System.out.println("Error! Could not write to file.");
                    e.printStackTrace();
                }
            } else if (choice.equals("N")) {
                System.out.println("Returning to main menu...");
                break;
            } else {
                System.out.println("Error! Enter a valid choice");
            }
        }
    }

    //Creating the load student method
    static void loadStudent(Scanner input) {
        boolean detailsGot = false;
        while (!detailsGot) {
            System.out.print("Do you want to load student details from the text file? (Y/N): ");
            String choice = input.next().toUpperCase();
            if (choice.equals("Y")) {
                try (BufferedReader br = new BufferedReader(new FileReader("Student details.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null && studentCount < maxStudents) {
                        String[] parts = line.split(","); //Split the data of the text files
                        if (parts.length == 5) {
                            String id = parts[0];
                            // Check if a student with this ID already exists
                            if (findStudentById(id) != null) {
                                System.out.println("Error! Student with ID " + id + " is already registered in the system. Skipping this entry.");
                                continue;
                            }
                            String name = parts[1]; //Extracting the name from the text file and assign it
                            Student student = new Student(id);
                            student.setName(name);
                            for (int i = 0; i < 3; i++) {
                                student.setModuleMarks(i, Integer.parseInt(parts[i + 2]));
                            }
                            students[studentCount] = student;
                            studentCount++; //Increase the student count
                        }
                    }
                    System.out.println("students details have been successfully loaded.");
                    detailsGot = true;
                } catch (FileNotFoundException e) {
                    System.out.println("Error! Could not find the file.");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Error! Could not read the file.");
                    e.printStackTrace();
                }
            } else if (choice.equals("N")) {
                System.out.println("Returning to main menu...");
                break;
            } else {
                System.out.println("Error! Enter a valid choice");
            }
        }
    }

    //Creating the view student method
    static void viewStudent() {
        if (studentCount == 0) {
            System.out.println("No students registered. Please register a student first.");
            return;
        }

        //Calling the bubble sort method
        bubbleSort();

        System.out.println("\nList of students:");
        for (int i = 0; i < studentCount; i++) {
            System.out.println((i + 1) + ". " + students[i].getName() + " (ID: " + students[i].getId() + ")");
        }
    }

    //Creating the bubble sorting method
    static void bubbleSort() {
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (students[j].getName().compareToIgnoreCase(students[j + 1].getName()) > 0) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }
}