package project.intern;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class FileManager {

    public static void saveStudents(List<Student> students, String fileName) {

        try (
                FileWriter writer = new FileWriter(fileName, true);
                CSVPrinter csvPrinter = new CSVPrinter(
                        writer,
                        CSVFormat.DEFAULT.withHeader("ID", "Name", "Age")
                )
        ) {

            for (Student student : students) {

                csvPrinter.printRecord(
                        student.getId(),
                        student.getName(),
                        student.getAge()
                );
            }

            csvPrinter.flush();

            System.out.println("Students saved successfully.");

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}


