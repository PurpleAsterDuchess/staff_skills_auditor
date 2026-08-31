CREATE TABLE skill_aggregate (
                                 id VARCHAR PRIMARY KEY,
                                 name VARCHAR(100) NOT NULL UNIQUE,
                                 description VARCHAR(500) NOT NULL,
                                 category VARCHAR(50) NOT NULL,
                                 is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE skill_portfolio (
                                 id VARCHAR PRIMARY KEY,
                                 staff_id VARCHAR NOT NULL UNIQUE,
                                 FOREIGN KEY (staff_id) REFERENCES staff_member(id) ON DELETE CASCADE
);

CREATE TABLE portfolio_entry (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 portfolio_id VARCHAR NOT NULL, -- FK Aggregate Root
                                 skill_id VARCHAR NOT NULL,     -- FK Skill Aggregate

                                 skill_level INT NOT NULL,
                                 expiration_date DATE,
                                 notes VARCHAR(1000),

                                 verification_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                                 verified_by VARCHAR,
                                 verified_on TIMESTAMP,

                                 FOREIGN KEY (portfolio_id) REFERENCES skill_portfolio(id) ON DELETE CASCADE,
                                 FOREIGN KEY (skill_id) REFERENCES skill_aggregate(id) ON DELETE RESTRICT,
                                 FOREIGN KEY (verified_by) REFERENCES staff_member(id) ON DELETE SET NULL,

    -- prevent duplicate allocations of the same skill
                                 UNIQUE (portfolio_id, skill_id)
);

-- ----------------------------------------------------------------------------
-- CQRS
-- ----------------------------------------------------------------------------

CREATE INDEX idx_portfolio_entry_pending
    ON portfolio_entry (verification_status);
CREATE INDEX idx_portfolio_entry_expired
    ON portfolio_entry (expiration_date);

CREATE INDEX idx_portfolio_entry_search
    ON portfolio_entry (portfolio_id, skill_id, skill_level);


