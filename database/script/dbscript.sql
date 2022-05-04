CREATE DATABASE oasip DEFAULT CHARACTER SET utf8;
USE oasip;
CREATE TABLE eventcategories (
 eventCategoryId INT NOT NULL AUTO_INCREMENT,
 eventCategoryName VARCHAR(100) NOT NULL,
 eventCategoryDescription VARCHAR(500) NULL,
 eventDuration INT NOT NULL,
 PRIMARY KEY (eventCategoryId),
 UNIQUE INDEX eventCategoryName_UNIQUE (eventCategoryName ASC) VISIBLE
);
CREATE TABLE events (
 eventId INT NOT NULL AUTO_INCREMENT,
 bookingName VARCHAR(100) NOT NULL,
 bookingEmail VARCHAR(100) NOT NULL,
 eventStartTime DATETIME NOT NULL,
 eventDuration INT NOT NULL,
 eventNotes VARCHAR(500) NULL,
 eventCategoryId INT NOT NULL,
 PRIMARY KEY (eventId),
 UNIQUE INDEX bookingEmail_UNIQUE (bookingEmail ASC) VISIBLE,
 INDEX fk_events_eventcategories_idx (eventCategoryId ASC) VISIBLE,
 CONSTRAINT fk_events_eventcategories
   FOREIGN KEY (eventCategoryId)
   REFERENCES eventcategories (eventCategoryId)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION)
);
INSERT INTO eventcategories (eventCategoryName,eventCategoryDescription,eventDuration)
VALUES ('Project Management Clinic', 'ตารางนัดหมายนี้ใช้สำหรับนัดหมาย Project Management Clinic ในวิชา INT221 integrated project I', 30);
INSERT INTO eventcategories (eventCategoryName,eventCategoryDescription,eventDuration)
VALUES ('DevOps/Infra Clinic', 'ตารางนัดหมายนี้ใช้สำหรับนัดหมาย DevOps/Infra Clinic ในวิชา INT221 integrated project I', 20);
INSERT INTO eventcategories (eventCategoryName,eventCategoryDescription,eventDuration)
VALUES ('Database Clinic', 'ตารางนัดหมายนี้ใช้สำหรับนัดหมาย Database Clinic ในวิชา INT221 integrated project I', 30);
INSERT INTO eventcategories (eventCategoryName,eventCategoryDescription,eventDuration)
VALUES ('Client-side Clinic', 'ตารางนัดหมายนี้ใช้สำหรับนัดหมาย  Client-side Clinic ในวิชา INT221 integrated project I', 60);
INSERT INTO eventcategories (eventCategoryName,eventCategoryDescription,eventDuration)
VALUES (' Server-side Clinic', 'ตารางนัดหมายนี้ใช้สำหรับนัดหมาย Server-side Clinic ในวิชา INT221 integrated project I', 45);
INSERT INTO events (bookingName,bookingEmail,eventStartTime,eventDuration,eventCategoryId)
values (' สมเกียรติ ขยันเรียน กลุ่ม TT-2','somkiat.k@gmail.com','2022-05-12 10:10:10',30,'1');
INSERT INTO events (bookingName,bookingEmail,eventStartTime,eventDuration,eventCategoryId)
values ('Harry Maguire กลุ่ม SSI-2','staymanu_forever@gmail.com','2022-04-10 12:30:00',45,'5');
INSERT INTO events (bookingName,bookingEmail,eventStartTime,eventDuration,eventNotes,eventCategoryId)
values (' Tawan Thanyaphon กลุ่ม SSA-5','tawan_than@gmail.com','2022-05-02 16:45:00',30,'ผมงงตรงที่อาจารย์พูดตอนต้นคาบครับ','1');
INSERT INTO events (bookingName,bookingEmail,eventStartTime,eventDuration,eventCategoryId)
values (' Todsawat Somtua กลุ่ม AT-3','todsawat.st@gmail.com','2022-04-30 09:20:00',20,'2');
INSERT INTO events (bookingName,bookingEmail,eventStartTime,eventDuration,eventCategoryId)
values (' Supaporn Parkwarn กลุ่ม PL-3','supapornpw@gmail.com','2022-05-18 11:00:00',60,'4');
INSERT INTO events (bookingName,bookingEmail,eventStartTime,eventDuration,eventCategoryId)
values (' Danny Welbeck กลุ่ม KW-2','godofthegod@gmail.com','2022-05-14 08:50:00',60,'4');
INSERT INTO events (bookingName,bookingEmail,eventStartTime,eventDuration,eventCategoryId)
values (' Pronpitcha Pimpa กลุ่ม OR-4','pronpitcha@gmail.com','2022-04-29 14:30:00',45,'5');
INSERT INTO events (bookingName,bookingEmail,eventStartTime,eventDuration,eventNotes,eventCategoryId)
values ('Wallapa Tosap กลุ่ม SY-1','wallapak@gmail.com','2022-05-06 13:00:00',30,'งงเรื่อง database ใน week นี้ค่ะ  ว่าทำยังไง','3');
INSERT INTO events (bookingName,bookingEmail,eventStartTime,eventDuration,eventCategoryId)
values ('Nattapon Chusriwan กลุ่ม US-5','nattapon_champ@gmail.com','2022-05-02 15:10:00',30,'2');
INSERT INTO events (bookingName,bookingEmail,eventStartTime,eventDuration,eventCategoryId)
values (' ไชยา ชูเจริญ กลุ่ม KP-5','chaitya_chu@gmail.com','2022-05-04 12:20:00',30,'1');
