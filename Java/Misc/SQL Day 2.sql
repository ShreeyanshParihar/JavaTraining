GO;
CREATE SCHEMA hr;
GO;

DROP TABLE IF EXISTS hr.candidates;
DROP TABLE IF EXISTS hr.employees;

CREATE TABLE hr.candidates (
	id INT PRIMARY KEY IDENTITY,
	fullname VARCHAR(100) NOT NULL
);

CREATE TABLE hr.employees (
	id INT PRIMARY KEY IDENTITY,
	fullname VARCHAR(100) NOT NULL
);

INSERT INTO
	hr.candidates
VALUES
	('Divya Jain'),
	('Amey Sawant'),
	('Jayant Kulkarni'),
	('Shreeyansh Parihar'),
	('Nikhil Rane');

INSERT INTO
	hr.employees
VALUES
	('Divya Jain'),
	('Nihal Babu'),
	('Mayank Patel'),
	('Vinayak Navada'),
	('Nikhil Rane');

SELECT * FROM hr.candidates;
SELECT * FROM hr.employees;

SELECT * FROM hr.candidates, hr.employees;
SELECT * FROM hr.candidates CROSS JOIN hr.employees;

SELECT
	*
FROM
	hr.candidates
	INNER JOIN
	hr.employees
ON
	candidates.fullname = employees.fullname;

SELECT
	*
FROM
	hr.candidates
	LEFT JOIN
	hr.employees
ON
	candidates.fullname = employees.fullname;

SELECT
	*
FROM
	hr.candidates
	RIGHT JOIN
	hr.employees
ON
	candidates.fullname = employees.fullname;

	
SELECT
	*
FROM
	hr.candidates
	FULL OUTER JOIN
	hr.employees
ON
	candidates.fullname = employees.fullname;

SELECT
	*
FROM
	hr.candidates AS candidates1
	JOIN
	hr.candidates AS candidates2
ON
	candidates1.fullname = candidates2.fullname;

SELECT
	employee.first_name AS employee,
	manager.first_name AS manager
FROM
	sales.staffs AS manager
	JOIN
	sales.staffs AS employee
ON
	employee.manager_id = manager.staff_id;

-- Print customers from new york
SELECT
	*
FROM
	sales.customers
WHERE
	city = 'New York';


-- Print all orders
SELECT
	*
FROM
	sales.orders;


-- Print sales order for New York Customers
SELECT
	*
FROM
	sales.orders
WHERE
	customer_id IN (
		SELECT
			customer_id
		FROM
			sales.customers
		WHERE
			city = 'New York'
	);


-- Print all products for which stock is > 20
WITH stocks AS (
	SELECT
		product_id,
		SUM(quantity) AS quantity
	FROM
		production.stocks
	GROUP BY
		product_id
	HAVING
		SUM(quantity) > 20
)
SELECT
	products.product_id,
	products.product_name,
	stocks.quantity
FROM
	stocks
	JOIN
	production.products
ON
	stocks.product_id = products.product_id;

WITH stocks AS (
	SELECT
		store_id,
		product_id,
		quantity
	FROM
		production.stocks
	WHERE
		quantity > 20
)
SELECT
	products.product_id,
	products.product_name,
	stocks.store_id,
	stocks.quantity
FROM
	stocks
	JOIN
	production.products
ON
	stocks.product_id = products.product_id;


-- Products with price more than avg price
SELECT
	products.product_name,
	products.list_price
FROM
	production.products
WHERE
	products.list_price >= ANY (
		SELECT
			AVG(products.list_price)
		FROM
			production.products
		GROUP BY
			products.brand_id
	)

SELECT GETDATE();
SELECT DAY(GETDATE());
SELECT MONTH(GETDATE());
SELECT YEAR(GETDATE());
SELECT DATEADD(SECOND, 30, '2021-12-31 23:45:00') RESULT;
SELECT DATEADD(MINUTE, 30, '2021-12-31 23:45:00') RESULT;

DECLARE @d DATETIME = GETDATE();
SELECT
	DATEPART(YEAR, @d) YEAR,
	DATENAME(WEEKDAY, @d) WEEKDAY;

DECLARE
	@start_date DATETIME = '2021-12-31 23:45:30',
	@end_date DATETIME = '2022-06-09 11:15:10'
SELECT
	DATEDIFF(YEAR, @start_date, @end_date) YEAR,
	DATEDIFF(MONTH, @start_date, @end_date) MONTH,
	DATEDIFF(DAY, @start_date, @end_date) DAY,
	DATEDIFF(HOUR, @start_date, @end_date) HOUR,
	DATEDIFF(SECOND, @start_date, @end_date) SECOND;
