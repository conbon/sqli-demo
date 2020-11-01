CREATE SCHEMA IF NOT EXISTS sqli_demo;

CREATE TABLE IF NOT EXISTS sqli_demo.user_details (
    id varchar(50) CONSTRAINT user_pk PRIMARY KEY,
    first_name varchar (100),
    favourite_colour varchar(100),
    favourite_animal varchar(100)
)