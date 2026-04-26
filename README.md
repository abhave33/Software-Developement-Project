# Employee Management Project

## Compile

Compile the Java files with the MySQL JDBC driver:

```bash
javac -cp "lib/mysql-connector-j-9.7.0.jar:src" src/*.java
```

On Windows Command Prompt, use a semicolon instead of a colon:

```bat
javac -cp "lib/mysql-connector-j-9.7.0.jar;src" src\*.java
```

## Run

Run the console program with:

```bash
java -cp "lib/mysql-connector-j-9.7.0.jar:src" SearchEmployee
```

On Windows Command Prompt:

```bat
java -cp "lib/mysql-connector-j-9.7.0.jar;src" SearchEmployee
```

## Sample Menu Inputs

When the program starts, the menu will look like this:

```text
1. Search by Employee ID
2. Search by First Name
3. Search by Last Name
4. Search by SSN
5. Update Employee Information
6. Insert New Employee
7. Exit
```

Example: search by employee ID

```text
Enter your choice: 1
Enter Employee ID: 101
```

Example: search by first name

```text
Enter your choice: 2
Enter First Name: Snoopy
```

Example: update employee information

```text
Enter your choice: 5
Enter Employee ID to update: 101
Enter new email: snoopy@email.com
Enter new salary: 65000
Enter new SSN: 123456789
```

Example: insert new employee

```text
Enter your choice: 6
Enter Employee ID: 110
Enter First Name: Charlie
Enter Last Name: Brown
Enter Email: charlie@email.com
Enter Salary: 55000
Enter SSN: 987654321
```

Example: exit

```text
Enter your choice: 7
```

## Database Setup Steps

1. Make sure MySQL is installed and running.
2. Create the database:

```sql
CREATE DATABASE employeedata3;
```

3. Select the database:

```sql
USE employeedata3;
```

4. Create the `employees` table if it does not already exist:

```sql
CREATE TABLE employees (
    empid INT PRIMARY KEY,
    Fname VARCHAR(50),
    Lname VARCHAR(50),
    email VARCHAR(100),
    Salary DOUBLE,
    SSN VARCHAR(9)
);
```

5. If your table already exists without `SSN`, run the provided script:

```sql
SOURCE sql/add_ssn_column.sql;
```

6. Add a few sample rows so search, update, and insert can be tested:

```sql
INSERT INTO employees (empid, Fname, Lname, email, Salary, SSN) VALUES
(101, 'Snoopy', 'Brown', 'snoopy@email.com', 50000, '123456789'),
(102, 'Charlie', 'Brown', 'charlie@email.com', 55000, '987654321');
```

7. Update the database login in [src/DBConnection.java](/Users/anjalibhave/Desktop/SoftwareProject/Software-Developement-Project-main/src/DBConnection.java) if your MySQL username, password, or database name is different.
