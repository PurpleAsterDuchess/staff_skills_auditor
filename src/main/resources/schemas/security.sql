-- Application Users
CREATE TABLE app_user (
                          id VARCHAR PRIMARY KEY,
                          username VARCHAR(50) NOT NULL UNIQUE,
                          password_hash VARCHAR(255) NOT NULL,
                          is_active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Application Roles (Security roles like ROLE_STAFF, ROLE_MANAGER, ROLE_ADMIN)
CREATE TABLE app_role (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(50) NOT NULL UNIQUE
);
