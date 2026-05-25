package project.intern;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class FileManager {

    public static void saveStudents(List<Student> students, String fileName) {

        try (FileWriter writer = new FileWriter(fileName)) {

            for (Student student : students) {
                writer.write(student.toString() + "\n");
            }

            System.out.println("Students saved to file successfully.");

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}



