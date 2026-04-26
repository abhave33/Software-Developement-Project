ALTER TABLE employees
ADD COLUMN jobTitle VARCHAR(50);

ALTER TABLE employees
ADD COLUMN division VARCHAR(50);

CREATE TABLE pay_statement (
    payId INT PRIMARY KEY AUTO_INCREMENT,
    empid INT,
    payDate DATE,
    grossPay DOUBLE,
    taxes DOUBLE,
    netPay DOUBLE,
    FOREIGN KEY (empid) REFERENCES employees(empid)
);
