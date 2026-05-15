package com.spring.main.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.main.entity.Tasks;
import com.spring.main.entity.User;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByUser(User user);
}