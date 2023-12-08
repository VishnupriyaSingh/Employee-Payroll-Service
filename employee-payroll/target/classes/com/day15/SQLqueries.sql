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
ADD Gender CHAR(1) AFTER Name;
UPDATE employee_payroll set Gender ='M' 
WHERE Name = 'Amit' or Name ='Vivek'or Name = 'Kaushal';
-- UPDATE employee_payroll set Gender ='M'  1 row(s) affected  Rows matched: 3  Changed: 3 



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