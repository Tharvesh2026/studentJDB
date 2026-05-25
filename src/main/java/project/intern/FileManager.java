package project.intern;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void saveStudents(List<Student> students, String fileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("ID,Name,Age");
            writer.newLine();

            for (Student student : students) {
                writer.write(student.getId() + "," + student.getName() + "," + student.getAge());
                writer.newLine();
            }

            writer.flush();
            System.out.println("Students saved to file successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static List<Student> loadStudents(String fileName) {

        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            System.out.println("File not found: " + fileName);
            return new ArrayList<>();
        }

        List<Student> loaded = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line == null) {
                return loaded;
            }

            boolean isHeader = line.trim().equalsIgnoreCase("ID,Name,Age");
            if (!isHeader) {
                parseRow(line, loaded, fileName);
            }

            while ((line = reader.readLine()) != null) {
                parseRow(line, loaded, fileName);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return loaded;
    }

    private static void parseRow(String line, List<Student> loaded, String fileName) {
        String[] tokens = line.split(",", -1);
        if (tokens.length < 3) {
            System.out.println("Skipping invalid row in " + fileName + ": " + line);
            return;
        }

        try {
            int id = Integer.parseInt(tokens[0].trim());
            String name = tokens[1].trim();
            int age = Integer.parseInt(tokens[2].trim());
            loaded.add(new Student(id, name, age));
        } catch (NumberFormatException e) {
            System.out.println("Skipping invalid row in " + fileName + ": " + line);
        }
    }
}



