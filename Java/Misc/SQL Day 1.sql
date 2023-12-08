USE TrainingDB;

DROP TABLE employee_master;

CREATE TABLE employee_master (
    employee_id INT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    date_of_birth DATE,
    salary DECIMAL(10,2)
);

SELECT * FROM employee_master;

ALTER TABLE employee_master
DROP COLUMN date_of_birth;

ALTER TABLE employee_master
ADD date_of_birth DATE;

ALTER TABLE employee_master
ADD
    gender CHAR(1),
    designation VARCHAR(20);

ALTER TABLE employee_master
ALTER COLUMN gender VARCHAR(5);

ALTER TABLE employee_master
DROP COLUMN date_of_birth, gender, designation;

SELECT * FROM employee_master;

SP_HELP employee_master;

-- Contraints
-- SQL Server PRIMARY KEY

CREATE TABLE activities (
    activity_id INT PRIMARY KEY,
    activity_name VARCHAR(50) NOT NULL,
    activity_date DATE NOT NULL
)

SP_HELP activities;

INSERT INTO activities (activity_id, activity_name, activity_date)
VALUES (101, 'Backup of File System', GETDATE());

SELECT * FROM activities;

INSERT INTO activities (activity_id, activity_name, activity_date)
VALUES (101, 'Restart System', GETDATE());

-- SQL Server FOREIGN KEY

CREATE TABLE vendor_group (
	group_id INT PRIMARY KEY,
	group_name VARCHAR(100)
);

INSERT INTO vendor_group (group_id, group_name)
VALUES
	(1, 'Third Party'),
	(2, 'Interco'),
	(3, 'One-Time');

SELECT * FROM vendor_group;

CREATE TABLE vendors (
	vendor_id INT PRIMARY KEY,
	vendor_name VARCHAR(50),
	group_id INT
	CONSTRAINT fk_group_vendor FOREIGN KEY(group_id) REFERENCES vendor_group(group_id)
);

INSERT INTO vendors(vendor_id, vendor_name, group_id)
VALUES
	(101, 'ABC Corp', 1),
	(102, 'DEF Corp', 3),
	(103, 'GHI Corp', 3);

SELECT * FROM vendors;

INSERT INTO vendors(vendor_id, vendor_name, group_id)
VALUES (104, 'JKL Corp', 4);

INSERT INTO vendors(vendor_id, vendor_name, group_id)
VALUES (104, 'MNP Corp', 2);

-- SQL Server CHECK CONTRAINT, IDENTITY

CREATE TABLE products (
	product_id INT PRIMARY KEY IDENTITY,
	product_name VARCHAR(50),
	unit_price INT,
	CONSTRAINT positive_price_check CHECK(unit_price > 0)
);

INSERT INTO products (product_name, unit_price)
VALUES
	('Lux', 10),
	('Nirma', 15),
	('Rin', 8);

SELECT * FROM products;

INSERT INTO products (product_name, unit_price)
VALUES ('Vim', 0);

-- SQL Server UNIQUE

CREATE TABLE subscriber_details (
	id INT UNIQUE,
	name VARCHAR(20)
);

INSERT INTO subscriber_details
VALUES
	(1, 'ABC'),
	(2, 'DEF');

INSERT INTO subscriber_details
VALUES (1, 'GHI');
INSERT INTO subscriber_details
VALUES (NULL, 'JKL');

-- SQL Server DEFAULT

DROP TABLE employee;

CREATE TABLE employee (
	employee_id INT PRIMARY KEY IDENTITY,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	age INT,
	city VARCHAR(50) DEFAULT 'Mumbai'
);

INSERT INTO employee (first_name, last_name, age, city)
VALUES ('Divya', 'Jain', 22, DEFAULT);

INSERT INTO employee (first_name, last_name, age, city)
VALUES ('Shreeyansh', 'Parihar', 23, 'Ajmer');

SP_HELP employee;

SELECT * FROM employee;

UPDATE employee
SET first_name = 'Vivek', last_name = 'Gohil'
WHERE employee_id = 2

DELETE FROM employee
WHERE employee_id = 2
