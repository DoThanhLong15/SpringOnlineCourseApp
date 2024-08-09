use onlinecourse;

CREATE TABLE register_detail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    price DECIMAL(10,2) NOT NULL,
    
    register_order_id INT NOT NULL,
	FOREIGN KEY (register_order_id) REFERENCES  register_order(id) ON DELETE RESTRICT,
    
    course_id INT,
	FOREIGN KEY (course_id) REFERENCES  course(id)
);