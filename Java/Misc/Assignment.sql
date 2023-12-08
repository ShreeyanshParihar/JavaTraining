DROP DATABASE IF EXISTS AssignmentDB;
CREATE DATABASE AssignmentDB;

use AssignmentDB;


CREATE TABLE client_master
(
    client_no varchar(6) PRIMARY KEY CHECK(client_no like 'C%'),
    name varchar(20) NOT NULL,
    address1 varchar(30),
    address2 varchar(30),
    city varchar(15),
    state varchar(15),
    pincode numeric(6),
    bal_due numeric(10,2)
);

CREATE TABLE product_master
(
    product_no VARCHAR(6) PRIMARY KEY CHECK(product_no LIKE 'P%'),
    description VARCHAR(50) NOT NULL,
    profit_percent DECIMAL(3,2) NOT NULL,
    unit_measure VARCHAR(10) NOT NULL,
    qty_on_hand INTEGER NOT NULL CHECK(qty_on_hand > 0),
    record_lvl INTEGER NOT NULL,
    sell_price DECIMAL(8,2) NOT NULL CHECK(sell_price > 0),
    cost_price DECIMAL(8,2) NOT NULL CHECK(cost_price > 0)
);

CREATE TABLE salesman_master
(
    salesman_no VARCHAR(6) PRIMARY KEY CHECK(salesman_no LIKE 'S%'),
    salesman_name VARCHAR(20) NOT NULL,
    address1 VARCHAR(30) NOT NULL,
    address2 VARCHAR(30),
    city VARCHAR(20),
    pincode VARCHAR(6),
    state VARCHAR(20),
    sal_amt NUMERIC(8,2) NOT NULL CHECK(sal_amt > 0),
    tgt_to_get NUMERIC(6,2) NOT NULL CHECK(tgt_to_get > 0),
    ytd_sales NUMERIC(6,2) NOT NULL,
    remarks varchar(60)
);

CREATE TABLE sales_order
(
    s_order_no VARCHAR(6) PRIMARY KEY CHECK(s_order_no LIKE 'O%'),
    s_order_date DATE,
    client_no VARCHAR(6),
    --fk
    dely_addr VARCHAR(25),
    salesman_no VARCHAR(6),
    --fk
    dely_type CHAR CHECK(dely_type in ('F','P')) DEFAULT 'F',
    billed_yn CHAR CHECK(billed_yn in ('Y','N')) DEFAULT 'N',
    dely_date DATE,
    order_status VARCHAR(10) CHECK(order_status in ('In Process', 'Fulfilled', 'BackOrder', 'Canceled')),
    CONSTRAINT check_dely_date_after_s_order_date CHECK (dely_date >= s_order_date),
    CONSTRAINT fk_sales_order_client_no FOREIGN KEY(client_no) REFERENCES client_master(client_no) ON DELETE CASCADE,
    CONSTRAINT fk_sales_order_salesman_no FOREIGN KEY(salesman_no) REFERENCES salesman_master(salesman_no) ON DELETE CASCADE
);

CREATE TABLE sales_order_details
(
    s_order_no varchar(6) FOREIGN KEY (s_order_no) REFERENCES sales_order(s_order_no) ON DELETE CASCADE,
    product_no varchar(6) FOREIGN KEY (product_no) REFERENCES product_master(product_no) ON DELETE CASCADE,
    qty_ordered numeric(8),
    qty_disp numeric(8),
    product_rate numeric(10,2)
);

CREATE TABLE challan_header
(
    challan_no VARCHAR(6) PRIMARY KEY CHECK(LEFT(challan_no,2) = 'CH'),
    s_order_no VARCHAR(6),
    challan_date DATE NOT NULL,
    billed_yn CHAR(1) CHECK(billed_yn IN( 'Y','N')) DEFAULT 'N',
    CONSTRAINT fk_challan_header_order_sales_order FOREIGN KEY(s_order_no) REFERENCES sales_order(s_order_no) ON DELETE CASCADE
);

CREATE TABLE challan_details
(
    challan_no varchar(6),
    product_no varchar(6),
    qty_disp numeric(4,2) not null,
    PRIMARY KEY(challan_no,product_no),
    CONSTRAINT fk_challan_no FOREIGN KEY(challan_no) REFERENCES challan_header(challan_no) ON DELETE CASCADE,
    CONSTRAINT fk_product_no FOREIGN KEY(product_no)  REFERENCES product_master(product_no) ON DELETE CASCADE
);

