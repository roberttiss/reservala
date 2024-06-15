CREATE TABLE IF NOT EXISTS room (
    roomNumber BIGINT PRIMARY KEY,
    type VARCHAR(255),
    price INT,
    available BOOLEAN
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

INSERT INTO client (id, name, age) VALUES
(1, 'Client 1', 30),
(2, 'Client 2', 35),
(3, 'Client 3', 40),
(4, 'Client 4', 45),
(5, 'Client 5', 50),
(6, 'Client 6', 55),
(7, 'Client 7', 60),
(8, 'Client 8', 65),
(9, 'Client 9', 70),
(10, 'Client 10', 75);

INSERT INTO room (roomNumber, type, price, available) VALUES
(101, 'Single', 100, true),
(102, 'Double', 200, true),
(103, 'Suite', 300, true),
(104, 'Single', 100, true),
(105, 'Double', 200, true),
(106, 'Suite', 300, true),
(107, 'Single', 100, true),
(108, 'Double', 200, true),
(109, 'Suite', 300, true),
(110, 'Single', 100, true);

INSERT INTO reservation (id, idClient, roomNumber, checkIn, checkOut) VALUES
(1, 1, 101, '2024-06-14', '2024-06-23'),
(2, 2, 102, '2024-06-24', '2024-07-03'),
(3, 3, 103, '2024-07-04', '2024-07-13'),
(4, 4, 104, '2024-07-14', '2024-07-23'),
(5, 5, 105, '2024-07-24', '2024-08-02'),
(6, 6, 106, '2024-08-03', '2024-08-12'),
(7, 7, 107, '2024-08-13', '2024-08-22'),
(8, 8, 108, '2024-08-23', '2024-09-01'),
(9, 9, 109, '2024-09-02', '2024-09-11'),
(10, 10, 110, '2024-09-12', '2024-09-21'),
(11, 10, 101, '2024-09-22', '2024-10-01');