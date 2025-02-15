create table users
(
    id              bigint       not null
        primary key,
    external_id     varchar(255) not null
        constraint user_external_id_unique_constr
            unique,
    external_source varchar(255) not null,
    login           varchar(255) not null
);
alter table users
    owner to postgres;

create table workouts
(
    id      bigint not null
        primary key,
    date    timestamp(6),
    title   varchar(255),
    user_id bigint not null
        constraint workouts_user_id_users_fk_constr
            references users
);
alter table workouts
    owner to postgres;

create table sets
(
    id         bigint not null
        primary key,
    completed  boolean,
    index      integer,
    load       real,
    reps       integer,
    rest       integer,
    title      varchar(255),
    workout_id bigint
        constraint sets_workout_id_workouts_fk_constr
            references workouts,
    sets_order integer
);
alter table sets
    owner to postgres;

create sequence users_seq increment by 50;
alter sequence users_seq owner to postgres;

create sequence workouts_seq increment by 50;
alter sequence workouts_seq owner to postgres;

create sequence sets_seq increment by 50;
alter sequence sets_seq owner to postgres;
