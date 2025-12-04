package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private int id; 
    private String status;
    private String createdAt;
    private String updatedAt;
    
    // public boolean equals(Task task)
    // {
    //     return this.status == task.status;
    // }

    public Task(int id, String description)
    {
    this.description = description;
    this.id = id;
    status = "todo";
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    createdAt = date.format(dateFormat);
    }

    public String getDescription()
    {
        return description;
    }
    public String setDescription(String descriptionNew)
    {
        updatingDateTime();
        description = descriptionNew;
        return "";
    }

    public int getId()
    {
        return id;
    }

    public void setId(int newId)
    {
        id = newId;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String newStatus)
    {
        status = newStatus;
        updatingDateTime();
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public String getUpdatedAt()
    {
        if(updatedAt == null)
        {
            return "Is task allready is not updated";
        }
        return updatedAt;
    }

    private void updatingDateTime()
    {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        updatedAt = date.format(dateFormat);
    }

}
