use onlinecourse;

CREATE TABLE content_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL UNIQUE,
    is_required BOOLEAN DEFAULT FALSE
);

use onlinecourse;

INSERT INTO content_type (type)
VALUES ('LESSON');

INSERT INTO content_type (type, is_required)
VALUES ('EXERCISE', TRUE);

INSERT INTO content_type (type, is_required)
VALUES ('TEST', TRUE);

SELECT * FROM content_type;