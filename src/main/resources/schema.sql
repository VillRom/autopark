CREATE TABLE IF NOT EXISTS dealers
(
    id BIGINT NOT NULL,
    title VARCHAR(100),
    email VARCHAR(50),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    constraint pk_dealer PRIMARY KEY (id),
    constraint uq_title UNIQUE (title),
    constraint uq_mail UNIQUE (email)
);
CREATE TABLE IF NOT EXISTS owners
(
    id BIGINT NOT NULL,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    middle_name VARCHAR(40),
    phone VARCHAR(10),
    email VARCHAR(50),
    dealer_id BIGINT REFERENCES dealers (id),
    constraint pk_owner PRIMARY KEY (id),
    constraint uq_phone UNIQUE (phone),
    constraint uq_email UNIQUE (email)
);
CREATE TABLE IF NOT EXISTS cars
(
    id BIGINT NOT NULL,
    unique_number BIGINT NOT NULL,
    build_date date,
    owner_id BIGINT REFERENCES owners (id),
    constraint pk_car PRIMARY KEY (id),
    constraint uq_number UNIQUE (unique_number)
);
CREATE TABLE IF NOT EXISTS owners_car
(
    owner_id BIGINT REFERENCES owners (id),
    car_id BIGINT REFERENCES cars (id)
);
CREATE TABLE IF NOT EXISTS dealers_owner
(
    dealer_id BIGINT REFERENCES dealers (id),
    owner_id BIGINT REFERENCES owners (id)
);