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

**State-chart Diagram (Reimbursement Statuses)** 
![](./imgs/state-chart.jpg)

**Reimbursement Types**

Employees must select the type of reimbursement as: LODGING, TRAVEL, FOOD, or OTHER.

**Logical Model**

![](./imgs/logical.jpg)

**Physical Model**

![](./imgs/physical.jpg)

**Use Case Diagram**

![](./imgs/use-case.jpg)

**Activity Diagram**

![](./imgs/activity.jpg)

