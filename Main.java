package com.example;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;


public class Main {
    static ArrayList<Task> tasks = new ArrayList<Task>();
    static Scanner scan = new Scanner(System.in);
    static Scanner scan1 = new Scanner(System.in);
    static Gson gson = new Gson();
    static int idCount;
    public static void main(String[] args) {
        
        //deserialization of JSON into Java objects
        try (FileReader reader = new FileReader("taskWriter.json"))  
        {
            Type listType = new TypeToken<ArrayList<Task>>() {}.getType();
            tasks = gson.fromJson(reader, listType);
            
            //ID check 
            for (Task elem : tasks) {

                if(elem.getId() > idCount)
                {
                    idCount = elem.getId();
                }
            }
            } catch(Exception e)
            {
            System.out.println("Error reading JSON: " + e.getMessage());
            }
            

        do{ 
                
                System.out.println("\nWhat do you want to do? \n 1) New task \n 2)Look your Tasks \n 3)Update task\n 4) Delete task");
                int choosingOpt = scan.nextInt();
                
                switch(choosingOpt) 
                {
                    case 1:
                        createNewTask();
                        break;
                    case 2:
                        lookTasks();
                        break;
                    case 3:
                        updateTasks();
                        break;
                    case 4:
                        deleteTasks();
                        break;
                        default:
                            break;
                }
            }while (true);           
    }


    


    //Function for creating new task
    public static void createNewTask()
    {
        System.out.println("Write your task");
        String newTask = scan1.nextLine();
        idCount++;
        tasks.add(0, new Task(idCount, newTask));
        saveJsonFile();
    }

    //Function for looking tasks
    static public void lookTasks()
    {
        System.out.println("\nYour all tasks:");
        displayOfAllTasks();
        System.out.println("\nChoose list of tastk\n 1) Only todo \n 2) Only done \n 3) Only in progress");
        int choosingOpt = scan.nextInt();

        switch (choosingOpt) {
            case 1:
                System.out.println("\nHere your todo tasks\n");
                for (Task elem : tasks)
                {
                if(elem.getStatus().equals("todo"))
                {
                    System.out.println(elem.getId() + ") " + elem.getDescription() + "     " + "Status - " + elem.getStatus() + "     " + "\nCreated at - " + elem.getCreatedAt() + "     " + "Updated at - " + elem.getUpdatedAt() + "\n");      
                }
                }
                break;
            case 2:
                System.out.println("\nHere your done tasks");
                for (Task elem : tasks)
                {
                if(elem.getStatus().equals("done"))
                {
                    System.out.println(elem.getId() + ") " + elem.getDescription() + "     " + "Status - " + elem.getStatus() + "     " + "\nCreated at - " + elem.getCreatedAt() + "     " + "Updated at - " + elem.getUpdatedAt() + "\n");   
                }
                }
                break;
            case 3:
                System.out.println("\nHere your in-progress tasks");
                for (Task elem : tasks)
                {
                if(elem.getStatus().equals("in-progress"))
                {
                    System.out.println(elem.getId() + ") " + elem.getDescription() + "     " + "Status - " + elem.getStatus() + "     " + "\nCreated at - " + elem.getCreatedAt() + "     " + "Updated at - " + elem.getUpdatedAt() + "\n");   
                }
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    //Function for updating tasks
    public static void updateTasks()
    {
        System.out.println("\nWich task do you want to update?");
        displayOfAllTasks();
        int choosingOpt = scan.nextInt();

        for (int i = 0; i < tasks.size(); i++) {
            if(choosingOpt == tasks.get(i).getId())
            {
                System.out.println("\nWhat do you want to update? \n 1) Description \n 2) Status");
                int choosingOptForUpdate = scan.nextInt();
                switch (choosingOptForUpdate) {
                    case 1:
                        System.out.println("\n Write new description");
                        String updatedDescription = scan1.nextLine();
                        tasks.get(i).setDescription(updatedDescription);
                        saveJsonFile();
                        break;
                    case 2:
                        System.out.println("\n Choos new status \n 1) Todo \n 2) done \n 3) in-progress");

                        int choosingOptForStatus = scan1.nextInt();
                        switch (choosingOptForStatus) {
                            case 1:
                                tasks.get(i).setStatus("todo");
                                break;
                            case 2:
                                tasks.get(i).setStatus("done");
                                break;
                            case 3:
                                tasks.get(i).setStatus("in-progress");
                                break;
                            default:
                                saveJsonFile();
                                throw new AssertionError();
                        }
                        
                        saveJsonFile();
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
    }

    //Function for deleting tasks
    public static void deleteTasks()
    {
        System.out.println("\nWich task do you want to delete?");
        displayOfAllTasks();
        int choosingOpt = scan.nextInt();

        for (int i = 0; i < tasks.size(); i++) { 
                if(choosingOpt == tasks.get(i).getId())
                {
                    tasks.remove(i);
                }
        }
        saveJsonFile();
        

    }

    //Function for showing all tasks
    public static void displayOfAllTasks()
    {
        for (Task elem : tasks) {
            System.out.println(elem.getId() + ") " + elem.getDescription() + "     " + "Status - " + elem.getStatus() + "     " + "\nCreated at - " + elem.getCreatedAt() + "     " + "Updated at - " + elem.getUpdatedAt() + "\n");
        }
    }

    //Function for saving tasks to Json file
    public static void saveJsonFile()
    {
        try (FileWriter writer = new FileWriter("taskWriter.json"))
        {
            gson.toJson(tasks, writer);
            System.out.println("Все ок");
        } catch(Exception e)
        {
            System.out.println("Ошибка записи JSON: " + e.getMessage());
        }
    }

    
}