CREATE TABLE IF NOT EXISTS room (
    roomNumber BIGINT PRIMARY KEY,
    type VARCHAR(255),
    price INT,
    avalaible BOOLEAN
);

CREATE TABLE IF NOT EXISTS client (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    age INT
);

CREATE TABLE IF NOT EXISTS reservation (
    id INT PRIMARY KEY,
    idClient INT,
    roomNumber INT,
    checkIn DATE,
    checkOut DATE
);