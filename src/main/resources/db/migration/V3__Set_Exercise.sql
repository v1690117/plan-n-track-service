alter table sets
    add column exercise_id bigint
        constraint sets_execise_id_exercises_fk_constr references exercises;
