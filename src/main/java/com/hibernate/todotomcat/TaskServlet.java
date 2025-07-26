package com.hibernate.todotomcat;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

//Handles all requests to "/tasks"
@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    //Handles GET requests to retrieve and display all the tasks
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Opens a Hibernate session
        Session session = HibernateUtility.getSessionFactory().openSession();
        //Grabs all the Task entities from the database and stores them in a List named "tasks"
        List<Task> tasks = session.createQuery("FROM Task", Task.class).list();
        //Closes the session after getting the data
        session.close();
        //Adds the "tasks" list to the request so they can be accessed in the JSP
        request.setAttribute("tasks", tasks);
        //Forwards the request to the list.jsp page for rendering
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    //Handles the POST requests for adding or deleting tasks
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Determines which action to perform, whether it's "add" or "delete"
        String action = request.getParameter("action");

        //Opens a new Hibernate session and begins a transaction
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        //Handles the "add" action
        if ("add".equals(action)) {
            //Gets the new task
            String content = request.getParameter("task");
            if (content != null && !content.trim().isEmpty()) {
                //Creates a new Task entity and saves it to the database
                Task task = new Task(content);
                session.persist(task);
            }

//            Handles the "delete" action
        } else if ("delete".equals(action)) {
            //Gets the task id to be deleted
            try {
            int id = Integer.parseInt(request.getParameter("id"));
                //Finds the task by id and if it exists then it deletes it
                Task task = session.find(Task.class, id);
                if (task != null) {
                    session.remove(task);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        //Handles the "delete" action with an SQL injection vulnerability (for education purposes)
//        } else if ("delete".equals(action)) {
//            String id = request.getParameter("id");
//            try {
//                //Basic validation
//                if (id != null && !id.trim().isEmpty()) {
//                    String sql = "DELETE FROM Task WHERE id = " + id;
//                    session.createNativeQuery(sql).executeUpdate();
//                }
//            } catch (Exception e) {
//                //Avoids crashing the app
//                System.out.println("Invalid SQL input: " + id);
//                e.printStackTrace();
//            }
//        }

        //Commits the transaction and closes the session
        tx.commit();
        session.close();

        //Sends the user back to /tasks so they can see the changes
        response.sendRedirect("tasks");
    }
}