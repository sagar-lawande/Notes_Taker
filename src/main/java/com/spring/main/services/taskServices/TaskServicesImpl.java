package com.spring.main.services.taskServices;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.entity.Tasks;
import com.spring.main.entity.User;
import com.spring.main.repository.TasksRepository;

@Service
public class TaskServicesImpl implements TaskServices {

    @Autowired
    private TasksRepository tasksRepository;

    @Override
    public boolean addTasks(Tasks tasks) {
        tasksRepository.save(tasks);
        return true;
    }

    @Override
    public List<Tasks> getTasksByUser(User user) {
        return tasksRepository.findByUser(user);
    }

    @Override
    public void modifyTasks() {
    }

    @Override
    public boolean deleteTasks(int id) {
        if (tasksRepository.existsById((long) id)) {
            tasksRepository.deleteById((long) id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Tasks> getTask(int id) {
        return tasksRepository.findById((long) id);
    }
}