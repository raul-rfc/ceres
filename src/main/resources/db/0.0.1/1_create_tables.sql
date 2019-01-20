create table user_info
(
	id serial not null constraint user_info_pk primary key,
	name text not null,
	second_name text,
	surname text not null,
	second_surname text not null,
	email text not null,
	user_name text not null
);

create unique index user_info_email_uindex on user_info (email);
create unique index user_info_user_name_uindex on user_info (user_name);

create table item
(
	id serial not null constraint item_pk primary key,
	name  text   not null,
	price money  not null
);