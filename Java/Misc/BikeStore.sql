SELECT @@VERSION;

-- Basic SQL Server SELECT Statement

SELECT
	*
FROM
	production.brands;

SELECT
	*
FROM
	production.categories;

SELECT
	*
FROM
	[production].[products];

SELECT
	*
FROM
	[production].[stocks];

SELECT
	*
FROM
	[sales].[customers];


-- Print first_name, last_name of all customers
SELECT
	first_name,
	last_name
FROM
	sales.customers;

-- Filter rows based on one or more conditions, using WHERE clause
SELECT
	*
FROM
	sales.customers
WHERE
	state = 'NY';

-- To sort, using ORDER BY
SELECT
	*
FROM
	sales.customers
WHERE
	[state] = 'NY'
ORDER BY
	first_name;

-- Print first_name, last_name, city of customers and sort by city descending first and then by first name in ascending
SELECT
	city,
	first_name,
	last_name
FROM
	sales.customers
ORDER BY
	city DESC,
	first_name ASC;

-- Print first_name, last_name, city of customers and sort by length of customers first name
SELECT
	first_name,
	last_name,
	LEN(first_name) AS name_length
FROM
	sales.customers
ORDER BY
	name_length;

-- DISTINCT
-- Print all cities from customers
SELECT DISTINCT
	city, [state]
FROM
	sales.customers
ORDER BY
	city, [state];

SELECT
	city
FROM
	sales.customers
ORDER BY
	city;

-- Using WHERE Clause
-- Find rows that meet two conditions
-- Print all products
-- With catergory id is 1 and model is 2018
SELECT
	*
FROM
	production.products
WHERE
	category_id = 1 AND model_year = 2018
ORDER BY
	list_price;

SELECT
	*
FROM
	production.products
WHERE
	list_price > 300 AND model_year = 2018
ORDER BY
	list_price;

SELECT
	*
FROM
	production.products
WHERE
	list_price BETWEEN 1899 AND 1999.99
ORDER BY
	list_price;

SELECT
	*
FROM
	production.products
WHERE
	list_price in (299.99,466.99,489.99)
ORDER BY
	list_price;

-- Find products with 'Cruiser' in name
SELECT
	*
FROM
	production.products
WHERE
	product_name LIKE '%Cruiser%';


SELECT
	customer_id,
	COUNT(customer_id) AS 'orders_count',
	YEAR(order_date) AS 'order_year'
FROM
	sales.orders
GROUP BY
	customer_id,
	YEAR(order_date)
ORDER BY
	customer_id

SELECT
	[state],
	COUNT(customer_id) AS customer_count
FROM
	sales.customers
GROUP BY
	[state]
HAVING
	COUNT(customer_id) > 200;

