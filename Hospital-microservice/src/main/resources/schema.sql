CREATE TABLE IF NOT EXISTS public.hospital (
    hospital_id SERIAL PRIMARY KEY,
    hospital_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact_phone VARCHAR(25) NOT NULL,
    deleted BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS public.room (
    room_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    hospital_id INT REFERENCES public.hospital(hospital_id)
);
