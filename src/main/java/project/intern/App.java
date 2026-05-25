package project.intern;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentService service = new StudentService();

        while (true) {

            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Save to File");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();

                    Student student = new Student(id, name, age);

                    service.addStudent(student);

                    System.out.println("Student added successfully.");

                    break;

                case 2:

                    List<Student> students = service.getAllStudents();

                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {

                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }

                    break;

                case 3:

                    System.out.print("Enter Student ID: ");
                    int searchId = scanner.nextInt();

                    Student found = service.findStudentById(searchId);

                    if (found != null) {
                        System.out.println("Student Found: " + found);
                    } else {
                        System.out.println("Student not found.");
                    }

                    break;

                case 4:

                    System.out.print("Enter Student ID to update: ");
                    int updateId = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter new Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter new Age: ");
                    int newAge = scanner.nextInt();

                    boolean updated = service.updateStudent(updateId, newName, newAge);

                    if (updated) {
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }

                    break;

                case 5:

                    System.out.print("Enter Student ID to delete: ");

                    int deleteId = scanner.nextInt();

                    boolean deleted = service.deleteStudent(deleteId);

                    if (deleted) {
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }

                    break;

                case 6:

                    FileManager.saveStudents(
                            service.getAllStudents(),
                            "students.csv"
                    );

                    break;

                case 7:

                    System.out.println("Exiting application.");

                    scanner.close();

                    System.exit(0);

                default:

                    System.out.println("Invalid choice.");
            }
        }
    }
}