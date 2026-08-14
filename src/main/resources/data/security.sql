-- Security Roles
INSERT INTO app_role (name) VALUES
                                ('ROLE_STAFF'),
                                ('ROLE_MANAGER'),
                                ('ROLE_SKILL_MANAGER'),
                                ('ROLE_ADMIN');

-- Users (Passwords: bcrypt hashed representation of 'password123')
INSERT INTO app_user (id, username, password_hash, is_active) VALUES
                                                                  ('USR1', 'john.doe', '$2a$12$eImiTxAkJyN47M9uY/QkSuO59FExI0Iu64Y2t/kK53zRk62GCOwZ2', TRUE),
                                                                  ('USR2', 'sarah.jane', '$2a$12$eImiTxAkJyN47M9uY/QkSuO59FExI0Iu64Y2t/kK53zRk62GCOwZ2', TRUE),
                                                                  ('USR3', 'alan.smith', '$2a$12$eImiTxAkJyN47M9uY/QkSuO59FExI0Iu64Y2t/kK53zRk62GCOwZ2', TRUE),
                                                                  ('USR4', 'admin.sys', '$2a$12$eImiTxAkJyN47M9uY/QkSuO59FExI0Iu64Y2t/kK53zRk62GCOwZ2', TRUE);
