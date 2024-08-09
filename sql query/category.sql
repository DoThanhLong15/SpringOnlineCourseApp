use onlinecourse;

CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

use onlinecourse;

INSERT INTO category (name)
VALUES ('Tiếng Anh');

INSERT INTO category (name)
VALUES ('Lập Trình');

INSERT INTO category (name)
VALUES ('Toán Học');

INSERT INTO category(name)
VALUES ('Vật lý');

INSERT INTO category(name)
VALUES ('Kinh doanh');

INSERT INTO category(name)
VALUES ('Tin học văn phòng');

SELECT * FROM category;