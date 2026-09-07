package org.example.staffskillsauditor2.skills.ui.controllers;

import org.example.staffskillsauditor2.skills.ContextFacade;
import org.example.staffskillsauditor2.skills.application.dto.SkillDTO;
import org.example.staffskillsauditor2.skills.ui.commands.CreateSkillCommand;
import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.ui.commands.UpdateSkillCommand;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RequestMapping("/skills")
@RestController
@AllArgsConstructor
public class SkillController {

    private final ContextFacade facade;

    @GetMapping("/{skill_id}")
    @ResponseStatus(HttpStatus.OK)
    public SkillDTO getSkillById(@PathVariable String skill_id) {
        return facade.findSkillById(skill_id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SkillDTO> getAllSkills() {
        return facade.findAllSkills();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSkill(@RequestBody CreateSkillCommand command) {
        facade.createSkill(command.id(), command.name(), command.description(), command.category());
    }

    @PostMapping("/{skill_id}/deactivate")
    @ResponseStatus(HttpStatus.OK)
    public void deactivateSkill(@PathVariable String skill_id) {
        facade.deactivateSkill(skill_id);
    }

    @PostMapping("/{skill_id}/activate")
    @ResponseStatus(HttpStatus.OK)
    public void activateSkill(@PathVariable String skill_id) {
        facade.activateSkill(skill_id);
    }

    @PatchMapping("/{skill_id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSkill(
            @PathVariable("skill_id") String skillId,
            @RequestBody UpdateSkillCommand command
    ) {
        facade.updateSkill(skillId, command.name(), command.description(), command.category());
    }
}
