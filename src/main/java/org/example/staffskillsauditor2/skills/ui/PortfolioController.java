package org.example.staffskillsauditor2.skills.ui;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.ContextFacade;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/portfolio")
@RestController
@AllArgsConstructor
public class PortfolioController {
    private final ContextFacade facade;

    @GetMapping("/{portfolio_id}")
    @ResponseStatus(HttpStatus.OK)
    public PortfolioDTO getPortfolioById(
            @PathVariable String portfolio_id){
        return facade.findPortfolioById(portfolio_id);
    }
}
