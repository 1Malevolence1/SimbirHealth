

CREATE TABLE IF NOT EXISTS public.timetable (
    timetable_id SERIAL PRIMARY KEY,
    hospital_id INT REFERENCES public.hospital(hospital_id) ON DELETE CASCADE NOT NULL,
    doctor_id INT REFERENCES public.users(user_id) ON DELETE CASCADE NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    room VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.appointment (
    appointment_id SERIAL PRIMARY KEY,
    recording TIMESTAMP NOT NULL,
    active BOOLEAN NOT NULL,
    user_id INT REFERENCES public.users(user_id) ON DELETE CASCADE NOT NULL,  -- Каскадное удаление для user_id
    timetable_id INT REFERENCES public.timetable(timetable_id) ON DELETE CASCADE NOT NULL  -- Каскадное удаление для timetable
);
