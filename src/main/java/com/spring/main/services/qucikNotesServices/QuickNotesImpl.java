package com.spring.main.services.qucikNotesServices;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.entity.QuickNotes;
import com.spring.main.entity.User;
import com.spring.main.repository.QuickNotesRepository;

@Service
public class QuickNotesImpl implements QuickNotesService {

    @Autowired
    QuickNotesRepository quickNotesRepository;

    @Override
    public void saveNote(QuickNotes note) {
        quickNotesRepository.save(note);
    }

    @Override
    public List<QuickNotes> getNotesByUser(User user) {
        return quickNotesRepository.findByUser(user);
    }
}