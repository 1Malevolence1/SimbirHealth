
INSERT INTO public.timetable (hospital_id, doctor_id, start_time, end_time, room) VALUES
(1, 3, '2024-04-25T11:30:00Z', '2024-04-25T12:00:00Z', '101'),
(1, 3, '2024-04-25T12:30:00Z', '2024-04-25T13:00:00Z', '102'),
(2, 3, '2024-04-25T14:00:00Z', '2024-04-25T15:00:00Z', '201')
ON CONFLICT (timetable_id) DO NOTHING;


INSERT INTO public.appointment (recording, active, user_id, timetable_id) VALUES
('2024-04-25T12:00:00Z', TRUE, 1, 1),
('2024-04-25T12:30:00Z', TRUE, 1, 1),

('2024-04-25T13:00:00Z', TRUE, 1, 2),
('2024-04-25T13:30:00Z', TRUE, 1, 2),

('2024-04-25T14:30:00Z', TRUE, 1, 3),
('2024-04-25T14:30:00Z', TRUE, 1, 3)
ON CONFLICT (appointment_id) DO NOTHING;
