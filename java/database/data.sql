BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO public.mapboxapitoken(
	map_box_token)
	VALUES ('pk.eyJ1IjoiYW9ybGFuZG8iLCJhIjoiY2xwdHZiczZ0MGdzdjJpbWx6dzBpMWxtcCJ9.kCxPaCcdkYJ99kBUYLi0sw');

INSERT INTO public.venue_type(
	venue_type_name)
	VALUES ('Park');


INSERT INTO public.venue_type(
	venue_type_name)
	VALUES ('Museum');


INSERT INTO public.venue_type(
	venue_type_name)
	VALUES ('Restaurant');

INSERT INTO public.venue_type(
	venue_type_name)
	VALUES ('Entertainment');

INSERT INTO public.venue_type(
	venue_type_name)
	VALUES ('Community Center');

INSERT INTO public.venue_type(
	venue_type_name)
	VALUES ('Landmark');

INSERT INTO public.landmark(
	landmark_name, landmark_description, venue_type_id, longitude, latitude, address)
	VALUES ('Skydeck Chicago', 'Experience a unique aerial view of Chicago like you have never seen before!', 4,
			'-87.635490', '41.878910', '233 S Wacker Dr, Chicago, IL 60606');

INSERT INTO public.landmark(
	landmark_name, landmark_description, venue_type_id, longitude, latitude, address)
	VALUES ('Riverwalk', 'The Chicago Riverwalk is a multi-use public open space located on the south bank of the main branch of the Chicago River in Chicago,extending from Lake Michigan and Lake Shore Drive westward to Lake Street',
			1, '-87.625960', '41.888120', '875 N Michigan Ave 94th floor, Chicago, IL 60611');

INSERT INTO public.landmark(
	landmark_name, landmark_description, venue_type_id, longitude, latitude, address)
	VALUES ('The Art Institute of Chicago', 'Founded in 1879, this musuem hosts over 300,000 works of art that span over 5,000 years', 2, '-87.624039', '41.879539', '111 S Michigan Ave, Chicago, IL 60603');

INSERT INTO public.landmark(
	landmark_name, landmark_description, venue_type_id, longitude, latitude, address)
	VALUES ('360 Chicago Observation Deck ', 'Experience breathtaking panoramic views of Chicago skyline and Lake Michigan from 1,000 feet above the famed Magnificent Mile.', 4, '-87.624428', '41.882931', '875 N Michigan Ave 94th floor, Chicago, IL 60611');
INSERT INTO public.landmark(
	landmark_name, landmark_description, venue_type_id, longitude, latitude, address)
	VALUES ('Millennium Park', 'Millennium Park is a can not miss attraction for any Chicago visit.The lakefront campus, located in the heart of downtown, is home to the iconic Cloud Gate statue (aka “The Bean”), interactive fountains, tranquil gardens, free events, public art, skating rinks, rock climbing, and plenty of green space.', 1, '-87.622276', '41.884331', '201 E. Randolph St. Chicago, IL 60601');
INSERT INTO public.landmark(
	landmark_name, landmark_description, venue_type_id, longitude, latitude, address)
	VALUES ('Navy Pier', 'Navy Pier is where you go in Chicago for events, free public programs, attractions, culture,  dining, shopping and more. It’s where fun lives large, whether you’re a visitor or local, with family or friends', 1, '-87.609657', '41.891708', '600 E Grand Ave, Chicago, IL 60611');
INSERT INTO public.landmark(
	landmark_name, landmark_description, venue_type_id, longitude, latitude, address)
	VALUES ('Buckingham Fountain', 'A Chicago Landmark in the center of Grant Park, between Queens Landing and the end of Ida B. Wells Drive', 6, '-87.618622', '41.874840', '301 S Columbus Dr, Chicago, IL 60605');
INSERT INTO public.landmark(
	landmark_name, landmark_description, venue_type_id, longitude, latitude, address)
	VALUES ('Chicago Cultural Center', 'The historic Chicago Cultural Center isone of the citys top attractions and showcases art exhibits, free programs and events and amazing architecture.', 5, '-87.624977', '41.884350', '78 E Washington St, Chicago, IL 60602');
INSERT INTO public.landmark(
	landmark_name, landmark_description, venue_type_id, longitude, latitude, address)
	VALUES ('Rookery Building', 'The Rookery is a transitional structure in the history of American architecture, incorporating both masonry and metal construction methods', 6, '-87.631470', '41.879082', '209 S La Salle St, Chicago, IL 60604');
INSERT INTO public.landmark(
	landmark_name, landmark_description, venue_type_id, longitude, latitude, address)
	VALUES ('Doane Observatory/Adler Planetarium', 'Chicagos Adler Planetarium is Americas first planetarium and a premiere resource for inspiring the next generation of space explorers', 2, '-87.606682', '41.866371', '1300 S Lake Shore Dr, Chicago, IL 60605');


INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (1, 'Sunday', '09:00am', '10:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (1, 'Monday', '09:00am', '10:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (1, 'Tuesday', '09:00am', '10:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (1, 'Wednesday', '09:00am', '10:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (1, 'Thursday', '09:00am', '10:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (1, 'Friday', '09:00am', '10:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (1, 'Saturday', '09:00am', '10:00pm');

INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (2, 'Sunday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (2, 'Monday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (2, 'Tuesday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (2, 'Wednesday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (2, 'Thursday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (2, 'Friday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (2, 'Saturday', '06:00am', '11:00pm');

INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (3, 'Sunday', '11:00am', '05:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (3, 'Monday', '11:00am', '05:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (3, 'Thursday', '11:00am', '08:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (3, 'Friday', '11:00am', '05:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (3, 'Saturday', '11:00am', '05:00pm');

INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (4, 'Sunday', '09:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (4, 'Monday', '09:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (4, 'Tuesday', '09:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (4, 'Wednesday', '09:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (4, 'Thursday', '09:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (4, 'Friday', '09:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (4, 'Saturday', '09:00am', '11:00pm');

INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (5, 'Sunday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (5, 'Monday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (5, 'Tuesday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (5, 'Wednesday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (5, 'Thursday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (5, 'Friday', '06:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (5, 'Saturday', '06:00am', '11:00pm');

INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (6, 'Sunday', '11:00am', '09:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (6, 'Monday', '11:00am', '09:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (6, 'Tuesday', '11:00am', '09:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (6, 'Wednesday', '11:00am', '09:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (6, 'Thursday', '11:00am', '09:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (6, 'Friday', '11:00am', '10:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (6, 'Saturday', '11:00am', '10:00pm');

INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (7, 'Sunday', '08:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (7, 'Monday', '08:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (7, 'Tuesday', '08:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (7, 'Wednesday', '08:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (7, 'Thursday', '08:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (7, 'Friday', '08:00am', '11:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (7, 'Saturday', '08:00am', '11:00pm');

INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (8, 'Sunday', '10:00am', '05:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (8, 'Monday', '10:00am', '05:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (8, 'Tuesday', '10:00am', '05:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (8, 'Wednesday', '10:00am', '05:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (8, 'Thursday', '10:00am', '05:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (8, 'Friday', '10:00am', '05:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (8, 'Saturday', '10:00am', '05:00pm');

INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (9, 'Monday', '06:00am', '06:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (9, 'Tuesday', '06:00am', '06:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (9, 'Wednesday', '06:00am', '06:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (9, 'Thursday', '06:00am', '06:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (9, 'Friday', '06:00am', '06:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (9, 'Saturday', '08:00am', '02:00pm');

INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (10, 'Sunday', '09:00am', '04:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (10, 'Monday', '09:00am', '04:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (10, 'Wednesday', '04:00pm', '10:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (10, 'Friday', '09:00am', '04:00pm');
INSERT INTO public.day_time(
	landmark_id, day_name, open_time, close_time)
	VALUES (10, 'Saturday', '09:00am', '04:00pm');




INSERT INTO public.itinerary(
	user_id, itinerary_name, itinerary_date, start_point_id)
	VALUES (1, 'FirstTest', to_date('04-10-2024', 'MM-DD-YYYY'), 1);

INSERT INTO public.start_point(
	start_point_name, latitude_sp, longitude_sp, itinerary_id)
	VALUES ( 'test start point', '-87.606682', '41.866371', 1);

INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (1, '../assets/imgs/skydeck/skydeck1.jpg');

INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (1, '../assets/imgs/skydeck/skydeck2.jpg');

INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (1, '../assets/imgs/skydeck/skydeck3.jpg');

INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (1, '../assets/imgs/skydeck/skydeck4.jpg');

INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (7, '../assets/imgs/bunkinghamfountain/BuckinghamFountain-1.jpg');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (7, '../assets/imgs/bunkinghamfountain/BuckinghamFountain-2.jpg');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (7, '../assets/imgs/bunkinghamfountain/BuckinghamFountain-3.png');

INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (8, '../assets/imgs/culturecenter/ChicagoCulturalCenter1.jpg');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (8, '../assets/imgs/culturecenter/ChicagoCulturalCenter2.png');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (8, '../assets/imgs/culturecenter/ChicagoCulturalCenter3.png');

INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (5, '../assets/imgs/milleniumpark/MilleniumPark1.png');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (5, '../assets/imgs/milleniumpark/MilleniumPark2.png');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (5, '../assets/imgs/milleniumpark/MilleniumPark3.png');

INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (6, '../assets/imgs/navypier/NavyPier1.png');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (6, '../assets/imgs/navypier/NavyPier2.png');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (6, '../assets/imgs/navypier/NavyPier3.png');

INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (2, '../assets/imgs/riverwalk/riverwalk1.jpg');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (2, '../assets/imgs/riverwalk/riverwalk2.jpg');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (2, '../assets/imgs/riverwalk/riverwalk3.jpg');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (2, '../assets/imgs/riverwalk/riverwalk4.jpg');

INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (9, '../assets/imgs/rookerybuilding/RookeryBuilding1.jpg');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (9, '../assets/imgs/rookerybuilding/RookeryBuilding2.jpg');
INSERT INTO public.image_landmark(
	landmark_id, image_url)
VALUES (9, '../assets/imgs/rookerybuilding/RookeryBuilding3.jpg');

INSERT INTO public.itinerary_landmark(
	itinerary_id, landmark_id, sort_order)
	VALUES (1, 1, 1);
INSERT INTO public.itinerary_landmark(
	itinerary_id, landmark_id, sort_order)
	VALUES (1, 2, 2);
INSERT INTO public.itinerary_landmark(
	itinerary_id, landmark_id, sort_order)
	VALUES (1, 3, 3);

COMMIT TRANSACTION;
