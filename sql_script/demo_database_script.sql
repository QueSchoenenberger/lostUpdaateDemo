CREATE DATABASE IF NOT EXISTS demo_database;
USE demo_database;

CREATE TABLE IF NOT EXISTS bank_account (
    account_id INT PRIMARY KEY auto_increment,
    balance DECIMAL(10, 2)
);

INSERT INTO `demo_database`.`bank_account` (`account_id`, `balance`) VALUES ('1', '100');