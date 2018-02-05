--liquibase formatted sql
--changeset mathysjtaljaard:create-table-contracts
CREATE TABLE IF NOT EXISTS contracts (
	id INT PRIMARY KEY AUTO_INCREMENT,
	amountRequested DECIMAL NOT NULL,
	businessNumber VARCHAR(255) NOT NULL,
	activitationDate DATETIME,
	name VARCHAR(255) NOT NULL,
	status VARCHAR(50),
	contractType VARCHAR(50),
	updateDate DATETIME,
	createDate DATETIME
);