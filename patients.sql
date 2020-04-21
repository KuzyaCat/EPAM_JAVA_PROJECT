-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- Link to schema: https://app.quickdatabasediagrams.com/#/d/Sotdxt
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.


CREATE TABLE `Patient` (
    `ID_Patient` int  NOT NULL ,
    `First_name` string  NOT NULL ,
    `Second_name` string  NOT NULL ,
    `Age` int  NOT NULL ,
    `Doctor` string  NOT NULL ,
    PRIMARY KEY (
        `ID_Patient`
    )
);

CREATE TABLE `Nurse` (
    `ID_Nurse` int  NOT NULL ,
    `First_name` string  NOT NULL ,
    `Second_name` string  NOT NULL ,
    `Patients_ID` int  NOT NULL ,
    PRIMARY KEY (
        `ID_Nurse`
    )
);

CREATE TABLE `Doctor` (
    `ID_Doctor` int  NOT NULL ,
    `First_name` string  NOT NULL ,
    `Second_name` string  NOT NULL ,
    `Patients_ID` int  NOT NULL ,
    PRIMARY KEY (
        `ID_Doctor`
    )
);

CREATE TABLE `Diagnose` (
    `ID_Diagnose` int  NOT NULL ,
    `ID_Doctor` int  NOT NULL ,
    `ID_Patient` int  NOT NULL ,
    `Name_of_diagnose` string  NOT NULL ,
    PRIMARY KEY (
        `ID_Diagnose`
    )
);

CREATE TABLE `Treatment` (
    `ID_Treatment` int  NOT NULL ,
    `ID_Doctor` int  NOT NULL ,
    `ID_Nurse` int  NOT NULL ,
    `ID_Patient` int  NOT NULL ,
    `Name_of_treatment` string  NOT NULL ,
    PRIMARY KEY (
        `ID_Treatment`
    )
);

CREATE TABLE `Guest` (
    `ID_Guest` int  NOT NULL ,
    `First_name_of_guest` string  NOT NULL ,
    `Second_name_of_guest` string  NOT NULL ,
    PRIMARY KEY (
        `ID_Guest`
    )
);

CREATE TABLE `Appointment` (
    `ID_Appointment` int  NOT NULL ,
    `ID_Doctor` int  NOT NULL ,
    `ID_Patient` int  NOT NULL ,
    `ID_Diagnose` int  NOT NULL ,
    `Appointment_date` date NOT NULL
    PRIMARY KEY (
        `ID_Guest`
    )
);

ALTER TABLE `Nurse` ADD CONSTRAINT `fk_Nurse_Patients_ID` FOREIGN KEY(`Patients_ID`)
REFERENCES `Patient` (`ID_Patient`);

ALTER TABLE `Doctor` ADD CONSTRAINT `fk_Doctor_Patients_ID` FOREIGN KEY(`Patients_ID`)
REFERENCES `Patient` (`ID_Patient`);

ALTER TABLE `Diagnose` ADD CONSTRAINT `fk_Diagnose_ID_Doctor` FOREIGN KEY(`ID_Doctor`)
REFERENCES `Doctor` (`ID_Doctor`);

ALTER TABLE `Diagnose` ADD CONSTRAINT `fk_Diagnose_ID_Patient` FOREIGN KEY(`ID_Patient`)
REFERENCES `Patient` (`ID_Patient`);

ALTER TABLE `Treatment` ADD CONSTRAINT `fk_Treatment_ID_Doctor` FOREIGN KEY(`ID_Doctor`)
REFERENCES `Doctor` (`ID_Doctor`);

ALTER TABLE `Treatment` ADD CONSTRAINT `fk_Treatment_ID_Nurse` FOREIGN KEY(`ID_Nurse`)
REFERENCES `Nurse` (`ID_Nurse`);

ALTER TABLE `Treatment` ADD CONSTRAINT `fk_Treatment_ID_Patient` FOREIGN KEY(`ID_Patient`)
REFERENCES `Patient` (`ID_Patient`);

ALTER TABLE `Appointment` ADD CONSTRAINT `fk_Appointment_ID_Patient` FOREIGN KEY(`ID_Patient`)
REFERENCES `Patient` (`ID_Patient`);

ALTER TABLE `Appointment` ADD CONSTRAINT `fk_Appointment_ID_Doctor` FOREIGN KEY(`ID_Doctor`)
REFERENCES `Doctor` (`ID_Doctor`);

ALTER TABLE `Appointment` ADD CONSTRAINT `fk_Appointment_ID_Diagnose` FOREIGN KEY(`ID_Diagnose`)
REFERENCES `Diagnose` (`ID_Diagnose`);