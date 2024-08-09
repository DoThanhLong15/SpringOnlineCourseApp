use onlinecourse;

CREATE TABLE exercise_status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(50) NOT NULL UNIQUE
);

use onlinecourse;

INSERT INTO exercise_status (status)
VALUES ('NOT_DO');

INSERT INTO exercise_status (status)
VALUES ('DOING');

INSERT INTO exercise_status (status)
VALUES ('DONE');

SELECT * FROM exercise_status;