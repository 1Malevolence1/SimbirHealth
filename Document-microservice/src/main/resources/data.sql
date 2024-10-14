INSERT INTO public.history (history_date, pacient_id, hospital_id, doctor_id, data, room) VALUES
('2024-04-25T11:00:00', 1, 1, 3, 'Пациент пришёл на осмотр. Рекомендовано пройти анализы.', '101'),
('2024-04-26T11:30:00', 1, 1, 3, 'Пациент получил результаты анализов. Назначено лечение.', '102')
ON CONFLICT (history_id) DO NOTHING;