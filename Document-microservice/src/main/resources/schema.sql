CREATE TABLE IF NOT EXISTS public.history (
    history_id SERIAL PRIMARY KEY,
    pacient_id INT NOT NULL,
    hospital_id INT NOT NULL,
    doctor_id INT NOT NULL,
    data TEXT NOT NULL,
    history_date TIMESTAMP NOT NULL,
    room VARCHAR(255) NOT NULL
);