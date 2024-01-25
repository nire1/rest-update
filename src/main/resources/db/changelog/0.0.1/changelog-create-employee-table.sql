--liquibase formatted sql

--changeset DenisTsai:create-employee-table
--comment create create-employee-table
create table rest.employee
(
    id                serial primary key,
    name              varchar not null,
    surname           varchar not null,
    sex               varchar,
    date_birth        date    not null,
    phone             varchar,
    email             varchar,
    date_of_first_day timestamp,
    date_of_last_day  timestamp,
    position          varchar,
    salary            int not null,
    is_leader         boolean not null,
    departments_id    bigint
);

--rollback drop table rest.departments_employee;

--changeset DenisTsai:create-rest-departments_employee-table
--comment create table rest-departments_employee
create table rest.departments_employee
(
    departments_id integer not null,
    employee_id    integer not null
);
--rollback drop table rest.departments_employee;

--changeset DenisTsai:create-rest-departments_employee-table-constrains
--comment add constrains to rest-departments_employee
alter table rest.employee
    add foreign key (departments_id) references rest.departments(id);

alter table rest.departments_employee
    add constraint departments_employee__employee__fk
        foreign key (employee_id) references rest.employee (id);

alter table rest.departments_employee
    add constraint departments_employee__departments_fk
        foreign key (departments_id) references rest.departments(id);


alter table rest.departments_employee
    add constraint departments_employee_unique
        unique (departments_id,employee_id);
--rollback alter table rest.departments_employee drop constraint departments_employee__employee__fk;
--rollback alter table rest.departments_employee drop constraint departments_employee__departments_fk;
--rollback alter table rest.departments drop constraint departments_employee_unique;

--changeset DenisTsai:fill-rest-departments_employee-table
--comment fill rest.departments_employee-table

INSERT INTO rest.departments_employee
(departments_id, employee_id)
SELECT d.id,e.id
FROM rest.departments d
         CROSS JOIN rest.employee e