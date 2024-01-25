--liquibase formatted sql

--changeset DenisTsai:create-rest-schema
--comment create new schema
create schema rest;
--rollback drop schema rest;
