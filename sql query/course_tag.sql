use onlinecourse;

CREATE TABLE course_tag (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
	FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    tag_id INT NOT NULL,
	FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE,
    
    UNIQUE (course_id, tag_id)
);

INSERT INTO course_tag (course_id, tag_id)
VALUES (1, 4);

SELECT * FROM course_tag
