package com.horizon.dao;

import com.horizon.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAO {

    // In-memory storage for students
    private final List<Student> studentList = new ArrayList<>();
    private int idCounter = 1;

    // Retrieve all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentList); // Return a copy to prevent direct modification
    }

    // Add a new student
    public void addStudent(Student student) {
        student.setId(idCounter++);
        studentList.add(student);
    }

    // Update an existing student
    public boolean updateStudent(Student updatedStudent) {
        Optional<Student> existingStudent = studentList.stream()
                .filter(student -> student.getId() == updatedStudent.getId())
                .findFirst();

        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            student.setGrade(updatedStudent.getGrade());
            return true;
        }
        return false;
    }

    // Delete a student by ID
    public boolean deleteStudent(int id) {
        return studentList.removeIf(student -> student.getId() == id);
    }

    // Find a student by ID
    public Student findStudentById(int id) {
        return studentList.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Check if a student with a given ID exists
    public boolean studentExists(int id) {
        return studentList.stream().anyMatch(student -> student.getId() == id);
    }
}
