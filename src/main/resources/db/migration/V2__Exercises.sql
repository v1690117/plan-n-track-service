create table if not exists exercises
(
    id      bigint not null
        primary key,
    title   varchar(255),
    user_id bigint not null
        constraint exercises_user_id_users_fk_constr
            references users
);
create sequence if not exists exercises_seq increment by 1;
