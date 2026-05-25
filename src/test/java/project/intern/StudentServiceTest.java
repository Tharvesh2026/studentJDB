package project.intern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    public void testAddStudent() {

        StudentService service = new StudentService();

        Student student = new Student(1, "tharun", 20);

        service.addStudent(student);

        assertEquals(1, service.getAllStudents().size());
    }

    

    @Test
    public void testFindStudentById() {

        StudentService service = new StudentService();

        Student student = new Student(2, "Anu", 22);

        service.addStudent(student);

        Student found = service.findStudentById(2);

        assertNotNull(found);

        assertEquals("Anu", found.getName());
    }

    @Test
    public void testDeleteStudent() {

        StudentService service = new StudentService();

        Student student = new Student(3, "Samson", 21);

        service.addStudent(student);

        boolean deleted = service.deleteStudent(3);

        assertTrue(deleted);
    }

    @Test
    public void testUpdateStudent() {

        StudentService service = new StudentService();

        Student student = new Student(4, "Priya", 19);

        service.addStudent(student);

        boolean updated = service.updateStudent(4, "Priya K.", 20);

        assertTrue(updated);

        Student found = service.findStudentById(4);

        assertNotNull(found);
        assertEquals("Priya K.", found.getName());
        assertEquals(20, found.getAge());
    }
}