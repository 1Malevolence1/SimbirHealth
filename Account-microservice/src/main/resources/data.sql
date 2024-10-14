
INSERT INTO public.role (role_id, role_name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_DOCTOR'),
(4, 'ROLE_MANAGER')
ON CONFLICT (role_id) DO NOTHING;


INSERT INTO public.user_profiles (last_name, first_name, username, password, deleted) VALUES
('user', 'user', 'user', crypt('user', gen_salt('bf')), FALSE),
('admin', 'admin', 'admin', crypt('admin', gen_salt('bf')), FALSE),
('doctor', 'doctor', 'doctor', crypt('doctor', gen_salt('bf')), FALSE),
('manager', 'manager', 'manager', crypt('manager', gen_salt('bf')), FALSE)
ON CONFLICT (user_id) DO NOTHING;


INSERT INTO public.user_roles(role_id, user_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4)
ON CONFLICT (role_id, user_id) DO NOTHING;


