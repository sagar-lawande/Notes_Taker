package com.spring.main.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.spring.main.entity.QuickNotes;
import com.spring.main.entity.Tasks;
import com.spring.main.entity.User;
import com.spring.main.services.qucikNotesServices.QuickNotesService;
import com.spring.main.services.taskServices.TaskServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @Autowired
    private TaskServices taskServices;

    @Autowired
    private QuickNotesService quickNoteService;

    @GetMapping("/profile")
    public String openProfile(Model model, HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/openLogPage";
        }

        List<Tasks> tasks = taskServices.getTasksByUser(loggedInUser);

        long total = tasks.size();
        long completed = tasks.stream().filter(Tasks::isCompleted).count();
        long pending = total - completed;

        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("tasks", tasks);
        model.addAttribute("totalTasks", total);
        model.addAttribute("completedTasks", completed);
        model.addAttribute("pendingTasks", pending);
        model.addAttribute("todayFocus", pending);
        model.addAttribute("notes", quickNoteService.getNotesByUser(loggedInUser));

        return "profile";
    }

    @PostMapping("/addTask")
    public String addTask(@RequestParam String taskName,
                          @RequestParam String description,
                          HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        Tasks task = new Tasks();
        task.setTitle(taskName);
        task.setDescription(description);
        task.setCompleted(false);
        task.setUser(loggedInUser);

        taskServices.addTasks(task);

        return "redirect:/profile";
    }

    @PostMapping("/saveNote")
    public String saveNote(@RequestParam String content, HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        QuickNotes note = new QuickNotes();
        note.setContent(content);
        note.setUser(loggedInUser);

        quickNoteService.saveNote(note);

        return "redirect:/profile";
    }

    @PostMapping("/completeTask/{id}")
    public String completeTask(@PathVariable int id, HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Tasks task = taskServices.getTask(id).orElse(null);

        if (task != null && task.getUser().getId() == loggedInUser.getId()) {
            task.setCompleted(true);
            taskServices.addTasks(task);
        }

        return "redirect:/profile";
    }

    @PostMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable int id, HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Tasks task = taskServices.getTask(id).orElse(null);

        if (task != null && task.getUser().getId() == loggedInUser.getId()) {
            taskServices.deleteTasks(id);
        }

        return "redirect:/profile";
    }
}