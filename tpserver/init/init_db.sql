CREATE SCHEMA IF NOT EXISTS tp_schema;

CREATE TABLE IF NOT EXISTS tp_schema.tours (
                                id BIGSERIAL PRIMARY KEY,
                                name TEXT,
                                from_loc TEXT,
                                to_loc TEXT,
                                distance DOUBLE PRECISION,
                                estimated_time TEXT,
                                description TEXT,
                                image TEXT,
                                from_coords TEXT,
                                to_coords TEXT
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
('18/02/2023', 'Another log for the same tour', 'Medium', '0km', '01:30:00', 2, 1);
INSERT INTO tp_schema.logs
(date, comment, difficulty, distance, time, rating, tour_id)
VALUES
('01/05/2025', 'TEST', 'Hard', '0km', '01:30:00', 5, 1);



INSERT INTO tp_schema.tours
(name, from_loc, to_loc, distance, estimated_time, description, image, from_coords,to_coords)
VALUES
    ('FH Technikum Tour', 'Praterstern Wien', 'Höchstädtplatz Wien', 1, '234.0', 'From Praterstern to FH Technikum', 'fh.png','16.391564,48.217011','16.377229,48.239676');


CREATE USER tp_user WITH PASSWORD 'user_pass';

GRANT CONNECT ON DATABASE tp_db TO tp_user;
GRANT USAGE ON SCHEMA tp_schema TO tp_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA tp_schema TO tp_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA tp_schema GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO tp_user;