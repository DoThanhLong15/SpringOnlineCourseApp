use onlinecourse;

CREATE TABLE lesson (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
	created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    course_id INT NOT NULL,
	FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    
    UNIQUE idx_course_lesson (title, course_id)
);