package project.intern;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class FileManager {

    public static void saveStudents(List<Student> students, String fileName) {

    Path path = Paths.get(fileName);
    boolean writeHeader = true;
    try {
        if (Files.exists(path)) {
            writeHeader = Files.size(path) == 0;
        }
    } catch (IOException e) {
        // If we cannot determine the size, default to writing header to be safe
        writeHeader = true;
    }

    try (
        FileWriter writer = new FileWriter(fileName, true);
        CSVPrinter csvPrinter = new CSVPrinter(
            writer,
            writeHeader
                ? CSVFormat.DEFAULT.withHeader("ID", "Name", "Age")
                : CSVFormat.DEFAULT
        )
    ) {

            for (Student student : students) {
                writer.write(student.toString() + "\n");
            }

            System.out.println("Students saved to file successfully.");

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());
        }

        return loaded;
    }

    public static List<Student> loadStudents(String fileName) {

        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            System.out.println("File not found: " + fileName);
            return new ArrayList<>();
        }

        List<Student> loaded = new ArrayList<>();

        try (
                FileReader reader = new FileReader(fileName);
                CSVParser csvParser = new CSVParser(
                        reader,
                        CSVFormat.DEFAULT
                                .withHeader("ID", "Name", "Age")
                                .withFirstRecordAsHeader()
                                .withIgnoreHeaderCase()
                                .withTrim()
                )
        ) {

            for (CSVRecord record : csvParser) {
                try {
                    int id = Integer.parseInt(record.get("ID"));
                    String name = record.get("Name");
                    int age = Integer.parseInt(record.get("Age"));
                    loaded.add(new Student(id, name, age));
                } catch (IllegalArgumentException e) {
                    // covers missing headers and parse errors (NumberFormatException is a subtype)
                    System.out.println("Skipping invalid row in " + fileName + ": " + record);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return loaded;
    }
}



