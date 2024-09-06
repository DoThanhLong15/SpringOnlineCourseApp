use onlinecourse;

CREATE TABLE certificate (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    course_name VARCHAR(50) NOT NULL,
	learner_name VARCHAR(50) NOT NULL,
    finish_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    learner_id INT,
    FOREIGN KEY (learner_id) REFERENCES user(id) ON DELETE SET NULL,
    
    UNIQUE (course_name, learner_name)
);