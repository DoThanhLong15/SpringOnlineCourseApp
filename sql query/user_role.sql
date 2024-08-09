use onlinecourse;

CREATE TABLE user_role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(50) NOT NULL UNIQUE
);

use onlinecourse;

INSERT INTO user_role (role)
VALUES ('ROLE_ADMIN');

INSERT INTO user_role (role)
VALUES ('ROLE_LEARNER');

INSERT INTO user_role (role)
VALUES ('ROLE_LECTURER');

SELECT * FROM user_role;