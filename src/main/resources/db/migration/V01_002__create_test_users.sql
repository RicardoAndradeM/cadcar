INSERT INTO
    tb_user
    (name, email, password)
VALUES
    ('root', 'root@email.com', '$2a$10$aNTjuga81AXGSmW3OP561.ISCT4QXJ5rCSey2curzWGXIcQE2roB.'),
    ('operator1', 'operator1@email.com', '$2a$10$GwicznvobsF4Nk9.fojO4.d/O5hZ.3kjZC4TUxJPmGoy5qz2RDCGG');

INSERT INTO
    user_role
    (role , user_id )
VALUES
    ('ADMIN', 1),
    ('OPERATOR', 1),
    ('USER', 1),
    ('OPERATOR', 2),
    ('USER', 2);