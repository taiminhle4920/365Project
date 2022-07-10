select * from student;
select * from professor;
select * from course;
select * from grade;

drop table grade;
drop table course;
drop table professor;
drop table student;
drop table major;

create table major(
    majorName varchar(255) primary key
);

create table student (
    sid int auto_increment,
    firstName varchar(255),
    lastName varchar(255),
    email varchar(255),
    dob date,
    major varchar(255),
    primary key (sid),
    foreign key (major) references major(majorName) on delete cascade on update no action
);

create table professor (
    pid int auto_increment,
    firstName varchar(255),
    lastName varchar(255),
    email varchar(255),
    dob date,
    major varchar(255),
    foreign key (major) references major(majorName) on delete cascade on update no action,
    primary key (pid)
);

create table course (
    cid int auto_increment,
    courseLabel varchar(255),
    courseName varchar(255),
    quarter enum("Fall", "Winter", "Spring", "Summer"),
    schoolYear int check(schoolyear BETWEEN 1000 AND 2030),
    pid int,
    primary key (cid),
    foreign key (pid) references professor(pid) on delete cascade on update no action
);

create table grade (
    sid int,
    cid int,
    grade enum("A", "B", "C", "D", "F"),
    primary key (sid, cid),
    foreign key (sid) references student(sid) on delete cascade on update no action,
    foreign key (cid) references course(cid) on delete cascade on update no action
);

insert into major (majorName) values ("Computer Science");
insert into major (majorName) values ("Software Engineer");
insert into major (majorName) values ("Hardware Engineer");
insert into major (majorName) values ("Information Technology");
insert into major (majorName) values ("Information Systems Security");
insert into major (majorName) values ("Computer Engineering");

insert into student (firstName, lastName, email, dob, major) values ("firstName1", "lastName1", "email1@gmail.com", "2000-01-01", "Information Technology");
insert into student (firstName, lastName, email, dob, major) values ("firstName2", "lastName2", "email2@gmail.com", "2000-01-02", "Hardware Engineer");
insert into student (firstName, lastName, email, dob, major) values ("firstName3", "lastName3", "email3@gmail.com", "2000-01-03", "Information Systems Security");
insert into student (firstName, lastName, email, dob, major) values ("firstName4", "lastName4", "email4@gmail.com", "2000-01-04", "Software Engineer");
insert into student (firstName, lastName, email, dob, major) values ("firstName5", "lastName5", "email5@gmail.com", "2000-01-05", "Computer Engineering");
insert into student (firstName, lastName, email, dob, major) values ("firstName6", "lastName6", "email6@gmail.com", "2000-01-06", "Computer Science");
insert into student (firstName, lastName, email, dob, major) values ("firstName7", "lastName7", "email7@gmail.com", "2000-01-07", "Software Engineer");
insert into student (firstName, lastName, email, dob, major) values ("firstName8", "lastName8", "email8@gmail.com", "2000-01-08", "Computer Science");
insert into student (firstName, lastName, email, dob, major) values ("firstName9", "lastName9", "email9@gmail.com", "2000-01-09", "Computer Science");
insert into student (firstName, lastName, email, dob, major) values ("firstName10", "lastName10", "email10@gmail.com", "2000-01-10", "Hardware Engineer");
insert into student (firstName, lastName, email, dob, major) values ("firstName11", "lastName11", "email11@gmail.com", "2000-01-11", "Computer Science");
insert into student (firstName, lastName, email, dob, major) values ("firstName12", "lastName12", "email12@gmail.com", "2000-01-12", "Computer Engineering");
insert into student (firstName, lastName, email, dob, major) values ("firstName13", "lastName13", "email13@gmail.com", "2000-01-13", "Information Systems Security");
insert into student (firstName, lastName, email, dob, major) values ("firstName14", "lastName14", "email14@gmail.com", "2000-01-14", "Hardware Engineer");
insert into student (firstName, lastName, email, dob, major) values ("firstName15", "lastName15", "email15@gmail.com", "2000-01-15", "Information Technology");
insert into student (firstName, lastName, email, dob, major) values ("firstName16", "lastName16", "email16@gmail.com", "2000-01-16", "Computer Science");
insert into student (firstName, lastName, email, dob, major) values ("firstName17", "lastName17", "email17@gmail.com", "2000-01-17", "Software Engineer");
insert into student (firstName, lastName, email, dob, major) values ("firstName18", "lastName18", "email18@gmail.com", "2000-01-18", "Information Systems Security");
insert into student (firstName, lastName, email, dob, major) values ("firstName19", "lastName19", "email19@gmail.com", "2000-01-19", "Hardware Engineer");
insert into student (firstName, lastName, email, dob, major) values ("firstName20", "lastName20", "email20@gmail.com", "2000-01-20", "Computer Science");
insert into student (firstName, lastName, email, dob, major) values ("firstName21", "lastName21", "email21@gmail.com", "2000-01-21", "Information Technology");
insert into student (firstName, lastName, email, dob, major) values ("firstName22", "lastName22", "email22@gmail.com", "2000-01-22", "Software Engineer");
insert into student (firstName, lastName, email, dob, major) values ("firstName23", "lastName23", "email23@gmail.com", "2000-01-23", "Hardware Engineer");
insert into student (firstName, lastName, email, dob, major) values ("firstName24", "lastName24", "email24@gmail.com", "2000-01-24", "Software Engineer");
insert into student (firstName, lastName, email, dob, major) values ("firstName25", "lastName25", "email25@gmail.com", "2000-01-25", "Computer Engineering");

