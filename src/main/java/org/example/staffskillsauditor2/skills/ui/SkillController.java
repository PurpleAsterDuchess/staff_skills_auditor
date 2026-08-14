package org.example.staffskillsauditor2.skills.ui;

import org.example.staffskillsauditor2.skills.ContextFacade;
import org.example.staffskillsauditor2.skills.application.dto.SkillDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RequestMapping("/skills")
@RestController
@AllArgsConstructor
public class SkillController {
    private final ContextFacade facade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SkillDTO> getAllSkillDetails() {
        return facade.findAllSkills();
    }
}