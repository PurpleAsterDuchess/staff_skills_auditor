package org.example.staffskillsauditor2.skills.ui;

import org.example.staffskillsauditor2.skills.ContextFacade;
import org.example.staffskillsauditor2.skills.application.dto.SkillDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RequestMapping("/skills")
@RestController
@AllArgsConstructor
public class SkillController {
    private final ContextFacade facade;

    @GetMapping("/{skill_id}")
    @ResponseStatus(HttpStatus.OK)
    public SkillDTO getSkillById(
            @PathVariable String skill_id){
                return facade.findSkillById(skill_id);
    }

}