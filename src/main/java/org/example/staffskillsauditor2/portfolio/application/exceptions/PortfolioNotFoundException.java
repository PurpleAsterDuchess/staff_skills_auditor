package org.example.staffskillsauditor2.portfolio.application.exceptions;

public class PortfolioNotFoundException extends RuntimeException {
    public PortfolioNotFoundException(String portfolio_id) {
        super(portfolio_id);
    }
}

