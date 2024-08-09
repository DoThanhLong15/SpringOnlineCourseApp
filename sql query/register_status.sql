use onlinecourse;

CREATE TABLE register_status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO register_status (status)
VALUES ('NOT_BUY');

INSERT INTO register_status (status)
VALUES ('BUY');

SELECT * FROM register_status;