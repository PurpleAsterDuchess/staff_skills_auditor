package org.example.staffskillsauditor2.skills.application.exceptions;

public class PortfolioNotFoundException extends RuntimeException {
    public PortfolioNotFoundException(String portfolio_id) {
        super(portfolio_id);
    }
}