insert into client_master
values('C00001', 'Ivan Bayross', '', '', 'Bombay', 'Maharashtra', 400054, 15000);
insert into client_master
values('C00002', 'Vandana Saitwal', '', '', 'Madras' , 'Tamilnadu', 780001, 0);
insert into client_master
values('C00003', 'Pramada Jaguste', '', '', 'Bombay', 'Maharashtra', 400057, 5000);
insert into client_master
values('C00004', 'Basu Navindgi', '', '', 'Bombay' , 'Maharashtra', 400056, 0);
insert into client_master
values('C00005', 'Ravi Sreedharan', '', '', 'Delhi' , 'Delhi', 100001, 2000);
insert into client_master
values('C00006', 'Rukmini', '', '', 'Bombay', 'Maharashtra', 400050, 0);

INSERT INTO product_master
VALUES
    ('P00001', '1.44 Floppies', 5, 'Piece', 100, 20, 525, 500),
    ('P03453', 'Monitors', 6, 'Piece', 10, 3, 12000, 11280),
    ('P06734', 'Mouse', 5, 'Piece', 20, 5, 1050, 1000),
    ('P07865', '1.22 Floppies', 5, 'Piece', 100, 20, 525, 500),
    ('P07868', 'Keyboards', 2, 'Piece', 10, 3, 3150, 3050),
    ('P07885', 'CD Drive', 2.5, 'Piece', 10, 3, 5250, 5100),
    ('P07965', '540 HDD', 4, 'Piece', 10, 3, 8400, 8000),
    ('P07975', '1.44 Drive', 5, 'Piece', 10, 3, 1050, 1000),
    ('P08865', '1.22 Drive', 5, 'Piece', 2, 3, 1050, 1000);

INSERT INTO salesman_master
VALUES
    ('S00001', 'Kiran', 'A/14', 'Worli', 'Bombay', '400002', 'MAH', 3000, 100, 50, 'Good'),
    ('S00002', 'Manish', '65', 'Nariman', 'Bombay', '400001', 'MAH', 3000, 200, 100, 'Good'),
    ('S00003', 'Ravi', 'P-7', 'Bandra', 'Bombay', '400032', 'MAH', 3000, 200, 100, 'Good'),
    ('S00004', 'Ashish', 'A/5', 'Juhu', 'Bombay', '400044', 'MAH', 3500, 200, 150, 'Good');

INSERT INTO sales_order
    (s_order_no, s_order_date, client_no, dely_type, billed_yn, salesman_no, dely_date, order_status)
VALUES
    ('O19001', '12-Jan-1996', 'C00001', 'F', 'N', 'S00001', '20-Jan-1996', 'In Process'),
    ('O19002', '25-Jan-1996', 'C00002', 'P', 'N', 'S00002', '27-Jan-1996', 'Canceled'),
    ('O46865', '18-Feb-1996', 'C00003', 'F', 'Y', 'S00003', '20-FEB-1996', 'Fulfilled'),
    ('O19003', '03-Apr-1996', 'C00001', 'F', 'Y', 'S00001', '07-April-1996', 'Fulfilled'),
    ('O46866', '20-May-1996', 'C00004', 'P', 'N', 'S00002', '22-May-1996', 'Canceled'),
    ('O10008', '24-May-1996', 'C00005', 'F', 'N', 'S00004', '26-May-1996', 'In Process');

INSERT INTO sales_order_details
VALUES
    ('O19001', 'P00001', 4, 4, 525),
    ('O19001', 'P07965', 2, 1, 8400),
    ('O19001', 'P07885', 2, 1, 5250),
    ('O19002', 'P00001', 10, 0, 525),
    ('O46865', 'P07868', 3, 3, 3150),
    ('O46865', 'P07885', 3, 1, 5250),
    ('O46865', 'P00001', 10, 10, 525),
    ('O46865', 'P03453', 4, 4, 1050),
    ('O19003', 'P03453', 2, 2, 1050),
    ('O19003', 'P06734', 1, 1, 12000),
    ('O46866', 'P07965', 1, 0, 8400),
    ('O46866', 'P07975', 1, 0, 1050),
    ('O10008', 'P00001', 10, 5, 525),
    ('O10008', 'P07975', 5, 3, 1050);

INSERT INTO challan_header
    (challan_no,s_order_no,challan_date,billed_yn)
VALUES
    ('CH9001', 'O19001', '12-Dec-1995', 'Y'),
    ('CH6865', 'O46865', '12-Nov-1995', 'Y'),
    ('CH3965', 'O10008', '12-Oct-1995', 'Y');