insert into professor (firstName, lastName, email, dob, major) values ("profirstName1", "prolastName1", "proemail1@gmail.com", "2000-02-02", "Information Technology");
insert into professor (firstName, lastName, email, dob, major) values ("profirstName2", "prolastName2", "proemail2@gmail.com", "2000-03-03", "Computer Science");
insert into professor (firstName, lastName, email, dob, major) values ("profirstName3", "prolastName3", "proemail3@gmail.com", "2000-04-04", "Software Engineer");
insert into professor (firstName, lastName, email, dob, major) values ("profirstName4", "prolastName4", "proemail4@gmail.com", "2000-05-05", "Hardware Engineer");
insert into professor (firstName, lastName, email, dob, major) values ("profirstName5", "prolastName5", "proemail5@gmail.com", "2000-06-06", "Information Systems Security");
insert into professor (firstName, lastName, email, dob, major) values ("profirstName6", "prolastName6", "proemail6@gmail.com", "2000-07-07", "Computer Science");

insert into course (courseLabel, courseName, quarter, schoolYear, pid) values ("CSC 101", "Intro to Coding", 1, 2022, 1);
insert into course (courseLabel, courseName, quarter, schoolYear, pid) values ("CSC 202", "Data Structures", 2, 2022, 2);
insert into course (courseLabel, courseName, quarter, schoolYear, pid) values ("CSC 203", "Object Oriented Programming", 3, 2022, 3);
insert into course (courseLabel, courseName, quarter, schoolYear, pid) values ("CSC 357", "System Programming", 3, 2022, 4);

