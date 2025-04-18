# Employee Management App

A Java EE web application for managing employee records with a modern UI and database integration.

## Tech Stack

- Java EE (JSP, JSF)
- NetBeans IDE
- Payara Server
- PrimeFaces UI
- JDBC + MySQL

## Features

- Add new employees
- View all employees in a table
- Edit employee details inline
- Delete employees with confirmation
- PrimeFaces-based responsive UI
- Email uniqueness validation to prevent duplicates
- JDBC connection pool via Payara for efficient database access

## Setup Instructions

1. Clone or import the project into NetBeans.

2. Create the MySQL database:

   - Use the script: `employeedb_employees.sql` to set up schema and tables.

3. Configure JDBC DataSource:

   - Log into Payara Admin Console: http://localhost:4848
   - Navigate to: Resources → JDBC → JDBC Connection Pools → New
     - Pool Name: EmployeeDBPool
     - Resource Type: javax.sql.DataSource
     - Database Vendor: MySQL
   - Click Next and set the following:
     - User = your MySQL username
     - Password = your MySQL password
     - URL = jdbc:mysql://localhost:3306/employeedb
     - Driver Classname = com.mysql.cj.jdbc.Driver
   - Add these additional properties:
     - serverName = localhost
     - portNumber = 3306
     - databaseName = employeedb
   - Save and test the connection.
   - Go to JDBC Resources → New
     - JNDI Name: jdbc/employeedb
     - Pool Name: EmployeeDBPool

4. Update `EmployeeDAO.java` if needed to match your JNDI name (`jdbc/employeedb`).

5. Build and deploy the app on Payara Server.
     - to deploy on the server go to applications
     - choose the war file in the target directory of the application
     -click ok to deploy	

6. Access the app:

   - Open browser and go to: http://localhost:8080/EmployeeManagementApp/index.xhtml
   - click the launch icon in the dashboard and choose any link to open
				

## Notes

- Email field is validated to ensure no duplicates are inserted.
- Server logs will show exceptions if the database is not connected.
- Make sure MySQL service is running before testing.

