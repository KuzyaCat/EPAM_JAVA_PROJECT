CREATE TABLE PATIENT 
(
  ID_PATIENT INT NOT NULL 
, FIRST_NAME VARCHAR(20) NOT NULL 
, SECOND_NAME VARCHAR(20) NOT NULL 
, AGE INT NOT NULL 
, LOGIN VARCHAR(20) NOT NULL 
, PASSWORD VARCHAR(20) NOT NULL 
, DOCTOR_ID INT NOT NULL 
, RECOVERED BOOLEAN NOT NULL 
, CONSTRAINT PATIENT_PK PRIMARY KEY 
  (
    ID_PATIENT 
  )
);

INSERT INTO PATIENT
VALUES (1, 'Anatoliy', 'Tsoy', 55,'tsoyzhiv','kukushka', 1,true);

INSERT INTO PATIENT
VALUES (2, 'Sergey', 'Trofimov', 44,'konyachok','shashlichok', 2, false);


CREATE TABLE NURSE 
(
  ID_NURSE INT NOT NULL 
, FIRST_NAME VARCHAR(20) NOT NULL 
, SECOND_NAME VARCHAR(20) NOT NULL
, AGE INT NOT NULL
, LOGIN VARCHAR(20) NOT NULL 
, PASSWORD VARCHAR(20) NOT NULL 
, PATIENT_ID INT 
, CONSTRAINT NURSE_PK PRIMARY KEY 
  (
    ID_NURSE 
  )
);

INSERT into Nurse
values(1,'Mariya', 'Sharapova', 21, 'sugarpova','byumysweets', 1);

INSERT into Nurse
values(2,'Lyubov', 'Sharipova', 40, 'lushar','qqqq', 2);

CREATE TABLE DOCTOR 
(
  ID_DOCTOR INT NOT NULL 
, FIRST_NAME VARCHAR(20) NOT NULL 
, SECOND_NAME VARCHAR(20) NOT NULL
, AGE INT NOT NULL
, LOGIN VARCHAR(20) NOT NULL 
, PASSWORD VARCHAR(20) NOT NULL 
, PATIENT_ID INT NOT NULL 
, IS_HEAD_OF_DEPARTMENT BOOLEAN NOT NULL
, CONSTRAINT DOCTOR_PK PRIMARY KEY 
  (
    ID_DOCTOR 
  )
);

Insert into DOCTOR
values(1,'Boris', 'Levin', 22, 'borlev','12345', 1, false);
Insert into DOCTOR
values(2,'Semen', 'Lobanov', 31, 'semlob', '54321', 2, false);

CREATE TABLE DIAGNOSE 
(
  ID_DIAGNOSE INT NOT NULL 
, DOCTOR_ID INT NOT NULL 
, PATIENT_ID INT NOT NULL 
, NAME_OF_DIAGNOSE VARCHAR(20) NOT NULL 
, CONSTRAINT DIAGNOSE_PK PRIMARY KEY 
  (
    ID_DIAGNOSE 
  )
);

insert into diagnose
values(1,1,1,'ospa');

insert into diagnose
values(2,2,2,'vetryanka');

CREATE TABLE TREATMENT 
(
  ID_TREATMENT INT NOT NULL 
, ID_DOCTOR INT NOT NULL 
, ID_NURSE INT 
, ID_PATIENT INT NOT NULL
, T_PROCEDURE VARCHAR(20) NOT NULL 
, MEDICINE VARCHAR(20) NOT NULL 
, OPERATION VARCHAR(20) NOT NULL 
, CONSTRAINT TREATMENT_PK PRIMARY KEY 
  (
    ID_TREATMENT 
  )
);
insert into treatment
values(1,1,1,1,'Computer Games', 'DOTA', 'Destroy opponents');

insert into treatment
values(2,2,2,2,'Computer Games', 'CS', 'Go na midok');

CREATE TABLE APPOINTMENT 
(
  ID_APPOINTMENT INT NOT NULL 
, ID_DOCTOR INT NOT NULL 
, ID_PATIENT INT NOT NULL 
, ID_DIAGNOSE INT NOT NULL 
, APOINTMENT_DATE DATE NOT NULL 
, CONSTRAINT APPOINTMENT_PK PRIMARY KEY 
  (
    ID_APPOINTMENT 
  )
);

insert into appointment
values(1,1,1,1,'2019-12-11');
insert into appointment
values(2,2,2,2,'2020-01-13');

ALTER TABLE NURSE ADD CONSTRAINT fk_NURSE_PATIENT_ID FOREIGN KEY(PATIENT_ID)
REFERENCES PATIENT (ID_PATIENT);

ALTER TABLE DOCTOR ADD CONSTRAINT fk_DOCTOR_PATIENT_ID FOREIGN KEY(PATIENT_ID)
REFERENCES PATIENT (ID_PATIENT);

ALTER TABLE DIAGNOSE ADD CONSTRAINT fk_DIAGNOSE_DOCTOR_ID FOREIGN KEY(DOCTOR_ID)
REFERENCES DOCTOR (ID_DOCTOR);

ALTER TABLE DIAGNOSE ADD CONSTRAINT fk_Diagnose_PATIENT_ID FOREIGN KEY(PATIENT_ID)
REFERENCES PATIENT (ID_PATIENT);

ALTER TABLE TREATMENT ADD CONSTRAINT fk_Treatment_ID_DOCTOR FOREIGN KEY(ID_DOCTOR)
REFERENCES DOCTOR (ID_DOCTOR);

ALTER TABLE TREATMENT ADD CONSTRAINT fk_Treatment_ID_NURSE FOREIGN KEY(ID_NURSE)
REFERENCES NURSE (ID_NURSE);

ALTER TABLE TREATMENT ADD CONSTRAINT fk_Treatment_ID_Patient FOREIGN KEY(ID_PATIENT)
REFERENCES PATIENT (ID_PATIENT);

ALTER TABLE Appointment ADD CONSTRAINT fk_Appointment_ID_PATIENT FOREIGN KEY(ID_PATIENT)
REFERENCES Patient (ID_Patient);

ALTER TABLE Appointment ADD CONSTRAINT fk_Appointment_ID_Doctor FOREIGN KEY(ID_Doctor)
REFERENCES Doctor (ID_Doctor);

ALTER TABLE Appointment ADD CONSTRAINT fk_Appointment_ID_Diagnose FOREIGN KEY(ID_Diagnose)
REFERENCES Diagnose (ID_Diagnose);
