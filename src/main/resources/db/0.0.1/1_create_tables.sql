CREATE TABLE user_info
(
	id             SERIAL NOT NULL CONSTRAINT user_info_pk PRIMARY KEY,
	name           TEXT   NOT NULL,
	second_name    TEXT,
	surname        TEXT   NOT NULL,
	second_surname TEXT   NOT NULL,
	email          TEXT   NOT NULL,
	user_name      TEXT   NOT NULL
);

CREATE UNIQUE INDEX user_info_email_uindex ON user_info (email);
CREATE UNIQUE INDEX user_info_user_name_uindex ON user_info (user_name);

CREATE TABLE item
(
	id    SERIAL NOT NULL CONSTRAINT item_pk PRIMARY KEY,
	name  TEXT   NOT NULL,
	price MONEY  NOT NULL
);