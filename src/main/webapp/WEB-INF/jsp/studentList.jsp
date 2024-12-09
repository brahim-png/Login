<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/standard/jsp/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Student Directory</h2>
    <div class="text-end mb-3">
        <a href="addStudent" class="btn btn-success">Add Student</a>
        <a href="logout" class="btn btn-danger">Logout</a>
    </div>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Grade</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${studentList}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.age}</td>
                <td>${student.grade}</td>
                <td>
                    <a href="editStudent?id=${student.id}" class="btn btn-primary btn-sm">Edit</a>
                    <a href="deleteStudent?id=${student.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Handle case where studentList is empty -->
    <c:if test="${empty studentList}">
        <div class="alert alert-warning text-center">No students found.</div>
    </c:if>

</div>
</body>
</html>