CREATE TABLE Konto (
    kontoid int NOT NULL AUTO_INCREMENT,
    personid int ,
    kontonr varchar(255) ,
    guthaben double,

    PRIMARY KEY (kontoid)
);