use onlinecourse;

CREATE TABLE course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL UNIQUE,
	description TEXT NOT NULL,
	created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    participant_count INT DEFAULT 0,
	rating_count INT DEFAULT 0,
    rating DECIMAL(3,2) DEFAULT 0.00,
    price INT NOT NULL DEFAULT (0),
    status BOOLEAN DEFAULT TRUE,
    image VARCHAR(255) NOT NULL,
    
    lecturer_id INT,
    FOREIGN KEY (lecturer_id) REFERENCES user(id),
    
    category_id INT,
	FOREIGN KEY (category_id) REFERENCES category(id) 
);

INSERT INTO course (title, description, price)
VALUES ('DevOps for Freshers', 'Bạn đang muốn trở thành DevOps Engineer mà chưa biết bắt đầu từ đâu? Bạn chưa có kiến thức DevOps thực tế?
Bạn là Developer, Sysadmin, Tester, DBA,... mong muốn có thêm kiến thức DevOps để gia tăng thu nhập và nổi bật hơn ứng viên khác?', 1000000);

SELECT * FROM course
