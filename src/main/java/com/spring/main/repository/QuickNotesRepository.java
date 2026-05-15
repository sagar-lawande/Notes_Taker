package com.spring.main.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.main.entity.QuickNotes;
import com.spring.main.entity.User;

public interface QuickNotesRepository extends JpaRepository<QuickNotes, Integer> {
    List<QuickNotes> findByUser(User user);
}