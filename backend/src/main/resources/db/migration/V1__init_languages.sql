CREATE TABLE IF NOT EXISTS languages (
    id bigserial primary key ,
    created_at timestamp,
    update_at timestamp,
    deleted_at timestamp,
    name varchar(255)
);

INSERT INTO languages(created_at, name) values (current_timestamp, 'English');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Mandarin');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Hindi');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Spanish');
INSERT INTO languages(created_at, name) values (current_timestamp, 'French');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Arabic');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Bengali');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Russian');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Portugese');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Urdu');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Indonesian');
INSERT INTO languages(created_at, name) values (current_timestamp, 'German');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Japanese');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Italian');
INSERT INTO languages(created_at, name) values (current_timestamp, 'French');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Ukranian');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Turkish');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Tamil');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Cantonese');
INSERT INTO languages(created_at, name) values (current_timestamp, 'Vietnamese');
