create table if not exists audit_gha
(
    id serial primary key,
    evento varchar(255) not null,
    mensaje varchar(255) not null,
    fecha TIMESTAMPTZ DEFAULT now()
);
