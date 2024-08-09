use onlinecourse;

CREATE TABLE cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    course_id INT NOT NULL,
	FOREIGN KEY (course_id) REFERENCES  course(id) ON DELETE CASCADE,
    
    learner_id INT NOT NULL,
	FOREIGN KEY (learner_id) REFERENCES  user(id) ON DELETE CASCADE,
    
    UNIQUE idx_learner_course (learner_id, course_id)
);