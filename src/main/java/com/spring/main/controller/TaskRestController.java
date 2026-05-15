package com.spring.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.main.entity.Tasks;
import com.spring.main.entity.User;
import com.spring.main.services.taskServices.TaskServices;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    @Autowired
    private TaskServices taskServices;

    // Add Task for logged-in user
    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody Tasks tasks, HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return ResponseEntity.status(401).body("Please login first");
        }

        tasks.setUser(loggedInUser);
        boolean status = taskServices.addTasks(tasks);

        if (status) {
            return ResponseEntity.ok("Task added successfully");
        } else {
            return ResponseEntity.status(500).body("Task not added");
        }
    }

    // Get only logged-in user's tasks
    @GetMapping
    public ResponseEntity<List<Tasks>> getAllTasks(HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return ResponseEntity.status(401).build();
        }

        List<Tasks> taskList = taskServices.getTasksByUser(loggedInUser);
        return ResponseEntity.ok(taskList);
    }

    // Get Task By Id
    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable int id, HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Tasks task = taskServices.getTask(id).orElse(null);

        if (task != null && task.getUser().getId() == loggedInUser.getId()) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update Task
    @PutMapping("/{id}")
    public ResponseEntity<Tasks> modifyTasks(@PathVariable int id,
                                             @RequestBody Tasks tasks,
                                             HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Tasks oldTask = taskServices.getTask(id).orElse(null);

        if (oldTask != null && oldTask.getUser().getId() == loggedInUser.getId()) {
            oldTask.setTitle(tasks.getTitle());
            oldTask.setDescription(tasks.getDescription());
            oldTask.setCompleted(tasks.isCompleted());

            taskServices.addTasks(oldTask);

            return ResponseEntity.ok(oldTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Task
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id, HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Tasks task = taskServices.getTask(id).orElse(null);

        if (task != null && task.getUser().getId() == loggedInUser.getId()) {
            taskServices.deleteTasks(id);
            return ResponseEntity.ok("Task deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Task not found");
        }
    }
}