CREATE TABLE IF NOT EXISTS Book (
    id SERIAL PRIMARY KEY,
    title varchar(255) NOT NULL,
    author TEXT,
    status VARCHAR(20) NOT NULL,
    genre VARCHAR(20) NOT NULL,
	date_started TIMESTAMP,
	date_read TIMESTAMP
);