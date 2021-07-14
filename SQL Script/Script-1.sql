
TRUNCATE TABLE customer CASCADE ;
TRUNCATE TABLE saving_account;
TRUNCATE TABLE checking_account CASCADE;
--DROP TABLE customer;
DROP TABLE customer CASCADE;
DROP TABLE EMPLOYEE;
DROP TABLE saving_account;
DROP TABLE checking_account CASCADE;

-- Admin table 
CREATE TABLE employee_admin(
	admin_id SERIAL PRIMARY KEY,
	first_name Varchar(30 NOT NULL,
	last_name Varchar(30) NOT NULL,
	login_name VARCHAR(60) NOT NULL,
	pswd VARCHAR(50) NOT NULL
	

);
-- Employee table
CREATE TABLE employee(
	employee_id SERIAL PRIMARY KEY,
	emp_role VARCHAR(30),
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	login_name VARCHAR(60) UNIQUE NOT NULL,
	pswd VARCHAR(50) NOT NULL	
);

-- Customer table username
CREATE TABLE customer(
	customer_id SERIAL PRIMARY KEY,
	first_name Varchar(30) NOT NULL,
	last_name Varchar(30) NOT NULL ,
	login_name VARCHAR(50) UNIQUE NOT NULL,
	pswd VARCHAR(60)NOT NULL,
	address VARCHAR(100) NOT NULL,
	has_saving Bool DEFAULT FALSE,
	has_checking Bool DEFAULT FALSE,
	employee_id INTEGER, 
	CONSTRAINT fk_employee
		FOREIGN key(employee_id)
		REFERENCES employee(employee_id)	
);

CREATE TABLE saving_account(
	saving_id SERIAL PRIMARY KEY,
	balance Double PRECISION,
	customer_id SERIAL UNIQUE,
	CONSTRAINT fk_customer
		FOREIGN key(customer_id)
		REFERENCES customer(customer_id) ON DELETE CASCADE	
);

CREATE TABLE checking_account(
	checking_id SERIAL PRIMARY KEY,
	balance Double PRECISION,
	customer_id SERIAL UNIQUE,
	CONSTRAINT fk_customer
		FOREIGN key(customer_id)
		REFERENCES customer(customer_id) ON DELETE CASCADE	
);





INSERT INTO customer(first_name, last_name,login_name,pswd,address, employee_id)
	VALUES ('billy', 'toledo','billy@toledo','password','123 pink drive ', 1);
INSERT INTO customer(first_name, last_name,login_name,pswd,address, EMPLOYEE_ID)
	VALUES ('william', 'toledo','william@toledo','password',' 1234 madeup ', 1);

INSERT INTO customer(first_name, last_name,login_name,pswd,address)
	VALUES ('bill', 'larry','billy@toledo1','password','a street ');

INSERT INTO customer(first_name, last_name,login_name,pswd,address, EMPLOYEE_ID)
	VALUES ('steve', 'larry','larrysteve','password','a street ', 1);


INSERT INTO employee(emp_role,first_name ,last_name ,login_name,pswd)
	VALUES('regular' ,'philip','fry','fry','password')

INSERT INTO employee(emp_role,first_name ,last_name ,login_name,pswd)
	VALUES('admin' ,'boss','man','admin','password')





--UPDATE CUSTOMER 
--SET employee_id = 1
--WHERE customer_id = 3;

--UPDATE saving_account SET balance = 1000 WHERE CUSTOMER_ID = 2;
--UPDATE checking_account SET balance = 1000 WHERE CUSTOMER_ID = 2;

--UPDATE CUSTOMER 
--SET HAS_SAVING = TRUE 
--WHERE customer_id = 2;
--
--UPDATE CUSTOMER 
--SET HAS_CHECKING = true
--WHERE customer_id = 2;



-- DELETE Test
--DELETE FROM SAVING_ACCOUNT 
--WHERE CUSTOMER_ID  = 5;
--
--DELETE FROM CUSTOMER 
--WHERE CUSTOMER_ID =3;


--INSERT INTO saving_account( balance, customer_id)
--	SELECT 200,2
--	FROM customer
--	WHERE customer_id = 2;
--	
--INSERT INTO saving_account( balance,customer_id)
--	SELECT 150.15, 5 FROM customer WHERE customer_id = 5;
	

SELECT customer.first_name  , customer.last_name , saving_account.balance AS saving_amount, checking_account.balance AS checking_ammount
FROM customer 
LEFT JOIN saving_account ON saving_account.customer_id = customer.customer_id 
LEFT JOIN checking_account ON checking_account.customer_id = customer.customer_id ;

SELECT customer.first_name  , customer.last_name , saving_account.balance AS saving_amount , checking_account.balance AS checking_amount
FROM customer 
LEFT JOIN saving_account ON saving_account.customer_id = customer.customer_id 
LEFT JOIN checking_account ON checking_account.customer_id = customer.customer_id WHERE customer.customer_id = 2;

SELECT customer.customer_id  , customer.HAS_SAVING , customer.HAS_CHECKING , saving_account.balance AS saving_amount, checking_account.balance AS checking_ammount
FROM customer 
LEFT JOIN saving_account ON saving_account.customer_id = customer.customer_id 
LEFT JOIN checking_account ON checking_account.customer_id = customer.customer_id ;





