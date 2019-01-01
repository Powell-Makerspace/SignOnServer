-- Default sequence required for hibernate
CREATE SEQUENCE hibernate_sequence;



-- Create Members Table and sequence for storing member information
CREATE SEQUENCE member_member_id;

CREATE TABLE member
(
	member_id bigint not null primary key,
	member_name varchar(255),
	membership_type integer,
	punch_passes integer not null
);



-- Create Visit Table and sequence for storing visit information
CREATE SEQUENCE visit_visit_id;

CREATE TABLE visit
(
	visit_id bigint not null primary key,
	arrival_time timestamp,
	departure_time timestamp,
	visit_purpose integer,
	member_member_id bigint references member
);

