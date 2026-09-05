package org.example.staffskillsauditor2.skills.ui.controllers;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.ContextFacade;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioDTO;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioEntryDTO;
import org.example.staffskillsauditor2.skills.ui.commands.AllocateSkillCommand;
import org.example.staffskillsauditor2.skills.ui.commands.EditSkillCommand;
import org.example.staffskillsauditor2.skills.ui.commands.VerifySkillCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/portfolio")
@RestController
@AllArgsConstructor
public class PortfolioController {

    private final ContextFacade facade;

    @GetMapping("/{portfolio_id}")
    @ResponseStatus(HttpStatus.OK)
    public PortfolioDTO getPortfolioById(@PathVariable String portfolio_id) {
        return facade.findPortfolioById(portfolio_id);
    }

    @PostMapping("/{staff_id}/skills")
    @ResponseStatus(HttpStatus.CREATED)
    public void allocateSkill(
            @PathVariable String staff_id,
            @RequestBody AllocateSkillCommand command
    ) {
        facade.allocateSkillToPortfolio(staff_id, command.skillId(), command.skillLevel(), command.notes());
    }

    @PutMapping("/{staff_id}/skills/{skill_id}")
    @ResponseStatus(HttpStatus.OK)
    public void editSkill(
            @PathVariable String staff_id,
            @PathVariable String skill_id,
            @RequestBody EditSkillCommand command
    ) {
        facade.editSkillInPortfolio(staff_id, skill_id, command.skillLevel(), command.notes());
    }

    @PostMapping("/{portfolio_id}/skills/{skill_id}/verify")
    @ResponseStatus(HttpStatus.OK)
    public void verifySkill(
            @PathVariable String portfolio_id,
            @PathVariable String skill_id,
            @RequestBody VerifySkillCommand command
    ) {
        facade.verifySkillInPortfolio(portfolio_id, skill_id, command.verifiedBy());
    }

    @GetMapping("/pending")
    @ResponseStatus(HttpStatus.OK)
    public List<PortfolioEntryDTO> getPendingSkills() {
        return facade.findPendingSkills();
    }

    @GetMapping("/expired")
    @ResponseStatus(HttpStatus.OK)
    public List<PortfolioEntryDTO> getExpiredSkills() {
        return facade.findExpiredSkills();
    }

}
