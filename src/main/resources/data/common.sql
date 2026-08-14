-- Role Allocations
INSERT INTO role_allocation (name) VALUES
                                       ('manager'),
                                       ('admin'),
                                       ('staff');

-- Roles (1-2-1) Users
INSERT INTO user_roles (user_id, role_id) VALUES
                                              ('USR1', 1), -- john.doe -> ROLE_STAFF
                                              ('USR2', 2), -- sarah.connor -> ROLE_MANAGER
                                              ('USR3', 3), -- alan.smith -> ROLE_SKILL_MANAGER
                                              ('USR4', 4); -- admin.sys -> ROLE_ADMIN

-- HR Facade / Synchronised Cache Data for Staff
INSERT INTO staff_member (
    id, first_name, surname, email, hire_date, department,
    line_manager_id, current_role, role_start_date, job_level,
    employment_type, employment_status
) VALUES
-- Line Manager (Sarah Jane)
('STF2', 'Sarah', 'Jane', 'sarah.jane@company.com', '2022-03-15', 'Engineering',
 NULL, 'Software Engineering Manager', '2024-01-01', 'Manager',
 'Full-Time', 'ACTIVE'),

-- Staff Member (John Doe) managed by Sarah Jane
('STF1', 'John', 'Doe', 'john.doe@company.com', '2023-06-01', 'Engineering',
 'STF-002', 'Junior Software Engineer', '2023-06-01', 'Junior',
 'Full-Time', 'ACTIVE'),

-- HR/Skill Manager (Alan Smith)
('STF3', 'Alan', 'Smith', 'alan.smith@company.com', '2021-08-10', 'Human Resources',
 NULL, 'HR Director', '2021-08-10', 'Director',
 'Full-Time', 'ACTIVE'),

-- Admin (Sys Admin)
('STF4', 'System', 'Administrator', 'admin@company.com', '2020-01-01', 'IT Support',
 NULL, 'IT Systems Lead', '2020-01-01', 'Lead',
 'Full-Time', 'ACTIVE');
