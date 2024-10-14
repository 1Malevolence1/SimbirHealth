
INSERT INTO public.role(role_id, role_name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_DOCTOR'),
(4, 'ROLE_MANAGER')
ON CONFLICT (role_id) DO NOTHING;


INSERT INTO public.users (user_id, last_name, first_name, username, password, deleted) VALUES
(1, 'user', 'user', 'user', crypt('user', gen_salt('bf')), FALSE),
(2, 'admin', 'admin', 'admin', crypt('admin', gen_salt('bf')), FALSE),
(3, 'doctor', 'doctor', 'doctor', crypt('doctor', gen_salt('bf')), FALSE),
(4, 'manager', 'manager', 'manager', crypt('manager', gen_salt('bf')), FALSE)
ON CONFLICT (user_id) DO NOTHING;


INSERT INTO public.user_roles(role_id, user_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4)
ON CONFLICT (role_id, user_id) DO NOTHING;


SELECT last_value FROM users_user_id_seq;

SELECT setval('users_user_id_seq', (SELECT MAX(user_id) FROM users));

