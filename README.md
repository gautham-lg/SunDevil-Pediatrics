# SunDevil-Pediatrics

You can download the zip file and open this project in the Eclipse application.

In your mysql database create the following tables and insert the given below values for the doctorinfo and nurseinfo tables. Then run the application.

The usernames and passwords for doctor and nurse are given in the tables created in mysql server, so the patient registers and not the doctor and nurse.

The tables created are :

CREATE TABLE `doctorinfo` (
  `First_name` varchar(50) DEFAULT NULL,
  `Last_name` varchar(50) DEFAULT NULL,
  `DoctorID` int(11) NOT NULL,
  `Password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DoctorID`)
) ;



CREATE TABLE `nurseinfo` (
  `First_name` varchar(50) DEFAULT NULL,
  `Last_name` varchar(50) DEFAULT NULL,
  `NurseID` int(11) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL
) ;


CREATE TABLE `patient_info` (
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `cpassword` varchar(255) DEFAULT NULL,
  `insurance` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `number` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `pharmacy` varchar(255) DEFAULT NULL
) ;

CREATE TABLE `patient_medical_info` (
  `patientName` varchar(255) DEFAULT NULL,
  `weight` int(3) DEFAULT NULL,
  `height` int(3) DEFAULT NULL,
  `temp` float DEFAULT NULL,
  `bp` int(3) DEFAULT NULL,
  `allergy` varchar(255) DEFAULT NULL,
  `symptoms` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `prescription` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ;

CREATE TABLE `patient_message` (
  `message` varchar(255) DEFAULT NULL,
  `pname` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `rname` varchar(255) DEFAULT NULL
) ;

The values inserted in the doctorinfo table  are:


insert  into `doctorinfo`(`First_name`,`Last_name`,`DoctorID`,`Password`) values ('Andy','Moore',1001,'123');
insert  into `doctorinfo`(`First_name`,`Last_name`,`DoctorID`,`Password`) values ('Josh','Anderson',1010,'123');
insert  into `doctorinfo`(`First_name`,`Last_name`,`DoctorID`,`Password`) values ('Paul','Anderson',2010,'123');
insert  into `doctorinfo`(`First_name`,`Last_name`,`DoctorID`,`Password`) values ('John','Doe',3001,'123');
insert  into `doctorinfo`(`First_name`,`Last_name`,`DoctorID`,`Password`) values ('Eric','Lopez',4045,'123');

The values entered in the nurseinfo tabel are:

insert  into `nurseinfo`(`First_name`,`Last_name`,`NurseID`,`Password`) values ('Stephnie','Jones',4045,'123');
insert  into `nurseinfo`(`First_name`,`Last_name`,`NurseID`,`Password`) values ('Nancy','Brown',1001,'123');
insert  into `nurseinfo`(`First_name`,`Last_name`,`NurseID`,`Password`) values ('Christina','Martin',4045,'123');
insert  into `nurseinfo`(`First_name`,`Last_name`,`NurseID`,`Password`) values ('Jennifer','Lee',3001,'123');
insert  into `nurseinfo`(`First_name`,`Last_name`,`NurseID`,`Password`) values ('Nina','Ramirez',1009,'123');

Thank You
