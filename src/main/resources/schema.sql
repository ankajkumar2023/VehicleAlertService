CREATE TABLE user1 (

	userId  INT NOT NULL PRIMARY KEY  auto_increment,
	firstName VARCHAR(15) NOT NULL,
	lastName VARCHAR(15) NOT NULL,
	emailId VARCHAR(50) NOT NULL,
	mobile VARCHAR(10) NOT NULL,
	street VARCHAR(20) NOT NULL,
	city VARCHAR(25) NOT NULL,
	state VARCHAR(15) NOT NULL,
	country VARCHAR(10) NOT NULL,
	status VARCHAR(8) NOT NULL,
	createDate DATE NOT NULL,
	modifiedDate DATE
);

CREATE TABLE vehicle1 (

	vehicleId  INT NOT NULL PRIMARY KEY  auto_increment, 
	userId  INT NOT NULL,
	vehicleNumber VARCHAR(10), 
	vehicleRC  VARCHAR(15),
	vehicleType VARCHAR(10),
	yearOfRegistration VARCHAR(4),
	ownerName VARCHAR(15),
	mobileNumber VARCHAR(13),
	createDate DATE NOT NULL,
    modifiedDate DATE

);

CREATE TABLE login (

	loginId  INT NOT NULL PRIMARY KEY  auto_increment, 
	userId  INT NOT NULL,
	userName VARCHAR(15), 
	password  VARCHAR(15),
	createDate DATE NOT NULL,
    modifiedDate DATE

);

CREATE TABLE Role (

	roleId  INT NOT NULL PRIMARY KEY  auto_increment, 
	loginId  INT,
	role VARCHAR(20),
	createDate DATE NOT NULL,
    modifiedDate DATE

);

ALTER TABLE vehicle1 ADD FOREIGN KEY (userId)  REFERENCES  user1(userId);
ALTER TABLE login ADD FOREIGN KEY (userId)  REFERENCES  user1(userId);
ALTER TABLE Role ADD FOREIGN KEY (loginId)  REFERENCES  login(loginId);




