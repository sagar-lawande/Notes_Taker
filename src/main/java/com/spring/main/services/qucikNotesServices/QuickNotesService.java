package com.spring.main.services.qucikNotesServices;

import java.util.List;
import com.spring.main.entity.QuickNotes;
import com.spring.main.entity.User;

public interface QuickNotesService {
    void saveNote(QuickNotes note);
    List<QuickNotes> getNotesByUser(User user);
}