INSERT INTO challan_details
VALUES
    ('CH9001', 'P00001', 4),
    ('CH9001', 'P07965', 1),
    ('CH9001', 'P07885', 1),
    ('CH6865', 'P07868', 3),
    ('CH6865', 'P03453', 4),
    ('CH6865', 'P00001', 10),
    ('CH3965', 'P00001', 5),
    ('CH3965', 'P07975', 2);


SELECT *
FROM client_master;
SELECT *
FROM product_master;
SELECT *
FROM salesman_master;
SELECT *
FROM sales_order;
SELECT *
FROM sales_order_details;
SELECT *
FROM challan_header;
SELECT *
FROM challan_details;

-- 1)	Find out the names of all the clients.
SELECT
    name
FROM
    client_master;

-- 2)	Print the entire client_master table.
SELECT
    *
FROM
    client_master;

-- 3)	Retrieve the list of names and the cities of all the clients
SELECT
    name, city
FROM
    client_master;

-- 4)	List the various products available from the product_master table.
SELECT
    *
FROM
    product_master;

-- 5)	Find the names of all clients having ‘a’ as the second letter in their table.
SELECT
    name
FROM
    client_master
WHERE
    name LIKE '_a%';

-- 6)	Find the names of all clients who stay in a city whose second letter is ‘a’
SELECT
    name,
    city
FROM
    client_master
WHERE
    city LIKE '_a%';

-- 7)	Find out the clients who stay in a city ‘Bombay’ or city ‘Delhi’ or city ‘Madras’.
SELECT
    name
FROM
    client_master
WHERE
    city IN ('Bombay', 'Delhi', 'Madras');

-- 8)	List all the clients who are located in Bombay.
SELECT
    name
FROM
    client_master
WHERE
    city = 'Bombay';

-- 9)	Print the list of clients whose bal_due are greater than value 10000
SELECT
    name
FROM
    client_master
WHERE
    bal_due > 10000;

-- 10)	Print the information from sales_order table of orders placed in the month of January.
SELECT
    *
FROM
    sales_order
WHERE
    DATEPART(MONTH, s_order_date) = 1;

-- 11)	Display the order information for client_no ‘C00001’ and ‘C00002’
SELECT
    *
FROM
    sales_order
WHERE
    client_no IN ('C00001', 'C00002');

-- 12)	Find the products with description as ‘1.44 Drive’ and ‘1.22 Drive’
SELECT
    *
FROM
    product_master
WHERE
    description IN ('1.44 Drive', '1.22 Drive');

-- 13)	Find the products whose selling price is greater than 2000 and less than or equal to 5000
SELECT
    *
FROM
    product_master
WHERE
    sell_price BETWEEN 2000 AND 5000;

-- 14)	Find the products whose selling price is more than 1500 and also find the new selling price as original selling price * 15
SELECT
    product_no,
    sell_price,
    sell_price * 15 AS original_sell_price
FROM
    product_master
WHERE
    sell_price > 1500;

-- 15)	Rename the new column in the above query as new_price
SELECT
    product_no,
    sell_price,
    sell_price * 15 AS new_price
FROM
    product_master
WHERE
    sell_price > 1500;

-- 16)	Find the products whose cost price is less than 1500
SELECT
    *
FROM
    product_master
WHERE
    cost_price < 1500;

-- 17)	List the products in sorted order of their description.
SELECT
    *
FROM
    product_master
ORDER BY
    description;

-- 18)	Calculate the square root the price of each product.
SELECT
    product_no,
    sqrt(sell_price) AS root_price
FROM
    product_master;

-- 19)	Divide the cost of product ‘540 HDD’ by difference between its price and 100
SELECT
    product_no,
    cost_price / (sell_price - 100) AS cost_ratio
FROM
    product_master;

-- 20)	List the names, city and state of clients not in the state of Maharashtra
SELECT
    name,
    city,
    state
FROM
    client_master
WHERE
    state != 'Maharashtra';

-- 21)	List the product_no, description, sell_price of products whose description begin with letter ‘M’
SELECT
    product_no,
    description,
    sell_price
FROM
    product_master
WHERE
    description LIKE 'M%';

-- 22)	List all the orders that were canceled in the month of May.
SELECT
    *
FROM
    sales_order
WHERE
    DATEPART(MONTH, s_order_date) = 5
    AND order_status = 'Canceled';

-- 23)	Count the total number of orders.
SELECT
    COUNT(*)
FROM
    sales_order;

-- 24)	Calculate the average price of all the products.
SELECT
    AVG(sell_price)
FROM
    product_master;

-- 25)	Calculate the minimum price of products.
SELECT
    MIN(sell_price)
FROM
    product_master;

