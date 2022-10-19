DROP DATABASE IF EXISTS SuperheroSightings;
CREATE DATABASE SuperheroSightings;
USE SuperheroSightings;

CREATE TABLE Superpower (
    SuperpowerID INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(20) NOT NULL,
    `Description` VARCHAR(100)
);

CREATE TABLE HeroVillain (
    HeroVillainID INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,
    IsHero BOOL NOT NULL,
    `Description` VARCHAR(100),
    SuperpowerID INT,
    Image MEDIUMBLOB,
    CONSTRAINT fk_HeroVillain_Superpower FOREIGN KEY (SuperpowerID)
        REFERENCES Superpower (SuperpowerID)
);

CREATE TABLE Address (
    AddressID INT PRIMARY KEY AUTO_INCREMENT,
    StreetNumber VARCHAR(10) NOT NULL,
    StreetName VARCHAR(20) NOT NULL,
    City VARCHAR(30) NOT NULL,
    StateProvince CHAR(20) NOT NULL,
    ZipPostalCode CHAR(12) NOT NULL,
    Country VARCHAR(20) NOT NULL
);

CREATE TABLE `Organization` (
    OrganizationID INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,
    `Description` VARCHAR(100),
    Phone VARCHAR(15) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    AddressID INT,
    CONSTRAINT fk_Organization_Address FOREIGN KEY (AddressID)
        REFERENCES Address (AddressID)
);

CREATE TABLE CharacterOrganization (
    HeroVillainID INT NOT NULL,
    OrganizationID INT NOT NULL,
    PRIMARY KEY (HeroVillainID , OrganizationID),
    CONSTRAINT fk_HeroVillain_CharacterOrganization FOREIGN KEY (HeroVillainID)
        REFERENCES HeroVillain (HeroVillainID),
    CONSTRAINT fk_Organization_CharacterOrganization FOREIGN KEY (OrganizationID)
        REFERENCES `Organization` (OrganizationID)
);

CREATE TABLE Location (
    LocationID INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,
    `Description` VARCHAR(100) NOT NULL,
    Latitude DECIMAL(16 , 14 ) NOT NULL,
    Longitude DECIMAL(17 , 14 ) NOT NULL,
    AddressID INT,
    Image MEDIUMBLOB,
    CONSTRAINT fk_Location_Address FOREIGN KEY (AddressID)
        REFERENCES Address(AddressID)
);

CREATE TABLE Sighting (
    SightingID INT PRIMARY KEY AUTO_INCREMENT,
    `Date` DATE NOT NULL,
    LocationID INT NOT NULL,
    HeroVillainID INT NOT NULL,
    CONSTRAINT fk_Sighting_Location FOREIGN KEY (LocationID)
        REFERENCES Location (LocationID),
    CONSTRAINT fk_Sighting_HeroVillain FOREIGN KEY (HeroVillainID)
        REFERENCES HeroVillain (HeroVillainID)
);
