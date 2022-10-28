USE SuperheroSightings;

INSERT INTO Address(StreetNumber, StreetName, City, StateProvince, ZipPostalCode, Country) VALUES 
	('3968', 'Lynn St', 'Charlestown', 'MA', '02129', 'USA'),
    ('1702', 'Hilltop St', 'Springfield', 'MA', '01109', 'USA'),
    ('825', 'Rue St Charles', 'Longueuil', 'QC', 'J4H 1M3', 'Canada'),
    ('4000', 'Warner Blvd', 'Burbank', 'CA', '91522', 'USA'),
    ('225', 'Commissioners St', 'Toronto', 'ON', 'M4M0A1', 'Canada'),
    ('4001', 'Warming Ave', 'Burbank', 'CA', '91522', 'USA'),
    ('8050', 'Heritage Rd', 'Brampton', 'ON', 'L6YXXX', 'Canada'),
    ('4836', 'New Creek Rd', 'Huntsville', 'AL', '35802', 'USA');

INSERT INTO Location(`Name`, `Description`, Latitude, Longitude, AddressID) VALUES
	('Pinewood Studios', 'Movie Studio', 43.64886509571291, -79.34251122596578, 5),
    ('Longueuil', 'Quebec Apartments', 45.53134049323331, -73.51812601795072, 3),
    ("Somebody's house", "A stranger's house", 41.3500243296858, -71.72148063117592, 1),
    ("Homer's House", "The Simpson's Residence", 42.09642920415337, -72.48934198697623, 2),
    ('DC Studios', 'Event venue', 34.1487619939874, -118.33870238041126, 4);

INSERT INTO `Organization`(`Name`, `Description`, Phone, Email, AddressID) VALUES
	('DC Universe', 'DC universe organization', '18008008080', 'dcuniverse@dc.org', 6),
    ('Amazonians', 'The FC team', '180080809090', 'amazon@amazon.com', 7),
    ('No-Name Organization', 'Team Anonymous', '18889008080', 'noname@noname.org', 8);

INSERT INTO Superpower(`Name`, `Description`) VALUES 
	('Super speed', 'Superhuman levels of speed'),
    ('Super strength', 'Superhuman levels of strength'),
    ('Invincibility', "Ability to evade harm's way"),
    ('Stone Gaze', 'Immobilize people at will'),
    ('Invisibility', 'Turn invisible at will');

INSERT INTO HeroVillain(`Name`, IsHero, `Description`, SuperpowerID) VALUES 
	('Batkid', true, "Batman's son", 3),
    ('Superkid', true, "Superman's son", 2),
    ('Flash', true, 'Really fast superhero', 1),
    ('Medusa', false, 'Spiteful woman immobilizing people', 4),
    ('Hollow Man', false, 'Really evil man', 5);

INSERT INTO CharacterOrganization(HeroVillainID, OrganizationID) VALUES 
	(1, 1),
    (2, 1),
    (3, 1),
    (3, 3),
    (4, 2),
    (4, 3),
    (5, 2);

INSERT INTO Sighting(`Date`, LocationID, HeroVillainID) VALUES 
	('2022-10-07', 5, 1),
    ('2022-09-08', 2, 3),
    ('2022-10-10', 3, 5),
    ('2022-10-20', 4, 2),
    ('2022-09-27', 1, 4),
    ('2022-10-26', 5, 4),
    ('2022-09-29', 3, 4),
    ('2022-10-11', 2, 3),
    ('2022-07-07', 4, 5),
    ('2022-10-01', 3, 3),
    ('2022-09-11', 2, 5),
    ('2022-10-26', 1, 2),
    ('2022-10-13', 5, 5),
    ('2022-10-04', 3, 4),
    ('2022-10-12', 4, 4);