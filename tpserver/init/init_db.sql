CREATE SCHEMA IF NOT EXISTS tp_schema;

CREATE TABLE IF NOT EXISTS tp_schema.tours (
                                 id BIGSERIAL PRIMARY KEY,
                                 name TEXT,
                                 from_loc TEXT,
                                 to_loc TEXT,
                                 distance DOUBLE PRECISION,
                                 estimated_time TEXT,
                                 description TEXT,
                                 image TEXT
);

CREATE TABLE IF NOT EXISTS tp_schema.logs (
                                               id BIGSERIAL PRIMARY KEY,
                                               date TEXT,
                                               comment TEXT,
                                               difficulty TEXT,
                                               distance TEXT,
                                               time TEXT,
                                               rating INT,
                                               tour_id BIGSERIAL
);

INSERT INTO tp_schema.logs
(date, comment, difficulty, distance, time, rating, tour_id)
VALUES
('21/01/2022', 'Hello', 'Easy', '5km', '01:30:00', 4, 1);
INSERT INTO tp_schema.logs
(date, comment, difficulty, distance, time, rating, tour_id)
VALUES
('18/02/2023', 'Another log for the same tour', 'Medium', '0km', '01:30:00', 2, 1);
INSERT INTO tp_schema.logs
(date, comment, difficulty, distance, time, rating, tour_id)
VALUES
('01/05/2025', 'TEST', 'Hard', '0km', '01:30:00', 5, 2);



INSERT INTO tp_schema.tours
(name, from_loc, to_loc, distance, estimated_time, description, image)
VALUES
    ('Dummy Tour', 'Vienna', 'Graz', 200.5, '2h', 'A scenic test route.', 'dummy.png');
INSERT INTO tp_schema.tours
(name, from_loc, to_loc, distance, estimated_time, description, image)
VALUES
    ('Escape from Berlin', 'Berlin', 'Anywhere', 9999, '1 Week', 'Get Out of Berlin', 'berlin.png');
INSERT INTO tp_schema.tours
(name, from_loc, to_loc, distance, estimated_time, description, image)
VALUES
    ('FH Technikum Tour', 'FH Technikum', 'FH Technikum', 1, '1h', 'A tour through our beloved FH.', 'fh.png');


CREATE USER tp_user WITH PASSWORD 'user_pass';

GRANT CONNECT ON DATABASE tp_db TO tp_user;
GRANT USAGE ON SCHEMA tp_schema TO tp_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA tp_schema TO tp_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA tp_schema GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO tp_user;