package org.example.staffskillsauditor2.skills;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.application.PortfolioQueryHandler;
import org.example.staffskillsauditor2.skills.application.StaffCommandHandler;
import org.example.staffskillsauditor2.skills.application.StaffQueryHandler;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioDTO;
import org.example.staffskillsauditor2.skills.application.dto.SkillDTO;
import org.example.staffskillsauditor2.skills.application.dto.StaffDTO;
import org.springframework.stereotype.Component;
import org.example.staffskillsauditor2.skills.application.SkillQueryHandler;
import org.example.staffskillsauditor2.skills.ui.commands.RegisterStaffMemberCommand;

@Component
@AllArgsConstructor
public class ContextFacade {
    private final SkillQueryHandler skillQueryHandler;
    private final PortfolioQueryHandler portfolioQueryHandler;
    private final StaffQueryHandler staffQueryHandler;
    private final StaffCommandHandler staffCommandHandler;

    public PortfolioDTO findPortfolioById(String portfolioId) {
        return portfolioQueryHandler.findPortfolioById(portfolioId);
    }

    public SkillDTO findSkillById(String skillId) {
        return skillQueryHandler.findSkillById(skillId);
    }

    public StaffDTO findStaffById(String staffId) {
        return staffQueryHandler.findStaffById(staffId);
    }

    public String registerStaffMember(RegisterStaffMemberCommand command) {
        return staffCommandHandler.registerStaffMember(
                command.firstName(),
                command.surname(),
                command.email(),
                command.department(),
                command.roleName(),
                command.jobLevel(),
                command.employmentType(),
                command.employmentStatus()
        );
    }
}

