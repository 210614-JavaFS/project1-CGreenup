# Employee Reimbursment System (ERS)

## Project Description
The Expense Reimbursement System (ERS) manages the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Technologies Used
* HTML/CSS/JS
* Java
* Tomcat
* JDBC
* PostgreSQL
* AWS RDS


## Features

* Runs as a single page application
* Uses JDBC to connect to an AWS RDS PostgreSQL database. 
* deploys onto a Tomcat Server. 
* Middle tier uses Servlet technology for dynamic Web application development. 
* Passwords are encrypted in Java using SHA-256 Encryption
* Passwords are securely stored in the database. 
* The middle tier follows proper layered architecture
* Has reasonable test coverage of the service layer 
* Implements Logback for appropriate logging. 

## Getting Started

Requires Tomcat, Maven, and Spring Tool Suite or Java Eclipse:

* git clone https://github.com/210614-JavaFS/project1-CGreenup.git
* import project folder using Spring Tool Suite(STS) or Java Eclipse (JE)
* Install dependencies using Maven
* Add this line of code to the Tomcat's Server.xml: 
      
      <Context docBase="project1-CGreenup" path="/project1" reloadable="true" source="org.eclipse.jst.jee.server:project1-CGreenup"/></Host>
* Deploy project onto Tomcat Server
* Go to http://localhost:8080/static/

## Usage

Once on the locally hosted site (http://localhost:8080/static/) The user will be able to log in to a defined account using the log in page and fields

If the user inputs a valid login, the user will be redirected
* If the user is a finance manager, they will be sent to the manager functions page
* If the user in an employee, they will be shown the employee page

Managers can approve or deny incoming requests using the buttons on the right.
Managers can also filter requests by request status using the filters in the top right

Employees are shown all of their previously submitted requests with their status and information in a table.
Employees can also make a new request clicking the green button at the top
