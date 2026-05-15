package com.spring.main.services.taskServices;

import java.util.List;
import java.util.Optional;
import com.spring.main.entity.Tasks;
import com.spring.main.entity.User;

public interface TaskServices {

    boolean addTasks(Tasks tasks);

    List<Tasks> getTasksByUser(User user);

    void modifyTasks();

    boolean deleteTasks(int id);

    Optional<Tasks> getTask(int id);
}