insert into grade (sid, cid, grade) values (1, 1, 1);
insert into grade (sid, cid, grade) values (2, 1, 1);
insert into grade (sid, cid, grade) values (3, 1, 1);
insert into grade (sid, cid, grade) values (4, 1, 1);
insert into grade (sid, cid, grade) values (5, 1, 1);
insert into grade (sid, cid, grade) values (6, 1, 1);
insert into grade (sid, cid, grade) values (7, 1, 1);
insert into grade (sid, cid, grade) values (8, 1, 1);
insert into grade (sid, cid, grade) values (9, 1, 1);
insert into grade (sid, cid, grade) values (10, 1, 1);
insert into grade (sid, cid, grade) values (11, 1, 1);
insert into grade (sid, cid, grade) values (12, 1, 2);
insert into grade (sid, cid, grade) values (13, 1, 2);
insert into grade (sid, cid, grade) values (14, 1, 2);
insert into grade (sid, cid, grade) values (15, 1, 2);
insert into grade (sid, cid, grade) values (16, 1, 2);
insert into grade (sid, cid, grade) values (17, 1, 2);
insert into grade (sid, cid, grade) values (18, 1, 2);
insert into grade (sid, cid, grade) values (19, 1, 2);
insert into grade (sid, cid, grade) values (20, 1, 2);
insert into grade (sid, cid, grade) values (21, 1, 2);
insert into grade (sid, cid, grade) values (22, 1, 2);
insert into grade (sid, cid, grade) values (23, 1, 2);
insert into grade (sid, cid, grade) values (24, 1, 2);
insert into grade (sid, cid, grade) values (25, 1, 2);
insert into grade (sid, cid, grade) values (1, 2, 2);
insert into grade (sid, cid, grade) values (2, 2, 2);
insert into grade (sid, cid, grade) values (3, 2, 2);
insert into grade (sid, cid, grade) values (4, 2, 2);
insert into grade (sid, cid, grade) values (5, 2, 2);
insert into grade (sid, cid, grade) values (6, 2, 2);
insert into grade (sid, cid, grade) values (7, 2, 2);
insert into grade (sid, cid, grade) values (8, 2, 2);
insert into grade (sid, cid, grade) values (9, 2, 2);
insert into grade (sid, cid, grade) values (10, 2, 2);
insert into grade (sid, cid, grade) values (11, 2, 2);
insert into grade (sid, cid, grade) values (12, 2, 2);
insert into grade (sid, cid, grade) values (13, 2, 2);
insert into grade (sid, cid, grade) values (14, 2, 2);
insert into grade (sid, cid, grade) values (15, 2, 2);
insert into grade (sid, cid, grade) values (16, 2, 2);
insert into grade (sid, cid, grade) values (17, 2, 2);
insert into grade (sid, cid, grade) values (18, 2, 2);
insert into grade (sid, cid, grade) values (19, 2, 2);
insert into grade (sid, cid, grade) values (20, 2, 2);
insert into grade (sid, cid, grade) values (21, 2, 2);
insert into grade (sid, cid, grade) values (22, 2, 2);
insert into grade (sid, cid, grade) values (23, 2, 2);
insert into grade (sid, cid, grade) values (24, 2, 2);
insert into grade (sid, cid, grade) values (25, 2, 2);
insert into grade (sid, cid, grade) values (1, 3, 2);
insert into grade (sid, cid, grade) values (2, 3, 2);
insert into grade (sid, cid, grade) values (3, 3, 2);
insert into grade (sid, cid, grade) values (4, 3, 2);
insert into grade (sid, cid, grade) values (5, 3, 2);
insert into grade (sid, cid, grade) values (6, 3, 2);
insert into grade (sid, cid, grade) values (7, 3, 2);
insert into grade (sid, cid, grade) values (8, 3, 2);
insert into grade (sid, cid, grade) values (9, 3, 2);
insert into grade (sid, cid, grade) values (10, 3, 2);
insert into grade (sid, cid, grade) values (11, 3, 2);
insert into grade (sid, cid, grade) values (12, 3, 2);
insert into grade (sid, cid, grade) values (13, 3, 3);
insert into grade (sid, cid, grade) values (14, 3, 3);
insert into grade (sid, cid, grade) values (15, 3, 3);
insert into grade (sid, cid, grade) values (16, 3, 3);
insert into grade (sid, cid, grade) values (17, 3, 3);
insert into grade (sid, cid, grade) values (18, 3, 3);
insert into grade (sid, cid, grade) values (19, 3, 3);
insert into grade (sid, cid, grade) values (20, 3, 3);
insert into grade (sid, cid, grade) values (21, 3, 3);
insert into grade (sid, cid, grade) values (22, 3, 3);
insert into grade (sid, cid, grade) values (23, 3, 3);
insert into grade (sid, cid, grade) values (24, 3, 3);
insert into grade (sid, cid, grade) values (25, 3, 3);
insert into grade (sid, cid, grade) values (1, 4, 3);
insert into grade (sid, cid, grade) values (2, 4, 3);
insert into grade (sid, cid, grade) values (3, 4, 3);
insert into grade (sid, cid, grade) values (4, 4, 3);
insert into grade (sid, cid, grade) values (5, 4, 3);
insert into grade (sid, cid, grade) values (6, 4, 3);
insert into grade (sid, cid, grade) values (7, 4, 3);
insert into grade (sid, cid, grade) values (8, 4, 3);
insert into grade (sid, cid, grade) values (9, 4, 3);
insert into grade (sid, cid, grade) values (10, 4, 3);
insert into grade (sid, cid, grade) values (11, 4, 3);
insert into grade (sid, cid, grade) values (12, 4, 3);
insert into grade (sid, cid, grade) values (13, 4, 3);
insert into grade (sid, cid, grade) values (14, 4, 3);
insert into grade (sid, cid, grade) values (15, 4, 3);
insert into grade (sid, cid, grade) values (16, 4, 3);
insert into grade (sid, cid, grade) values (17, 4, 3);
insert into grade (sid, cid, grade) values (18, 4, 3);
insert into grade (sid, cid, grade) values (19, 4, 4);
insert into grade (sid, cid, grade) values (20, 4, 4);
insert into grade (sid, cid, grade) values (21, 4, 4);
insert into grade (sid, cid, grade) values (22, 4, 4);
insert into grade (sid, cid, grade) values (23, 4, 5);
insert into grade (sid, cid, grade) values (24, 4, 5);
insert into grade (sid, cid, grade) values (25, 4, 5);


select distinct
    grade.cid,
    course.pid,
    course.courseLabel,
    course.courseName,
    course.quarter,
    course.schoolYear
from
    grade,
    course
where
    grade.cid = 1;