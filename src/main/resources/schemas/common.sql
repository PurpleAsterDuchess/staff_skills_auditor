-- Role Allocation
CREATE TABLE role_allocation(
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(35) NOT NULL UNIQUE
);

-- User -3------|E- Role
CREATE TABLE user_roles (
                            user_id VARCHAR NOT NULL,
                            role_id INT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES app_role(id) ON DELETE CASCADE
);


CREATE TABLE staff_member (
                              id VARCHAR PRIMARY KEY,
                              first_name VARCHAR(50) NOT NULL,
                              surname VARCHAR(50) NOT NULL,
                              email VARCHAR(100) NOT NULL UNIQUE,

                              hire_date DATE NOT NULL,
                              department VARCHAR(50) NOT NULL,
                              line_manager_id VARCHAR,

                              current_role VARCHAR(100) NOT NULL,
                              role_start_date DATE NOT NULL,
                              job_level VARCHAR(30) NOT NULL,
                              employment_type VARCHAR(30) NOT NULL,

                              employment_status VARCHAR(30) NOT NULL,

                              FOREIGN KEY (line_manager_id) REFERENCES staff_member(id)
);

