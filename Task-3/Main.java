import java.io.*;
import java.util.*;

class Student {
    String Name;
    int Rno;
    String Grade;
    int Age;
    int Class_;
    String Section;
    String phno;

    public Student(String Name, int Rno, String Grade, int Age, int Class_, String Section, String phno) {
        this.Name = Name;
        this.Rno = Rno;
        this.Grade = Grade;
        this.Age = Age;
        this.Class_ = Class_;
        this.Section = Section;
        this.phno = phno;
    }

    public int getRno() { return Rno; }

    @Override
    public String toString() {
        return Rno + " | " + Name + " | " + Grade + " | " + Age + " | " + Class_ + " | " + Section + " | " + phno;
    }

    // Convert student to CSV line
    public String toCSV() {
        return Rno + "," + Name + "," + Grade + "," + Age + "," + Class_ + "," + Section + "," + phno;
    }

    // Create student from CSV line
    public static Student fromCSV(String line) {
        String[] parts = line.split(",");
        return new Student(
                parts[1],
                Integer.parseInt(parts[0]),
                parts[2],
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]),
                parts[5],
                parts[6]
        );
    }
}

public class Task3 {
    private static final String FILE_NAME = "students.csv";
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        loadStudents(); // Load students from file
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent(input);
                case 2 -> removeStudent(input);
                case 3 -> searchStudent(input);
                case 4 -> displayStudents();
                case 5 -> {
                    saveStudents(); // Save before exit
                    exit = true;
                    System.out.println("Exiting... Goodbye!");
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addStudent(Scanner input) {
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        System.out.print("Enter Roll Number: ");
        int rno = input.nextInt();
        input.nextLine(); 
        System.out.print("Enter Grade: ");
        String grade = input.nextLine();
        System.out.print("Enter Age:");
        int age = input.nextInt();
        input.nextLine();
        System.out.print("Enter Class: ");
        int class_ = input.nextInt();
        input.nextLine();
        System.out.print("Enter Section: ");
        String section = input.nextLine();
        System.out.print("Enter Phone Number: ");
        String phno = input.nextLine();

        students.add(new Student(name, rno, grade, age, class_, section, phno));
        System.out.println("Student added successfully!");
        saveStudents();
    }

    private static void removeStudent(Scanner input) {
        System.out.print("Enter Roll Number to Remove: ");
        int rollToRemove = input.nextInt();
        input.nextLine();
        boolean removed = students.removeIf(s -> s.getRno() == rollToRemove);
        if (removed) {
            System.out.println("Student removed!");
            saveStudents();
        } else System.out.println("Student not found!");
    }

    private static void searchStudent(Scanner input) {
        System.out.print("Enter Roll Number to Search: ");
        int rollToSearch = input.nextInt();
        input.nextLine();
        boolean found = false;
        for (Student s : students) {
            if (s.getRno() == rollToSearch) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) System.out.println("Student not found!");
    }

    private static void displayStudents() {
        if (students.isEmpty()) System.out.println("No students available.");
        else students.forEach(System.out::println);
    }

    private static void saveStudents() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    private static void loadStudents() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                students.add(Student.fromCSV(fileScanner.nextLine()));
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }
}
