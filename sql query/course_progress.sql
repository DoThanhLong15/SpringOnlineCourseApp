use onlinecourse;

CREATE TABLE course_progress (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
	FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    lesson_complete_count INT DEFAULT 0,
    learner_id INT NOT NULL,
	FOREIGN KEY (learner_id) REFERENCES user(id) ON DELETE CASCADE,
    
    UNIQUE (course_id, learner_id)
);