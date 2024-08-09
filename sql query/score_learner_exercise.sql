use onlinecourse;

CREATE TABLE score_learner_exercise (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comment TEXT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    doing_exercise_id INT NOT NULL,
	FOREIGN KEY (doing_exercise_id) REFERENCES  doing_exercise(id) ON DELETE CASCADE
);