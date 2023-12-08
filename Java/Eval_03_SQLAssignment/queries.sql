--1

CREATE TABLE Products
(
	Product_ID VARCHAR(10),
	Product_Name VARCHAR(30),
	Money_Type char(1),
	Min_Premium NUMERIC(5),
	Min_Period NUMERIC(2),
	Min_Mat_Years NUMERIC(2),
	Inaugural_Year NUMERIC(4),
	Loans char(1),
	PRIMARY KEY (Product_ID)
);


--2.

CREATE TABLE Customers
(
	Cust_ID VARCHAR(10),
	Name VARCHAR(30),
	Address VARCHAR(100),
	City VARCHAR(20),
	State VARCHAR(20),
	PinCode NUMERIC(6),
	Phone1 VARCHAR(15),
	Phone2 VARCHAR(15),
	DATE_Of_Birth DATE,
	Med_Rep_ID VARCHAR(10),
	Introducer VARCHAR(10),
	DOB_Proof char(1),
	Identity_Proof char(1),
	Comments VARCHAR(100),
	PRIMARY KEY (Cust_ID),
	FOREIGN KEY (Introducer) REFERENCES Customers
);


--3.

CREATE TABLE Policy
(
	Policy_No VARCHAR(10),
	Amount NUMERIC(10),
	DATE_Sold DATE,
	DATE_Inception DATE,
	DATE_Mature DATE,
	Product_ID VARCHAR(10),
	PRIMARY KEY (Policy_No),
	FOREIGN KEY (Product_ID) REFERENCES Products
);


--4.

CREATE TABLE Agency
(
	Agency_No VARCHAR(10),
	Name VARCHAR(30),
	Address VARCHAR(100),
	City VARCHAR(20),
	State VARCHAR(20),
	Pin NUMERIC(6),
	Contact_Person VARCHAR(20),
	PRIMARY KEY (Agency_No)
);


--5.

CREATE TABLE Agents
(
	Agent_No VARCHAR(10),
	Agency_No VARCHAR(10),
	Name VARCHAR(30),
	Address VARCHAR(100),
	City VARCHAR(20),
	State VARCHAR(20),
	Pin NUMERIC(6),
	PRIMARY KEY (Agent_No),
	FOREIGN KEY(Agency_No) REFERENCES Agency
);


--6.

CREATE TABLE Employees
(
	Employee_ID VARCHAR(10),
	Name VARCHAR(30),
	Address VARCHAR(100),
	City VARCHAR(20),
	State VARCHAR(20),
	Pin NUMERIC(6),
	PRIMARY KEY (Employee_ID)
);


--7.

CREATE TABLE Loans
(
	Loan_No VARCHAR(10),
	Policy_No VARCHAR(10),
	Amount NUMERIC(5),
	DATE_Loaned DATE,
	Sanctioned_By VARCHAR(10),
	PRIMARY KEY (Loan_No),
	FOREIGN KEY (Policy_No) REFERENCES Policy,
	FOREIGN KEY (Sanctioned_By) REFERENCES Employees
);


--8.

CREATE TABLE Maturity
(
	Maturity_No VARCHAR(10),
	DATE_Matured DATE,
	Policy_No VARCHAR(10),
	Processed_By VARCHAR(10),
	PRIMARY KEY (Maturity_No),
	FOREIGN KEY (Policy_No) REFERENCES Policy,
	FOREIGN KEY (Processed_By) REFERENCES Employees
);


--9.

CREATE TABLE Mat_Payments
(
	Mat_Payment_No VARCHAR(10),
	Maturity_No VARCHAR(10),
	Amount NUMERIC(5),
	DATE_Paid DATE,
	Processed_By VARCHAR(10),
	PRIMARY KEY (Mat_Payment_No),
	FOREIGN KEY (Maturity_No) REFERENCES Maturity,
	FOREIGN KEY (Processed_By) REFERENCES Employees
);

--10.

CREATE TABLE Premiums
(
	Premium_No VARCHAR(20),
	Policy_No VARCHAR(10),
	Amount NUMERIC(5),
	DATE_Paid DATE,
	Processed_By VARCHAR(10),
	PRIMARY KEY (Premium_No),
	FOREIGN KEY (Policy_No) REFERENCES [Policy],
	FOREIGN KEY (Processed_By) REFERENCES Employees
);


--11.

CREATE TABLE Policy_Customer
(
	Policy_No VARCHAR(10),
	Cust_Id VARCHAR(10),
	PRIMARY KEY (Policy_No, Cust_Id),
	FOREIGN KEY (Policy_No) REFERENCES Policy,
	FOREIGN KEY (Cust_Id) REFERENCES Customers
);


--12.

