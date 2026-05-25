package project.intern;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student findStudentById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public boolean deleteStudent(int id) {

        Student student = findStudentById(id);

        if (student != null) {
            students.remove(student);
            return true;
        }

        return false;
    }

    public void setStudents(List<Student> students) {
        this.students = new ArrayList<>(students);
    }
}