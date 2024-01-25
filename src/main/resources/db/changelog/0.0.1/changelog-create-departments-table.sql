--liquibase formatted sql

--changeset DenisTsai:create-departments-table
--comment create create-departments-table
create table rest.departments
(
    id      serial primary key,
    name    varchar not null ,
    created_timestamp  date  not null,
    up_id    integer
);