CREATE TABLE Policy_Agent
(
	Policy_No VARCHAR(10),
	Agent_No VARCHAR(10),
	Percentage NUMERIC(2),
	PRIMARY KEY (Policy_No, Agent_No),
	FOREIGN KEY (Policy_No) REFERENCES Policy,
	FOREIGN KEY (Agent_No) REFERENCES Agents
);


-- Queries

-- 1.	List all the products that the company sells (list the product code, the product name and the year in which it was introduced).
SELECT *
FROM Products;


-- 2.	List all the products that were introduced before 1990 and against the policies of which, no loan can be taken.
SELECT *
FROM Products
WHERE Inaugural_Year < 1990
	AND Loans = 'N';


-- 3.	List ALL customers with the name of their introducers (list the customer -- name, the customer's city and the name of the person that introduced that customer).
SELECT C.Name, C.City, I.Name
FROM Customers C
	JOIN Customers I
	ON C.Introducer = I.Cust_ID;


-- 4.	List all customers who have introduced at least one other customer.
SELECT *
FROM Customers
WHERE Cust_ID IN (SELECT DISTINCT Introducer
FROM Customers);


-- 5.	Write a query to test the previous query. List all those customers who have not introduced even one customer.
SELECT *
FROM Customers
WHERE Cust_ID NOT IN (SELECT DISTINCT Introducer
FROM Customers);

-- 6.	List all those customers who have been introduced by another customer, but where the introducer is from a different city. List the names and cities of both, the customer as well as the introducer.
SELECT C.Name, C.City, I.Name, I.City
FROM Customers C
	JOIN Customers I
	ON C.Introducer = I.Cust_ID
WHERE C.City <> I.City;


-- 7.	 List all the policies sold, in the chronological order of the date of sale (most recent last). List the policy number, the date of sale, the date of maturity and the sum assured.
SELECT P.Policy_No, P.DATE_Sold, M.DATE_Matured, P.Amount
FROM Policies P
	JOIN Maturity M
	ON P.Policy_No = M.Policy_No
ORDER BY P.DATE_Sold;


-- 8.	List all the policies (policy number, sum assured and date of maturity) with the name(s) of the policy holder(s); order the listing by the policy number.
SELECT P.Policy_No, P.Amount, M.DATE_Matured, C.Name
FROM Policies P
	JOIN Policy_Customer PC
	ON P.Policy_No = PC.Policy_No
	JOIN Customers C
	ON PC.Cust_Id = C.Cust_ID
	JOIN Maturity M
	ON P.Policy_No = M.Policy_No
ORDER BY P.Policy_No;


-- 9.	 List the agents in descending order of the number of policies sold by them. List the agent code, agent's name, and the number of policies sold by him.
SELECT A.Agent_No, A.Name, COUNT(P.Policy_No)
FROM Agents A
	JOIN Policies P
	ON A.Agent_No = P.Agent_No
GROUP BY A.Agent_No
ORDER BY COUNT(P.Policy_No) DESC;


-- 10.	 List all the products in ascending order of the number of policies of that product sold.
SELECT P.Product_Code, COUNT(P.Policy_No)
FROM Policies P
GROUP BY P.Product_Code
ORDER BY COUNT(P.Policy_No);


-- 11.	List all those policies that are held jointly - i.e. by more than one holder. List the policy number, date of sale of the policy, the sum assured, date of maturity and the names of the policyholders.



-- 12.	Write a query to test the previous query. List all those policies that are held by a sole applicant. List the same details as the previous query.



-- 13.	 Write a query that is exactly the same as previous query, but for one detail. Here, you are required to also list the name of the agent that sold the policy i.e. your listing should display the policy number, date of sale of the policy. the sum assured, date of maturity, the policy holder's name and the name of the salesman that sold it.



-- 14.	 Name the oldest product of the company. List the Product ID, the name of the product and the year in which the product was launched.
SELECT P.Product_Code, P.Name, P.Inaugural_Year
FROM Products P
WHERE P.Inaugural_Year = (SELECT MIN(P.Inaugural_Year)
FROM Products P);

-- 15.	 List IN THE SAME QUERY, the oldest AND the newest product of the company. The oldest must be listed FIRST. List the product_id, the product name and the inaugural year.
	SELECT P.Product_Code, P.Name, P.Inaugural_Year
	FROM Products P
	WHERE P.Inaugural_Year = (SELECT MIN(P.Inaugural_Year)
	FROM Products P)
UNION
	SELECT P.Product_Code, P.Name, P.Inaugural_Year
	FROM Products P
	WHERE P.Inaugural_Year = (SELECT MAX(P.Inaugural_Year)
	FROM Products P);

-- 16.	 List all those policies (the policy number and the customer) that were sold by agents working under an agency.
SELECT P.Policy_No, C.Name
FROM Policies P
	JOIN Policy_Customer PC
	ON P.Policy_No = PC.Policy_No
	JOIN Customers C
	ON PC.Cust_Id = C.Cust_ID
	JOIN Policy_Agent PA
	ON P.Policy_No = PA.Policy_No
	JOIN Agent A
	ON PA.Agent_No = A.Agent_No
WHERE A.Agency_No <> NULL;

-- 17.	 List all the loans sanctioned by Ruchira. List the loan number, the amount. loaned, the customer for whom it was sanctioned, and the amount of the policy against which the loan has been taken.
SELECT L.Loan_No, L.Loan_Amount, C.Name, P.Amount
FROM Loans L
	JOIN Customers C
	ON L.Cust_ID = C.Cust_ID
	JOIN Policies P
	ON L.Policy_No = P.Policy_No
WHERE L.Loan_Approved_By = (SELECT E.Employee_ID
FROM Employees E
WHERE E.Name = 'Ruchira');


-- 18.	 List the agencies that have sold at least 2 policies, in descending order of the number of policies sold by them.
SELECT A.Agency_No, COUNT(P.Policy_No)
FROM Agency A
	JOIN Agents Ag
	ON A.Agency_No = Ag.Agency_No
	JOIN Policy_Agent PA
	ON Ag.Agent_No = PA.Agent_No
	JOIN Policies P
	ON PA.Policy_No = P.Policy_No
GROUP BY A.Agency_No
HAVING COUNT(P.Policy_No) >= 2
ORDER BY COUNT(P.Policy_No) DESC;


-- 19.	 List the customers in descending order of the number of policies bought by them. List the name of the customer and the number of policies bought by them.



-- 20.	 List the customers who have availed of a loan against a policy. Note that a loan is extended in respect of a policy.



-- 21.	 List the customers and the number of policies that have been bought by people introduced by them. List only those customers who have introduced at least one other customer.



-- 22.	 List the agents (the agents name, the agency he works for and the city he hails from) who have sold at least one policy of "Jeevan Suraksha".
SELECT Ag.Name, Ag.Agency_No, Ag.City
FROM Agents Ag
	JOIN Policy_Agent PA
	ON Ag.Agent_No = PA.Agent_No
	JOIN Policies P
	ON PA.Policy_No = P.Policy_No
WHERE P.Product_Code = (SELECT P.Product_Code
FROM Products P
WHERE P.Name = 'Jeevan Suraksha');


-- 23.	 Find out which policies matured in April 1999 (list the policy number, date of maturity, the customer's name and the sum assured - i.e., amount of the policy).
SELECT P.Policy_No, P.DATE_Mature, C.Name, P.Sum_Assured
FROM Policies P
	JOIN Policy_Customer PC
	ON P.Policy_No = PC.Policy_No
	JOIN Customers C
	ON PC.Cust_Id = C.Cust_ID
WHERE MONTH(P.DATE_Mature) = 4
	AND YEAR(P.DATE_Mature) = 1999;


-- 24.	 List all those policies in respect of which the first premium was paid after more than a year from the date of sale of that policy.



-- 25.	 Report the premia received during the calendar years 1996, 1997 and 1998. Order the listing by date of payment. (List the date of payment, the policy number, the name/s of the customers holding that policy and the amount paid).
SELECT P.DATE_Paid, P.Policy_No, C.Name, P.Amount
FROM Premium Pr
	JOIN Policies P
	ON Pr.Policy_No = P.Policy_No
	JOIN Policy_Customer PC
	ON P.Policy_No = PC.Policy_No
	JOIN Customers C
	ON PC.Cust_Id = C.Cust_ID
WHERE YEAR(P.DATE_Paid) IN (1996, 1997, 1998)
ORDER BY P.DATE_Paid;


-- 26.	 List the premia paid in respect of policies sold by agents Francis and Schubert. (List the policy number, customer, date of payment and the amount paid).

-- 27.	 Draw up a list of the payments that will have to be made by the company in 2005 in respect of policies that will mature then (list the policy no., date of maturity, the customer's name and the amount payable).
SELECT P.Policy_No, P.DATE_Mature, C.Name, P.Amount
FROM Policies P
	JOIN Policy_Customer PC
	ON P.Policy_No = PC.Policy_No
	JOIN Customers C
	ON PC.Cust_Id = C.Cust_ID
WHERE YEAR(P.DATE_Mature) = 2005;


-- 28.	 List all the products (the product id, the product name and its inaugural year) and the number of products that were introduced after that product.

-- 29.	 Write a query that lists for each agent, the number of policies sold by him AND the following details of each policy sold by him - policy number, date of sale, date of maturity, sum assured and the names of the policy holder(s).

-- 30.	 List all those policies that did not mature on the date specified in the policy.