-- 26)	Determine the maximum and minimum product prices. Rename the title as max_price and min_price respectively.
SELECT
    MAX(sell_price) AS max_price,
    MIN(sell_price) AS min_price
FROM
    product_master;

-- 27)	Count the number of products having price greater than or equal to 1500.
SELECT
    COUNT(*)
FROM
    product_master
WHERE
    sell_price >= 1500;

-- 28)	Find all the products whose qty_on_hand is less than reorder level.
SELECT
    *
FROM
    product_master
WHERE
    qty_on_hand < record_lvl;

-- 29)	Print the information of client_master, product_master, sales_order table in the following formate for all the records {cust_name} has placed order {order_no} on {s_order_date}.
SELECT
    CONCAT(
		client_master.name,
		' has placed order ',
		sales_order.s_order_no,
		' on ',
		sales_order.s_order_date
	)
FROM
    client_master
    INNER JOIN
    sales_order
    ON
    client_master.client_no = sales_order.client_no;

-- 30)	Print the description and total qty sold for each product.
SELECT
    product_master.description,
    SUM(sales_order_details.qty_ordered) AS total_qty_sold
FROM
    sales_order
    INNER JOIN
    sales_order_details
    ON
	sales_order.s_order_no = sales_order_details.s_order_no
    INNER JOIN
    product_master
    ON
    product_master.product_no = sales_order_details.product_no
GROUP BY
    product_master.description;

-- 31)	Find the value of each product sold.
SELECT
    product_master.description,
    SUM(sales_order_details.product_rate * sales_order_details.qty_ordered) AS total_value
FROM
    sales_order
    INNER JOIN
    sales_order_details
    ON
	sales_order.s_order_no = sales_order_details.s_order_no
    INNER JOIN
    product_master
    ON
    product_master.product_no = sales_order_details.product_no
GROUP BY
    product_master.description;

-- 32)	Calculate the average qty sold for each client that has a maximum order value of 15000.00
SELECT
    client_master.name,
    AVG(sales_order_details.qty_ordered) AS avg_qty_sold
FROM
    sales_order
    INNER JOIN
    sales_order_details
    ON
    sales_order.s_order_no = sales_order_details.s_order_no
    INNER JOIN
    client_master
    ON
    client_master.client_no = sales_order.client_no
GROUP BY
    client_master.name
HAVING
    MAX(sales_order_details.qty_ordered*sales_order_details.product_rate) >= 15000.00;

-- 33)	Find out the total sales amount receivable for the month of jan. it will be the sum total of all the billed orders for the month.
SELECT
    SUM(sales_order_details.qty_ordered * sales_order_details.product_rate) AS total_sales_amount
FROM
    sales_order
    INNER JOIN
    sales_order_details
    ON
    sales_order.s_order_no = sales_order_details.s_order_no
WHERE
    DATEPART(MONTH, sales_order.s_order_date) = 1;

-- 34)	Print the information of product_master, order_detail table in the following format for all the records
-- {Description} worth Rs. {Total sales for the product} was sold.
SELECT
    CONCAT(
        product_master.description,
        ' worth Rs. ',
        SUM(sales_order_details.qty_ordered * sales_order_details.product_rate),
        ' was sold.'
    )
FROM
    sales_order
    INNER JOIN
    sales_order_details
    ON
    sales_order.s_order_no = sales_order_details.s_order_no
    INNER JOIN
    product_master
    ON
    product_master.product_no = sales_order_details.product_no
GROUP BY
    product_master.description;

-- 35)	Print the information of product_master, order_detail table in the following format for all the records
-- {Description} worth Rs. {Total sales for the product} was produced in the month of {s_order_date} in month formate.
SELECT
    CONCAT(
        product_master.description,
        ' worth Rs. ',
        SUM(sales_order_details.qty_ordered * sales_order_details.product_rate),
        ' was produced in the month of ',
        DATENAME(MONTH, sales_order.s_order_date)
    )
FROM
    sales_order
    INNER JOIN
    sales_order_details
    ON
    sales_order.s_order_no = sales_order_details.s_order_no
    INNER JOIN
    product_master
    ON
    product_master.product_no = sales_order_details.product_no
GROUP BY
    product_master.description,
    DATENAME(MONTH, sales_order.s_order_date);

-- 36)	Find the product_no and description of non-moving products.
SELECT
    product_master.product_no,
    product_master.description
FROM
    product_master
WHERE
    product_no NOT IN (
        SELECT
    product_no
FROM
    sales_order_details
    );

