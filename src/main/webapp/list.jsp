<%@ page import="com.hibernate.todotomcat.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>To-Do List</title></head>
<body>
<h2>Your To-Do List</h2>
<%
    //Gets the list of Task objects set by the servlet
    List<com.hibernate.todotomcat.Task> tasks = (List<Task>) request.getAttribute("tasks");
    //Checks if the list is not null and loops through each task to render it to the web page
    if (tasks != null) {
%>
<form action="tasks" method="post" style="display:inline;">
    <label>Enter ID to delete:</label>
    <!--  The input field where user will enter the id of a desired task to delete  -->
    <input type="text" name="id"/>
    <!-- Button to delete a task-->
    <button type="submit">Delete</button>
    <ul>
        <%
            for (com.hibernate.todotomcat.Task task : tasks) {
        %>
        <li>
            <!-- Displays the task id and content -->
                <%= task.getId() %>:
                <%= task.getTask() %>
            <!-- Hidden fields for the delete action -->
            <input type="hidden" name="action" value="delete"/>
            <input type="hidden" name="id" value="<%= task.getId() %>"/>
                <%
        } //This is the end of the for loop
    %>
    </ul>
</form>
</li>

<%
    } //This is the end of the null checker
%>
<!-- This is a link to the add.jsp page where a new task can be added -->
<a href="add.jsp">Add a New Task</a>
</body>
</html>