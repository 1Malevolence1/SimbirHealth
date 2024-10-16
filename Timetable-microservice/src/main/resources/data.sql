-- Вставка записей в таблицу timetable
INSERT INTO public.timetable (timetable_id, hospital_id, doctor_id, start_time, end_time, room) VALUES
(1, 1, 1, '2024-04-25T11:30:00Z', '2024-04-25T12:00:00Z', '101'),
(2, 1, 2, '2024-04-25T12:30:00Z', '2024-04-25T13:00:00Z', '102'),
(3, 2, 1, '2024-04-25T14:00:00Z', '2024-04-25T15:00:00Z', '201')
ON CONFLICT (timetable_id) DO NOTHING;

-- Вставка записей в таблицу appointment
INSERT INTO public.appointment (appointment_id, recording, active, user_id, timetable_id) VALUES
(1,'2024-04-25T12:00:00Z', TRUE, 1, 1),
(2,'2024-04-25T12:30:00Z', TRUE, 1, 1),
(3,'2024-04-25T13:00:00Z', TRUE, 1, 2),
(4,'2024-04-25T13:30:00Z', TRUE, 1, 2),
(5,'2024-04-25T14:30:00Z', TRUE, 1, 3)
ON CONFLICT (appointment_id) DO NOTHING;

-- Получение последнего значения последовательности для timetable_id
SELECT last_value FROM timetable_timetable_id_seq;

-- Установка значения последовательности на максимальное значение timetable_id
SELECT setval('timetable_timetable_id_seq', (SELECT MAX(timetable_id) FROM public.timetable));

-- Получение последнего значения последовательности для appointment_id
SELECT last_value FROM appointment_appointment_id_seq;

-- Установка значения последовательности на максимальное значение appointment_id
SELECT setval('appointment_appointment_id_seq', (SELECT MAX(appointment_id) FROM public.appointment));
