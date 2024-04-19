BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	f_name varchar(50),
	l_name varchar(50),
	email_address varchar(100),
	address varchar(200),
	phone_number varchar(15),
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE MapBoxAPIToken
(
    map_box_token character varying(200)
);

CREATE TABLE landmark (
    landmark_id SERIAL,
    landmark_name varchar(100) UNIQUE NOT NULL,
    landmark_description varchar(500) NOT NULL,
    venue_type_id int,
    longitude varchar(30) NOT NULL,
    latitude varchar(30) NOT NULL,
    address varchar(200),
    landmark_like int default 0,
    landmark_dislike int default 0

);

CREATE TABLE day_time (
    day_time_id SERIAL,
    landmark_id int,
    day_name varchar(15),
    open_time varchar(20),
    close_time varchar(20)

);

CREATE TABLE venue_type (
    venue_type_id SERIAL,
    venue_type_name varchar(50)

);

CREATE TABLE image_landmark (
    image_id SERIAL,
    landmark_id int NOT NULL,
    image_url varchar(300)
);

CREATE TABLE itinerary_landmark (
    itinerary_id int NOT NULL,
    landmark_id int NOT NULL,
    sort_order int NOT NULL,
    deletedFlag boolean default false,
    deletedDate date

);

CREATE TABLE user_landmark (
    user_id int NOT NULL,
    landmark_id int NOT NULL,
    thumbs int
);

CREATE TABLE itinerary (
    itinerary_id SERIAL,
    user_id int NOT NULL,
    itinerary_name varchar(50) NOT NULL,
    itinerary_date date NOT NULL,
    start_point_id int,
    deletedFlag boolean default false,
    deletedDate date

);

CREATE TABLE start_point (
    start_point_id SERIAL,
    start_point_name varchar(50),
    latitude_sp varchar(30) NOT NULL,
    longitude_sp varchar(30) NOT NULL,
    itinerary_id int,
    deletedFlag boolean default false,
    deletedDate date

);

CREATE TABLE review_landmark (
    review_id int NOT NULL,
    landmark_id int NOT NULL
);

CREATE TABLE review (
    review_id SERIAL,
    review_title varchar (100) NOT NULL,
    review_description varchar(300) NOT NULL,
    review_date date,
    user_id int NOT NULL
);



COMMIT TRANSACTION;
