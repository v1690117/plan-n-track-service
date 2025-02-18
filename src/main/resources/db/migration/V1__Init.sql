create table if not exists users
(
    id              bigint       not null
        primary key,
    external_id     varchar(255) not null
        constraint user_external_id_unique_constr
            unique,
    external_source varchar(255) not null,
    login           varchar(255) not null
);
create sequence if not exists workouts_seq increment by 1;

create table if not exists workouts
(
    id      bigint not null
        primary key,
    date    timestamp(6),
    title   varchar(255),
    user_id bigint not null
        constraint workouts_user_id_users_fk_constr
            references users
);
create sequence if not exists users_seq increment by 1;

create table if not exists sets
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
create sequence if not exists sets_seq increment by 1;
