DROP TABLE IF EXISTS cast;
DROP TABLE IF EXISTS actor;
DROP TABLE IF EXISTS movie;

CREATE TABLE movie
(
    movie_id     INT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(250) NOT NULL,
    release_date DATE         NOT NULL,
    synopsis     VARCHAR(2000) DEFAULT NULL
);

CREATE TABLE actor
(
    actor_id      INT AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(50) NOT NULL,
    last_name     VARCHAR(50) NOT NULL,
    date_of_birth DATE DEFAULT NULL
);

CREATE TABLE cast
(
    cast_id  INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    actor_id INT NOT NULL,
    role     VARCHAR(100) DEFAULT NULL
);

INSERT INTO movie(title, release_date, synopsis)
values ('Interstellar', '2014-11-04',
        'A team of explorers travel through a wormhole in space in an attempt to ensure humanity''s survival');

INSERT INTO actor(first_name, last_name, date_of_birth)
values ('Matthew', 'McConaughey', '1969-11-04');
INSERT INTO actor(first_name, last_name, date_of_birth)
values ('Anne', 'Hathaway', '1982-11-12');
INSERT INTO actor(first_name, last_name, date_of_birth)
values ('Jessica', 'Chastain', '1977-03-24');

INSERT INTO cast(movie_id, actor_id, role)
VALUES (1, 1, 'Cooper');
INSERT INTO cast(movie_id, actor_id, role)
VALUES (1, 2, 'Brand');
INSERT INTO cast(movie_id, actor_id, role)
VALUES (1, 3, 'Murph');

commit;