DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS loans;




CREATE TABLE users(
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name varchar(255),
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE books(
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title DOUBLE NOT NULL,
    FOREIGN KEY (from_account_id) REFERENCES accounts(id),
    FOREIGN KEY (to_account_id) REFERENCES accounts(id)
);

CREATE TABLE loans(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    from_account_id BIGINT,
    to_account_id BIGINT,
    amount DOUBLE NOT NULL,
    FOREIGN KEY (from_account_id) REFERENCES accounts(id),
    FOREIGN KEY (to_account_id) REFERENCES accounts(id)
);