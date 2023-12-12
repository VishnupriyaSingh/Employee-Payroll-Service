-- UC1
CREATE DATABASE payroll_service;
-- CREATE DATABASE payroll_service	1 row(s) affected	
SHOW DATABASES;
-- information_schema
-- mysql
-- payroll_service
-- performance_schema
-- sys
USE payroll_service;
-- USE payroll_service	0 row(s) affected	


-- UC2
SELECT DATABASE();
CREATE TABLE employee_payroll
(
ID INT unsigned NOT NULL auto_increment,
Name VARCHAR(30) NOT NULL,
Salary DOUBLE NOT NULL,
Start DATE NOT NULL,
PRIMARY KEY (ID)
);
-- CREATE TABLE employee_payroll 0 row(s) affected


-- UC3
INSERT INTO employee_payroll(ID, Name, Salary, Start) VALUES
(1, 'Amit', 1000, '2023-07-12'),(2, 'Kaushal', 2000, '2023-06-12'),(3, 'Vivek', 5000, '2023-05-12');
-- INSERT INTO employee_payroll(ID, Name, Salary, Start) VALUES	3 row(s) affected  Records: 3  Duplicates: 0 


-- UC4
SELECT * FROM employee_payroll;
-- 1	Amit	1000	2023-07-12
-- 2	Kaushal	2000	2023-06-12
-- 3	Vivek	5000	2023-05-12


-- UC5
SELECT Salary FROM employee_payroll 
WHERE Name = 'Vivek';
-- 5000
SELECT Salary FROM employee_payroll 
WHERE Start BETWEEN CAST('2023-6-12' AS DATE) AND DATE(NOW());
-- 1000
-- 2000


-- UC6
ALTER TABLE employee_payroll 
ADD COLUMN Gender CHAR(1) AFTER Name;
UPDATE employee_payroll set Gender ='M' 
WHERE Name = 'Amit' or Name ='Vivek' or Name = 'Kaushal';
-- UPDATE employee_payroll set Gender ='M'  1 row(s) affected  Rows matched: 3  Changed: 3 
SELECT * FROM employee_payroll;


-- UC7 
SELECT SUM(Salary) FROM employee_payroll
WHERE Gender = 'M' GROUP BY Gender;
-- 8000
SELECT AVG(Salary) FROM employee_payroll
WHERE Gender = 'M' GROUP BY Gender;
-- 2666.6666666666665
SELECT MIN(Salary) FROM employee_payroll
WHERE Gender = 'M' GROUP BY Gender;
-- 1000
SELECT MAX(Salary) FROM employee_payroll
WHERE Gender = 'M' GROUP BY Gender;
-- 5000
SELECT COUNT(Salary) FROM employee_payroll
WHERE Gender = 'M' GROUP BY Gender;
-- 3


-- UC8
ALTER TABLE employee_payroll
ADD COLUMN phone VARCHAR(10),
ADD COLUMN address VARCHAR(50) DEFAULT 'Not Specified',
ADD COLUMN department VARCHAR(50) NOT NULL;
UPDATE employee_payroll
SET Address = 'Not Specified' WHERE Address IS NULL OR Address = '';
UPDATE employee_payroll
SET Department = 'Frontend' WHERE ID= 1;
UPDATE employee_payroll
SET Department = 'DevOps' WHERE ID= 2;
UPDATE employee_payroll
SET Department = 'Backend' WHERE ID= 3;
UPDATE employee_payroll
SET Department = 'General' WHERE Department IS NULL OR Department = '';
UPDATE employee_payroll
SET Phone = '123' WHERE ID= 1;
UPDATE employee_payroll
SET Phone = '345' WHERE ID= 2;
UPDATE employee_payroll
SET Address = '2C' WHERE ID= 1;
UPDATE employee_payroll
SET Address = '4D' WHERE ID= 2;
SELECT * FROM employee_payroll;
-- 1	Amit	M	1000	2023-07-12	 123	2C				 Frontend
-- 2	Kaushal	M	2000	2023-06-12	 345	4D				 DevOps
-- 3	Vivek	M	5000	2023-05-12	    	Not Specified	 Backend


-- UC9
ALTER TABLE employee_payroll
ADD COLUMN BasicPay DOUBLE,
ADD COLUMN Deductions DOUBLE,
ADD COLUMN TaxablePay DOUBLE,
ADD COLUMN IncomeTax DOUBLE,
ADD COLUMN NetPay DOUBLE;
UPDATE employee_payroll
SET Phone = '1234567890', Address = 'Address 1', Department = 'IT',
    BasicPay = 900, Deductions = 100, TaxablePay = 800, IncomeTax = 50, NetPay = 750
WHERE ID = 1;

UPDATE employee_payroll
SET Phone = '9876543210', Address = 'Address 2', Department = 'HR',
    BasicPay = 1800, Deductions = 200, TaxablePay = 1600, IncomeTax = 100, NetPay = 1500
WHERE ID = 2;

UPDATE employee_payroll
SET Phone = '9998887770', Address = 'Address 3', Department = 'Finance',
    BasicPay = 4800, Deductions = 500, TaxablePay = 4300, IncomeTax = 300, NetPay = 4000
WHERE ID = 3;
SELECT * FROM employee_payroll;
-- 1	Amit	M	1000	2023-07-12	1234567890	Address 1	IT	900	100	800	50	750
-- 2	Kaushal	M	2000	2023-06-12	9876543210	Address 2	HR	1800	200	1600	100	1500
-- 3	Vivek	M	5000	2023-05-12	9998887770	Address 3	Finance	4800	500	4300	300	4000


