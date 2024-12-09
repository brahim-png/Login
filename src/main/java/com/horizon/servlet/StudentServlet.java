package com.horizon.servlet;

import com.horizon.model.Student;
import com.horizon.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private List<Student> studentList;

    @Override
    public void init() throws ServletException {
        studentList = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            request.setAttribute("studentList", studentList);
            request.getRequestDispatcher("WEB-INF/jsp/studentList.jsp").forward(request, response);
        } else if (action.equals("add")) {
            request.getRequestDispatcher("WEB-INF/jsp/addStudent.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = getUserById(id);  // Using the new method here
            if (student != null) {
                request.setAttribute("student", student);
                request.getRequestDispatcher("WEB-INF/jsp/editStudent.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // Handle not found case
            }
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            studentList.removeIf(student -> student.getId() == id);
            response.sendRedirect("studentList");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("add")) {
            String name = request.getParameter("name");
            String ageStr = request.getParameter("age");
            String grade = request.getParameter("grade");

            if (ValidationUtil.isNotEmpty(name) && ValidationUtil.isNumeric(ageStr)) {
                int age = Integer.parseInt(ageStr);
                int id = studentList.size() + 1; // Assuming IDs are sequential and start from 1
                Student newStudent = new Student(id, name, age, grade);
                studentList.add(newStudent);
                response.sendRedirect("studentList");
            } else {
                // Handle validation errors
                request.setAttribute("errorMessage", "Invalid input data.");
                request.getRequestDispatcher("WEB-INF/jsp/addStudent.jsp").forward(request, response);
            }
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String ageStr = request.getParameter("age");
            String grade = request.getParameter("grade");

            if (ValidationUtil.isNotEmpty(name) && ValidationUtil.isNumeric(ageStr)) {
                int age = Integer.parseInt(ageStr);
                Student studentToUpdate = getUserById(id); // Fetch the existing student

                if (studentToUpdate != null) {
                    studentToUpdate.setName(name);
                    studentToUpdate.setAge(age);
                    studentToUpdate.setGrade(grade);
                    response.sendRedirect("studentList");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND); // Handle not found case
                }
            } else {
                // Handle validation errors
                request.setAttribute("errorMessage", "Invalid input data.");
                request.setAttribute("student", getUserById(id)); // Re-fetch for the edit form
                request.getRequestDispatcher("WEB-INF/jsp/editStudent.jsp").forward(request, response);
            }
        }
    }

    private Student getUserById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null; // Return null if no matching student is found
    }
}