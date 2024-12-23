create database mentormanage;


show databases;

use mentormanage;

CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    FirstName VARCHAR(100),
    LastName VARCHAR(100),
    Role VARCHAR(50)
);
CREATE TABLE MentorGroup (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    GroupName VARCHAR(255) NOT NULL,
    MentorID INT NOT NULL,
    MentorName VARCHAR(255) NOT NULL,
    MenteeID INT NOT NULL,
    MenteeName VARCHAR(255) NOT NULL,
    FOREIGN KEY (MentorID) REFERENCES User(Id),  -- Assuming you have a 'Users' table with 'Id' as primary key
    FOREIGN KEY (MenteeID) REFERENCES User(Id)   -- Assuming 'Users' table also stores mentees
);






