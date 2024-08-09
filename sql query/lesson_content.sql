use onlinecourse;

CREATE TABLE lesson_content (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    duration INT DEFAULT 0,
    content TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    lesson_id INT NOT NULL,
	FOREIGN KEY (lesson_id) REFERENCES lesson(id) ON DELETE CASCADE,
    
    content_type_id INT,
	FOREIGN KEY (content_type_id) REFERENCES content_type(id),
    
    UNIQUE idx_course_lesson (title, lesson_id)
);