CREATE EXTENSION IF NOT EXISTS pgcrypto;



CREATE TABLE IF NOT EXISTS public.users (
    user_id SERIAL PRIMARY KEY,
    last_name VARCHAR(30) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    username VARCHAR(30) NOT NULL unique,
    password TEXT NOT NULL,
    deleted BOOLEAN NOT null
);

CREATE TABLE IF NOT EXISTS public.role (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(15) NOT NULL
);



CREATE TABLE IF NOT EXISTS public.user_roles (
    role_id INT REFERENCES public.role(role_id),
    user_id INT REFERENCES public.users(user_id),
    PRIMARY KEY (role_id, user_id)
);


CREATE TABLE IF NOT EXISTS public.black_listed_token(
    black_listed_token_id serial primary key,
    token text not null,
    expiration_time timestamp not null
);



