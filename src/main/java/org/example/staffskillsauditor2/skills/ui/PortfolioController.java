package org.example.staffskillsauditor2.skills.ui;

import org.example.staffskillsauditor2.skills.ContextFacade;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/portfolio")
public class PortfolioController {
    private final ContextFacade facade;

    @GetMapping("/{portfolio_id}")
    @ResponseStatus(HttpStatus.OK)
    public PortfolioDTO getPortfolioId(@PathVariable String portfolio_id){
        return facade.findPortfolioById(portfolio_id);
    }
}
