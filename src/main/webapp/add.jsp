<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Add Task</title></head>
<body>
<h2>Add a Task</h2>
<!-- Sends a POST request to the "tasks" servlet -->
<form action="tasks" method="post">
  <!-- Hidden field used by the servlet to determine the type of action (add/delete) -->
  <input type="hidden" name="action" value="add"/>
  <!-- Text input for the user to enter the task description -->
  <label>Task:</label> <input type="text" name="task"/>
  <!-- Submit button to send the form data -->
  <input type="submit" value="Add Task"/>
</form>
<!-- A link to go back to the task list page -->
<a href="tasks">Back to List</a>
</body>
</html>
