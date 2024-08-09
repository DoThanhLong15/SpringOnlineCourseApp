use onlinecourse;

CREATE TABLE course_rating (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rating DECIMAL(3,2),
    comment TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    learner_id INT NOT NULL,
	FOREIGN KEY (learner_id) REFERENCES user(id) ON DELETE CASCADE,
    
    course_id INT,
	FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    
    UNIQUE idx_learner_course (learner_id, course_id)
);