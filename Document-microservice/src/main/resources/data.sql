-- Вставка записей в таблицу history
INSERT INTO public.history (history_id, history_date, pacient_id, hospital_id, doctor_id, data, room) VALUES
(1,'2024-04-25T11:00:00', 1, 1, 3, 'Пациент пришёл на осмотр. Рекомендовано пройти анализы.', '101'),
(2,'2024-04-26T11:30:00', 1, 1, 3, 'Пациент получил результаты анализов. Назначено лечение.', '102')
ON CONFLICT (history_id) DO NOTHING;

-- Получение последнего значения последовательности для history_id
SELECT last_value FROM history_history_id_seq;

-- Установка значения последовательности на максимальное значение history_id
SELECT setval('history_history_id_seq', (SELECT MAX(history_id) FROM public.history));
