package com.hibernate.todotomcat;

import jakarta.persistence.*;

//Let's Hibernate know that this class is mapped to a table in the database named "Task"
@Entity(name = "Task")
public class Task {

    //Sets the primary key with auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //Maps the task attribute to the column named "task" in the Task table
    @Column(name = "task")
    private String task;

    //Constructor
    public Task(String task) {
        this.task = task;
    }

    //Overloaded constructor
    public Task() {
    }

    //Getters and setters
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    //toString
    @Override
    public String toString() {
        return this.id + ": " + task;
    }
}
