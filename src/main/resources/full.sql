BEGIN;

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id serial PRIMARY KEY, name VARCHAR(255), version int, isDeleted boolean);
INSERT INTO customers (name, version, isDeleted) VALUES
('Vasiliy', 1 ,false),
('Tommy', 1 ,false),
('Bill', 1 ,false);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id serial PRIMARY KEY, title VARCHAR(255), price int, version int, isDeleted boolean);
INSERT INTO products (title,price,version,isDeleted) VALUES
('Shoes', 3100, 1 ,false),
('T-shirt', 1100, 1 ,false),
('Coat', 5000, 1 ,false),
('Hat', 1200, 1 ,false),
('Socks', 500, 1 ,false),
('Trousers', 1000, 1 ,false);

COMMIT;