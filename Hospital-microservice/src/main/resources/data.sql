


INSERT INTO public.hospital (hospital_id, hospital_name, address, contact_phone, deleted) VALUES
(1, 'City Hospital', '123 Main St, Springfield', '555-1234', FALSE),
(2, 'County Hospital', '456 Elm St, Springfield', '555-5678', FALSE),
(3, 'General Hospital', '789 Oak St, Springfield', '555-9012', FALSE)
ON CONFLICT (hospital_id) DO NOTHING;

INSERT INTO public.room (room_id, title, hospital_id) VALUES
(1, '101', 1),
(2, '102', 1),
(3, '103', 1)
ON CONFLICT (room_id) DO NOTHING;


INSERT INTO public.room (room_id, title, hospital_id) VALUES
(4, '201', 2),
(5, '202', 2),
(6, '203', 2)
ON CONFLICT (room_id) DO NOTHING;


INSERT INTO public.room (room_id, title, hospital_id) VALUES
(7, '301', 3),
(8, '302', 3),
(9, '303', 3)
ON CONFLICT (room_id) DO NOTHING;
