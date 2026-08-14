--------------------------------
-- Aggregates
--------------------------------
INSERT INTO skill_aggregate (id, name, description, category, is_active) VALUES
                                                                             ('SKL1', 'Java Programming', 'Core Java syntax, OOP, Concurrency, and JVM performance tuning.', 'Technical', TRUE),
                                                                             ('SKL2', 'Spring Boot Microservices', 'Building cloud-native REST APIs, Spring Data, and security frameworks.', 'Technical', TRUE),
                                                                             ('SKL3', 'Domain-Driven Design (DDD)', 'Strategic mapping, aggregates, value objects, and bounded contexts.', 'Technical', TRUE),
                                                                             ('SKL4', 'Agile Leadership', 'Facilitating Scrum teams, sprint planning, and coaching team members.', 'Leadership', TRUE),
                                                                             ('SKL5', 'Technical Presentations', 'Delivering clean, impact-oriented slides and project architectures.', 'Soft Skills', TRUE),
                                                                             ('SKL6', 'SQL Database Tuning', 'Writing highly optimised queries, indexing structures, and relational schemas.', 'Technical', FALSE); -- Deactivated Skill


-- Skill Portfolios for John and Sarah
INSERT INTO skill_portfolio (id, staff_id) VALUES
                                               ('PTF1', 'STF1'), -- John Doe's Portfolio
                                               ('PTF2', 'STF2'); -- Sarah Jane's Portfolio

--------------------------------
-- portfolio data
--------------------------------

-- Allocate Skills into Portfolios
INSERT INTO portfolio_entry (
    portfolio_id, skill_id, skill_level, expiration_date, notes,
    verification_status, verified_by, verified_on
) VALUES

--------------------------------
-- John Doe
--------------------------------

-- 1. Verified Skill Level (Sarah verified John's Java skills)
('PTF1', 'SKL1', 3, '2027-12-31', 'John successfully demonstrated MVC and Collections API usage.',
 'VERIFIED', 'STF2', '2026-07-20 14:30:00'),

-- 2. Pending Verification (John allocated Spring Boot, awaiting approval)
('PTF1', 'SKL2', 2, '2027-06-30', 'Completed Spring Security module in latest training course.',
 'PENDING', NULL, NULL),

-- 3. Expired Skill (Needs renewal)
('PTF1', 'SKL3', 1, '2026-01-15', 'Self-study of Blue Book. Certification expired.',
 'VERIFIED', 'STF2', '2025-01-15 09:00:00'),

--------------------------------
-- Sarah Jane
--------------------------------

-- 1. Verified High Level Skill
('PTF2', 'SKL4', 5, '2028-01-01', 'Years of experience managing high-performing agile engineering cohorts.',
 'VERIFIED', 'STF3', '2026-01-01 10:00:00');
