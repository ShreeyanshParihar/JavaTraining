use TrainingDB;

CREATE TABLE employee_emails_master
(
    email VARCHAR(100) PRIMARY KEY
);

INSERT INTO employee_emails_master
VALUES
    ('divykjain@gmail.com'),
    ('d1v9kj01n@gmail.com'),
    ('d$vykj*in@gmail.com');

SELECT *
FROM employee_emails_master;

SELECT *
FROM employee_emails_master
WHERE email LIKE
    '%_@__%.__%';

-- Views

use BikeStores;

GO;
CREATE VIEW products_view
AS
    SELECT [product_id]
      , [product_name]
      , [brand_id]
      , [category_id]
      , [model_year]
    FROM [production].[products];
GO;

SELECT *
FROM products_view;

SELECT *
FROM production.products;

UPDATE products_view
SET [model_year] = 2017
WHERE [product_id] = 1;

EXEC sp_rename 'products_view', 'products_restricted';

SELECT *
FROM products_restricted;

DROP VIEW products_restricted;

SELECT *
FROM sys.views;

EXEC sp_helptext 'production.products';

-- Self joined view data updation

SELECT *
FROM sales.staffs;

GO;
CREATE  VIEW staff_joined_view
AS
    SELECT staff.[staff_id] AS [staff_id],
        staff.[first_name] AS [first_name],
        staff.[last_name] AS [last_name],
        staff.manager_id AS manager_id,
        manager.first_name AS [manager_first_name],
        manager.last_name AS [manager_last_name]
    FROM
        [sales].[staffs] AS staff
        INNER JOIN
        [sales].[staffs] AS manager
        ON staff.manager_id = manager.staff_id;
GO;

SELECT *
FROM staff_joined_view;

UPDATE staff_joined_view
SET first_name = 'Mireya Updated'
WHERE [staff_id] = 2;

UPDATE staff_joined_view
SET manager_first_name = 'Fabiola Updated'
WHERE [staff_id] = 2;

SELECT *
FROM sales.staffs;

-- Indexes

DROP TABLE products_index;

CREATE TABLE products_index
(
    product_id INT IDENTITY,
    name VARCHAR(100),
    price FLOAT
);

SET NOCOUNT ON

DECLARE @count INT = 0;
WHILE @count < 9999999
BEGIN
    INSERT INTO products_index
        (name, price)
    VALUES
        ('Product ' + CAST(@count AS VARCHAR(10)), CAST(@count AS FLOAT));
    SET @count = @count + 1;
END;

SELECT COUNT(product_id)
FROM products_index;

CREATE CLUSTERED INDEX ix_product_id
ON products_index(product_id);

DROP INDEX ix_product_id ON products_index;

ALTER TABLE products_index
ADD CONSTRAINT pk_product_id PRIMARY KEY(product_id);

ALTER TABLE products_index
DROP CONSTRAINT pk_product_id;

SELECT *
FROM products_index
WHERE product_id = 1000000;

-- add non clustered index on name
CREATE UNIQUE INDEX idx_product_name
ON products_index(name);

-- add non clustered index on price
CREATE INDEX idx_product_price
ON products_index(price);

-- add duplicate value in name
INSERT INTO products_index
    (name, price)
VALUES
    ('Product 1', 1);

DROP TABLE products_index;

-- Transactions

CREATE TABLE products
(
    product_id INT PRIMARY KEY IDENTITY,
    name VARCHAR(100),
    price FLOAT
)

INSERT INTO products
    (name, price)
VALUES
    ('Product 1', 100),
    ('Product 2', 100),
    ('Product 3', 100),
    ('Product 4', 100),
    ('Product 5', 100);

SELECT *
FROM products;

BEGIN TRANSACTION;

INSERT INTO products
    (name, price)
VALUES
    ('Product 6', 100);
UPDATE products SET price = 300 WHERE product_id = 3;
DELETE FROM products WHERE product_id = 5;

COMMIT TRANSACTION;

SELECT *
FROM products;

BEGIN TRANSACTION;

INSERT INTO products
    (name, price)
VALUES
    ('Product 7', 100);
UPDATE products SET price = 500 WHERE product_id = 2;
DELETE FROM products WHERE product_id = 4;

SELECT *
FROM products;

ROLLBACK TRANSACTION;

SELECT *
FROM products;
