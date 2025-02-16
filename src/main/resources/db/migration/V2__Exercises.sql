create table exercises
(
    id      bigint not null
        primary key,
    title   varchar(255),
    user_id bigint not null
        constraint exercises_user_id_users_fk_constr
            references users
);
alter table exercises
    owner to postgres;

create sequence exercises_seq increment by 1;
alter sequence exercises_seq owner to postgres;
