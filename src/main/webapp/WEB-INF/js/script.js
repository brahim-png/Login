// script.js

// Example of form validation using JavaScript (optional)
document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            if (username === '' || password === '') {
                alert('Username and password cannot be empty!');
                event.preventDefault();
            }
        });
    }

    const studentForm = document.getElementById('studentForm');
    if (studentForm) {
        studentForm.addEventListener('submit', function(event) {
            const name = document.getElementById('name').value;
            const age = document.getElementById('age').value;
            const grade = document.getElementById('grade').value;
            if (name === '' || age === '' || grade === '') {
                alert('All fields are required!');
                event.preventDefault();
            }
        });
    }
});
