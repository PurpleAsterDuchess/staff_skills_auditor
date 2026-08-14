package org.example.staffskillsauditor2.skills.application;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.persistance.repositories.SkillRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SkillQueryHandler {
    private SkillRepository skillRepository;


}
