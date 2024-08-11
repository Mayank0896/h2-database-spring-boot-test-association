
# Spring Boot Project Documentation

## Overview

This Spring Boot project uses an in-memory H2 database and demonstrates various database operations and entity relationships. Below is a detailed overview of the project's features, database configurations, and testing resources.

## Project Features

1. **Database Configuration**
    - **Database:** H2 (In-memory)
    - **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

2. **Entity Relationships**
    - **Unidirectional OneToOne Association:** `Employee` class to `Address` class.
    - **Bidirectional OneToMany Association:** `Order` class to `Item` class.

3. **Custom Exception Handlers**
    - Custom exception handling for the above-mentioned associations.

4. **Idempotent Payment Handling**
    - `Payment` class implemented to create idempotent POST requests using UUID as an identifier.
    - The `Idempotent-Key` header is used to ensure unique and repeatable POST requests.

5. **Testing**
    - `PaymentServiceTest` class is created to test methods in the `PaymentService` class.

## Database Queries

**General Queries:**
```sql
select * from address;
select * from employee;
select * from order_table;
select * from item_table;
select * from address order by address_id;
select * from employee order by employee_id;
select * from payment;
select count(*) from address;
select count(*) from employee;
select count(*) from order_table;
select count(*) from item_table;
select count(em.employee_id) from employee em join address ad on ad.address_id = em.address_id;
select count(distinct(it.order_id)) from order_table ot join item_table it on it.order_id = ot.order_id;
select * from employee where employee_id = '20099449';
select * from order_table where order_id = '10346';
select * from item_table where item_id = '';
select * from item_table where order_id = '10346';
select order_id, item_id, product_name from item_table group by order_id, item_id, product_name;
SELECT * FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_NAME = 'address_seq';
SELECT * FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_NAME = 'employee_seq';
INSERT INTO order_table (order_id, mobile_number, customer_name, order_time) VALUES (2, 'd', 'Arpit Kumar', '2024-08-10 14:30:00');
```

## Project Files

### `src/main/resources/api-test/`
1. **EmployeeAddressRunner.csv**
    - Contains 1000 records for uploading to the `address` and `employee` tables using Postman runner.

2. **postman.json**
    - Contains all API requests for testing with Postman.

3. **OrderItemRunner.csv**
    - Contains 1000 records for uploading to the `order_table` and `item_table` for testing.

### `src/main/resources/`
- **schema.sql**
    - Creates all entity tables and sequences for primary keys.

- **data.sql**
    - Contains sample data for uploading into the tables.

## How to Run

1. **Build and Run the Application:**
    - Use `mvn spring-boot:run` or `./mvnw spring-boot:run` to start the application.

2. **Access H2 Console:**
    - Open the H2 console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console) and connect to the database using the URL `jdbc:h2:mem:testdb`.

3. **Use Postman for API Testing:**
    - Import `postman.json` into Postman and execute the requests to test the API endpoints.

## Contributing

Feel free to fork the repository, make changes, and submit pull requests. Ensure that all tests pass before submitting your contributions.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

For any questions or issues, please reach out to the project maintainers.
```

This `README.md` covers the essential aspects of your project, including configurations, file details, and how to run and test the application. You can adjust it based on any additional specifics or requirements for your project.