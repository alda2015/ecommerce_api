drop table if exists Favoris;
drop table if exists SaveSearch;
drop table if exists Announcement;
drop table if exists User;

create table User(
	id int primary key AUTO_INCREMENT,
	email varchar(32) not null unique,
	mdp varchar(255),
	tel varchar(32),
	address varchar(255),
	firstname varchar(64),
	admin boolean,
	lastname varchar(64)
);

create table Announcement (
	id int primary key AUTO_INCREMENT,
	title varchar(128),
	datePost date,
	descr text,
	prix float,
	surface float,
	localisation varchar(32),
	photo varchar(255),
	user_id int,
	CONSTRAINT user_fk 
		FOREIGN KEY(user_id) REFERENCES User(id)				
);

create table Favoris (
	user_id int,
	announcement_id int,
	CONSTRAINT user_fav_fk 
		FOREIGN KEY(user_id) REFERENCES User(id),
	CONSTRAINT announcement_fav_fk 
		FOREIGN KEY(announcement_id) REFERENCES Announcement(id),	
	CONSTRAINT fav_pk primary key (user_id,announcement_id)
);
create table SaveSearch (
	id int primary key AUTO_INCREMENT,
	prixMin float,
	prixMax float,
	surfaceMin float,
	surfaceMax float,
	localisation varchar(32),
	user_id int,
	CONSTRAINT user_save_search_fk 
		FOREIGN KEY(user_id) REFERENCES User(id)	
);