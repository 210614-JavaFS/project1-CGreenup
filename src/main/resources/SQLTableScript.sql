
DROP TABLE IF EXISTS ERS_REIMBURSEMENT_STATUS CASCADE;
CREATE TABLE ERS_REIMBURSEMENT_STATUS(
	REIMB_STATUS_ID 	SERIAL 		PRIMARY KEY NOT NULL,
	REIMB_STATUS 		VARCHAR(10)	NOT NULL UNIQUE 
);

INSERT INTO ERS_REIMBURSEMENT_STATUS (REIMB_STATUS)
VALUES	('PENDING'),
		('APPROVED'),
		('DENIED');

DROP TABLE IF EXISTS ERS_REIMBURSEMENT_TYPE CASCADE;
CREATE TABLE ERS_REIMBURSEMENT_TYPE(
	REIMB_TYPE_ID 		SERIAL 		PRIMARY KEY NOT NULL,
	REIMB_TYPE 			VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS ERS_USER_ROLES CASCADE;
CREATE TABLE ERS_USER_ROLES(
	ERS_USER_ROLES_ID 	SERIAL 		PRIMARY KEY NOT NULL,
	USER_ROLE 			VARCHAR(10)	NOT NULL UNIQUE
);
INSERT INTO ERS_USER_ROLES (USER_ROLE) 
VALUES 	('MANAGER'), 
		('EMPLOYEE');

DROP TABLE IF EXISTS ERS_USERS CASCADE;
CREATE TABLE ERS_USERS(
	ERS_USER_ID 	SERIAL 			PRIMARY KEY,
	ERS_USERNAME 	VARCHAR(50)		UNIQUE NOT NULL,
	ERS_PASSWORD	VARCHAR(50)		NOT NULL,
	USER_EMAIL		VARCHAR(150)	UNIQUE NOT NULL,
	USER_FIRST_NAME	VARCHAR(100)	NOT NULL,
	USER_LAST_NAME	VARCHAR(100)	NOT NULL,
	USER_ROLE_ID	INTEGER			NOT NULL REFERENCES ERS_USER_ROLES(ERS_USER_ROLES_ID)
);
INSERT INTO ERS_USERS (ERS_USERNAME, ERS_PASSWORD, USER_EMAIL, USER_FIRST_NAME, USER_LAST_NAME, USER_ROLE_ID)
VALUES 
('JuneAdmin', 'pass', 'example@email.com', 'June', 'Greenup', (SELECT ERS_USER_ROLES_ID FROM ERS_USER_ROLES UR WHERE UR.USER_ROLE = 'MANAGER'));


DROP TABLE IF EXISTS ERS_REIMBURSEMENT CASCADE;
CREATE TABLE ERS_REIMBURSEMENT(
	REIMB_ID SERIAL 	PRIMARY KEY 		NOT NULL,
	REIMB_AMOUNT 		DOUBLE PRECISION 	NOT NULL CHECK(REIMB_AMOUNT >= 0),
	REIMB_SUBMITTED		TIMESTAMP			NOT NULL,
	REIMB_RESOLVED		TIMESTAMP,
	REIMB_DESCRIPTION	VARCHAR(250),
	REIMB_RECEIPT		BYTEA,
	REIMB_AUTHOR		INTEGER				NOT NULL REFERENCES ERS_USERS(ERS_USER_ID),
	REIMB_RESOLVER		INTEGER				REFERENCES ERS_USERS(ERS_USER_ID),
	REIMB_STATUS_ID		INTEGER				NOT NULL REFERENCES ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID),
	REIMB_TYPE_ID		INTEGER				NOT NULL REFERENCES ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID)
);