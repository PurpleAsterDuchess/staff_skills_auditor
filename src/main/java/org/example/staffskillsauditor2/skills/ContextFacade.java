package org.example.staffskillsauditor2.skills;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.application.PortfolioQueryHandler;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioDTO;
import org.springframework.stereotype.Component;
import org.example.staffskillsauditor2.skills.application.SkillQueryHandler;


//CQRS-Qureies 23
@Component
@AllArgsConstructor
public class ContextFacade {
    private final SkillQueryHandler skillQueryHandler;
    private final PortfolioQueryHandler portfolioQueryHandler;

    public PortfolioDTO findPortfolioById(String portfolioId) {
        return portfolioQueryHandler.findPortfolioById(portfolioId);
    }
}
