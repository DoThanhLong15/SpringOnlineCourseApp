use onlinecourse;

CREATE TABLE doing_exercise (
    id INT AUTO_INCREMENT PRIMARY KEY,
    score DECIMAL(4,2),
    content TEXT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    learner_id INT NOT NULL,
	FOREIGN KEY (learner_id) REFERENCES user(id) ON DELETE CASCADE,
    
    exercise_status_id INT DEFAULT 1,
	FOREIGN KEY (exercise_status_id) REFERENCES exercise_status(id),
    
    lesson_content_id INT NOT NULL,
	FOREIGN KEY (lesson_content_id) REFERENCES  lesson_content(id) ON DELETE CASCADE,
    
    UNIQUE idx_learner_course (learner_id, lesson_content_id)
);