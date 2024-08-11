-- By default this files executed when application is started

CREATE TABLE IF NOT EXISTS example (
    id INT PRIMARY KEY,
    name VARCHAR(255)
);

----------- START Unidirectional OneToOne from Employee to Address -----------
-- Create a sequence for generating address IDs
CREATE SEQUENCE address_seq START WITH 206207 INCREMENT BY 1;

-- Create the address table
-- Create address table before employee table as employee table contain reference for this table
CREATE TABLE address (
    address_id BIGINT PRIMARY KEY,
    address_line VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    country VARCHAR(50),
    pincode INTEGER
);

-- Create a sequence for generating employee IDs
CREATE SEQUENCE employee_seq START WITH 20099449 INCREMENT BY 1;

-- Create the employee table
CREATE TABLE employee (
    employee_id BIGINT PRIMARY KEY,
    address_id BIGINT UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department VARCHAR(50),
    salary DECIMAL(15, 2),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
);

----------- END Unidirectional OneToOne from Employee to Address -----------


----------- START Bidirectional OneToMay from Order to Item -----------
-- Create a sequence for generating order IDs
CREATE SEQUENCE order_seq START WITH 10346 INCREMENT BY 1;

-- Create the order table
-- Order table is created before table item as it is referenced for foreign key
-- using order_table instead of order because order is a keyword and giving issues
CREATE TABLE Order_Table (
    order_id BIGINT PRIMARY KEY,
    mobile_number VARCHAR(20),
    customer_name VARCHAR(100) NOT NULL,
    order_time TIMESTAMP
);
-- handling timestamp: INSERT INTO order_table (order_id, mobile_number, customer_name, order_time) VALUES (2, 'd', 'Arpit Kumar', '2024-08-10 14:30:00.00245');
-- INSERT INTO order_table (order_id, mobile_number, customer_name, order_time) VALUES (2, 'd', 'Arpit Kumar', '2024-08-10 14:30:00');


-- Create a sequence for generating item IDs
CREATE SEQUENCE item_seq START WITH 456789 INCREMENT BY 1;

-- Create the item table
CREATE TABLE Item_Table (
    item_id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_name VARCHAR(50) NOT NULL,
    quantity Integer NOT NULL,
    price DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Order_Table(order_id)
);

----------- END Bidirectional OneToMany from Order to Item -----------

----------- START Idempotent key test for Payment Entity -------------
-- Create a sequence for generating payment IDs
CREATE SEQUENCE payment_seq START WITH 1001 INCREMENT BY 1;

-- Create the payment table
CREATE TABLE Payment (
    payment_id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    identifier VARCHAR(50) UNIQUE NOT NULL,
    time TIMESTAMP
);

----------- END Idempotent key test for Payment Entity -------------