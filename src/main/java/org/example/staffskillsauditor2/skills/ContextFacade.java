package org.example.staffskillsauditor2.skills;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.application.handlers.PortfolioCommandHandler;
import org.example.staffskillsauditor2.skills.application.handlers.PortfolioQueryHandler;
import org.example.staffskillsauditor2.skills.application.handlers.SkillQueryHandler;
import org.example.staffskillsauditor2.skills.application.handlers.StaffCommandHandler;
import org.example.staffskillsauditor2.skills.application.handlers.StaffQueryHandler;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioDTO;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioEntryDTO;
import org.example.staffskillsauditor2.skills.application.dto.SkillDTO;
import org.example.staffskillsauditor2.skills.application.dto.StaffDTO;
import org.example.staffskillsauditor2.skills.ui.commands.RegisterStaffMemberCommand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ContextFacade {

    private final SkillQueryHandler skillQueryHandler;
    private final PortfolioQueryHandler portfolioQueryHandler;
    private final StaffQueryHandler staffQueryHandler;
    private final StaffCommandHandler staffCommandHandler;
    private final PortfolioCommandHandler portfolioCommandHandler;

//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF', 'USER')")
    public PortfolioDTO findPortfolioById(String portfolioId) {
        return portfolioQueryHandler.findPortfolioById(portfolioId);
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF', 'USER')")
    public SkillDTO findSkillById(String skillId) {
        return skillQueryHandler.findSkillById(skillId);
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF', 'USER')")
    public Iterable<SkillDTO> findAllSkills() {
        return skillQueryHandler.findAllSkills();
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF', 'USER')")
    public StaffDTO findStaffById(String staffId) {
        return staffQueryHandler.findStaffById(staffId);
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
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

//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF', 'USER')")
    public void allocateSkillToPortfolio(String staffId, String skillId, int level, String notes) {
        portfolioCommandHandler.allocateSkillToPortfolio(staffId, skillId, level, notes);
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF', 'USER')")
    public void editSkillInPortfolio(String staffId, String skillId, int level, String notes) {
        portfolioCommandHandler.editSkillInPortfolio(staffId, skillId, level, notes);
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public void verifySkillInPortfolio(String staffId, String skillId, String verifiedBy) {
        portfolioCommandHandler.verifySkillInPortfolio(staffId, skillId, verifiedBy);
    }

    public void unverifySkillInPortfolio(String staffId, String skillId, String unverifiedBy) {
        portfolioCommandHandler.unverifySkillInPortfolio(staffId, skillId, unverifiedBy);
    }

    public void rejectSkillInPortfolio(String staffId, String skillId, String rejectedBy) {
        portfolioCommandHandler.rejectSkillInPortfolio(staffId, skillId, rejectedBy);
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public List<PortfolioEntryDTO> findPendingSkills() {
        return portfolioQueryHandler.findPendingSkills();
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public List<PortfolioEntryDTO> findExpiredSkills() {
        return portfolioQueryHandler.findExpiredSkills();
    }
}
