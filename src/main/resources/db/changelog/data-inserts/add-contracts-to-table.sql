--liquibase formatted sql
--changeset mathysjtaljaard:insert-contracts-data
INSERT INTO contracts(amountRequested, businessNumber, activitationDate, name, status, contractType, createDate) VALUES (40000, 'c-12345', CURRENT_TIMESTAMP, 'oldies', 'Approved', 'Express', CURRENT_TIMESTAMP);
INSERT INTO contracts(amountRequested, businessNumber, activitationDate, name, status, contractType, createDate) VALUES (50000, 'c-12347', CURRENT_TIMESTAMP, 'junkers', 'Approved', 'Sales', CURRENT_TIMESTAMP);
INSERT INTO contracts(amountRequested, businessNumber, activitationDate, name, status, contractType, createDate) VALUES (400000,'c-12349', CURRENT_TIMESTAMP, 'exotics', 'Approved', 'Sales', CURRENT_TIMESTAMP);

INSERT INTO contracts(amountRequested, businessNumber, name, status, contractType, createDate) VALUES (45000,'c-12346', 'goldies', 'Denied', 'Express', CURRENT_TIMESTAMP);
INSERT INTO contracts(amountRequested, businessNumber, name, status, contractType, createDate) VALUES (60000,'c-12348', 'clunkers', 'Denied', 'Sales', CURRENT_TIMESTAMP);