-- UC 10
INSERT INTO employee_payroll (ID, Name, Salary, Start, Gender, Phone, Address, Department, BasicPay, Deductions, TaxablePay, IncomeTax, NetPay)
VALUES
    (4, 'Terissa', 2500, '2023-08-12', 'F', '1112223334', 'Address 4', 'Sales & Marketing', 2300, 200, 2100, 150, 1950);

INSERT INTO employee_payroll (ID, Name, Salary, Start, Gender, Phone, Address, Department, BasicPay, Deductions, TaxablePay, IncomeTax, NetPay)
VALUES
    (5, 'Terissa', 2600, '2023-09-12', 'F', '5556667778', 'Address 5', 'Sales & Marketing', 2400, 200, 2200, 180, 2020);
SELECT * FROM employee_payroll;
-- 1	Amit	M	1000	2023-07-12	1234567890	Address 1	IT					900	  100	800
-- 2	Kaushal	M	2000	2023-06-12	9876543210	Address 2	HR					1800  200	1600
-- 3	Vivek	M	5000	2023-05-12	9998887770	Address 3	Finance				4800  500	4300
-- 4	Terissa	F	2500	2023-08-12	1112223334	Address 4	Sales & Marketing	2300  200	2100
-- 5	Terissa	F	2600	2023-09-12	5556667778	Address 5	Sales & Marketing	2400  200	2200


-- UC 11
-- Employee table
CREATE TABLE Employee (
    EmployeeID INT PRIMARY KEY,
    Name VARCHAR(50),
    Gender CHAR(1),
    StartDate DATE,
    Phone VARCHAR(15),
    Address VARCHAR(100)
);
INSERT INTO Employee (EmployeeID, Name, Gender, StartDate, Phone, Address)
VALUES 
    (1, 'Amit', 'M', '2023-07-12', '1234567890', 'Address 1'),
    (2, 'Kaushal', 'M', '2023-06-12', '9876543210', 'Address 2'),
    (3, 'Vivek', 'M', '2023-05-12', '9998887770', 'Address 3'),
    (4, 'Terissa', 'F', '2023-08-12', '1112223334', 'Address 4'),
    (5, 'Terissa', 'F', '2023-09-12', '5556667778', 'Address 5');

-- Department table
CREATE TABLE Department (
    DepartmentID INT PRIMARY KEY,
    DepartmentName VARCHAR(50)
);
INSERT INTO Department (DepartmentID, DepartmentName)
VALUES 
    (1, 'IT'),
    (2, 'HR'),
    (3, 'Finance'),
    (4, 'Sales & Marketing');

-- SalaryDetails table
CREATE TABLE SalaryDetails (
    SalaryID INT PRIMARY KEY,
    EmployeeID INT,
    BasicPay DOUBLE,
    Deductions DOUBLE,
    TaxablePay DOUBLE,
    IncomeTax DOUBLE,
    NetPay DOUBLE,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);
INSERT INTO SalaryDetails (SalaryID, EmployeeID, BasicPay, Deductions, TaxablePay, IncomeTax, NetPay)
VALUES 
    (1, 1, 900, 100, 800, 50, 750),
    (2, 2, 1800, 200, 1600, 100, 1500),
    (3, 3, 4800, 500, 4300, 300, 4000),
    (4, 4, 2300, 200, 2100, 150, 1950),
    (5, 5, 2400, 200, 2200, 180, 2020);

-- EmployeeDepartment table (for Many-to-Many relationship)
CREATE TABLE EmployeeDepartment (
    EmployeeID INT,
    DepartmentID INT,
    PRIMARY KEY (EmployeeID, DepartmentID),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID),
    FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID)
);
INSERT INTO EmployeeDepartment (EmployeeID, DepartmentID)
VALUES 
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 4);

-- Redoing UC7 for UC11
SELECT d.DepartmentName, SUM(sd.NetPay) AS TotalSalary
FROM Department d
JOIN EmployeeDepartment ed ON d.DepartmentID = ed.DepartmentID
JOIN SalaryDetails sd ON ed.EmployeeID = sd.EmployeeID
GROUP BY d.DepartmentName;
-- DepartmentName       Total Salary
-- IT					750
-- HR					1500
-- Finance	  			4000
-- Sales & Marketing	3970


-- UC12
-- Retrive UC4,UC5,UC7 if working properly
SELECT * FROM Employee; 
/*1	Amit	M	2023-07-12	1234567890	Address 1
2	Kaushal	M	2023-06-12	9876543210	Address 2
3	Vivek	M	2023-05-12	9998887770	Address 3
4	Terissa	F	2023-08-12	1112223334	Address 4
5	Terissa	F	2023-09-12	5556667778	Address 5*/

SELECT SalaryDetails.NetPay AS Salary
FROM Employee
JOIN SalaryDetails ON Employee.EmployeeID = SalaryDetails.EmployeeID
WHERE Employee.Name = 'Vivek';
-- 4000

SELECT AVG(SalaryDetails.NetPay) AS AvgSalary
FROM Employee
JOIN SalaryDetails ON Employee.EmployeeID = SalaryDetails.EmployeeID
WHERE Employee.Gender = 'M';
-- 2083.3333333333335
