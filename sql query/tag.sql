use onlinecourse;

CREATE TABLE tag (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO tag (name)
VALUES ('Lập trình Android');

INSERT INTO tag (name)
VALUES ('Vật lý lớp 10');

INSERT INTO tag (name)
VALUES ('Tác phẩm truyện Kiều');

INSERT INTO tag (name)
VALUES ('Khóa học Devops');

INSERT INTO tag (name)
VALUES ('Lập trình');

INSERT INTO tag (name)
VALUES ('C++');

SELECT * FROM tag;