-- 37)	Find the customer name, address1, address2, city and pin code for the client who has placed order no ‘O19001’
SELECT
    client_master.name,
    client_master.address1,
    client_master.address2,
    client_master.city,
    client_master.pincode
FROM
    client_master
WHERE
    client_master.client_no = (
        SELECT
    client_no
FROM
    sales_order
WHERE
            s_order_no = 'O19001'
    );

-- 38)	Find the client names who have placed orders before the month of May, 1996
SELECT
    client_master.name
FROM
    client_master
WHERE
    client_master.client_no IN (
        SELECT
    client_no
FROM
    sales_order
WHERE
            DATEPART(YEAR, s_order_date) < 1996
    OR
    (
                DATEPART(YEAR, s_order_date) = 1996
    AND
    DATEPART(MONTH, s_order_date) < 5
            )
    );

-- 39)	Find out if product ‘1.44 Drive’ is ordered by client and print the client_no, name to whom it is was sold.
SELECT
    client_master.client_no,
    client_master.name
FROM
    client_master
WHERE
    client_master.client_no IN (
        SELECT
    client_no
FROM
    sales_order
WHERE
            s_order_no IN (
                SELECT
    s_order_no
FROM
    sales_order_details
WHERE
                    product_no = (
                        SELECT
    product_no
FROM
    product_master
WHERE
                            description = '1.44 Drive'
                    )
            )
    );

-- 40)	Find the names of clients who have placed orders worth Rs. 10000 or more.
WITH
    sales
    AS
    (
        SELECT
            client_no,
            SUM(sales_order_details.qty_ordered * sales_order_details.product_rate) AS total_sales
        FROM
            sales_order
            INNER JOIN
            sales_order_details
            ON
            sales_order.s_order_no = sales_order_details.s_order_no
        GROUP BY
            client_no
        HAVING
            SUM(sales_order_details.qty_ordered * sales_order_details.product_rate) >= 10000
    )
SELECT
    client_master.name
FROM
    sales
    INNER JOIN
    client_master
    ON
    sales.client_no = client_master.client_no;

-- 41)	Display the order number and day on which clients placed their order.
SELECT
    sales_order.s_order_no,
    DATENAME(WEEKDAY, sales_order.s_order_date)
FROM
    sales_order;

-- 42)	Display the month (in alphabets) and date when the order must deliver.
SELECT
    sales_order.s_order_no,
    DATENAME(MONTH, sales_order.dely_date) AS month,
    DATEPART(DAY, sales_order.dely_date) AS date
FROM
    sales_order;

-- 43)	Display the s_order_date in the format ‘DD-MM-YY’. E.g. 12-February-1996 
SELECT
    FORMAT(s_order_date, 'dd-MMMM-yyyy') AS s_order_date
FROM
    sales_order;

-- 44)	Find the date, 15 days after today’s date.
SELECT
    DATEADD(DAY, 15, GETDATE());

-- 45)	Find the number of days elapsed between today’s date and the delivery date of the orders placed by the clients.
SELECT
    DATEDIFF(DAY, sales_order.dely_date, GETDATE())
FROM
    sales_order;

-- 46)	Change the s_order_date of client_no ‘C00001’ to 24/07/96.
UPDATE
    sales_order
SET
    s_order_date = '1996-07-24',
	dely_date = '1996-07-24'
WHERE
    client_no = 'C00001';

-- 47)	Change the selling price of ‘1.44 Floppy Drive’ to Rs. 1150.00
UPDATE
    product_master
SET
    sell_price = 1150.00
WHERE
    description = '1.44 Floppy Drive';

-- 48)	Delete the records with order number ‘O19001’ from the order table.
DELETE
FROM
    sales_order
WHERE
    s_order_no = 'O19001';

-- 49)	Delete all the records having delivery date before 10th July’96
DELETE
FROM
    sales_order
WHERE
    dely_date < '1996-07-10';

-- 50)	Change the city of client_no ‘C00005’ to ’Bombay’.
UPDATE
    client_master
SET
    city = 'Bombay'
WHERE
    client_no = 'C00005';

-- 51)	Change the delivery date of order number ‘O10008” to 16/08/96
UPDATE
    sales_order
SET
    dely_date = '1996-08-16'
WHERE
    s_order_no = 'O10008';

-- 52)	Change the bal_due of client_no ‘C00001’ to 1000
UPDATE
    client_master
SET
    bal_due = 1000
WHERE
    client_no = 'C00001';

-- 53)	Change the cost price of ‘1.22 Floppy Drive’ to Rs. 950.00.
UPDATE
    product_master
SET
    cost_price = 950.00
WHERE
    description = '1.22 Floppy Drive';
