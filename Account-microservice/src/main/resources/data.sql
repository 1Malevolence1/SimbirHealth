
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

INSERT INTO public.users (user_id, last_name, first_name, username, password, deleted) VALUES
(6, 'doctor1', 'Doctor One', 'doctor1', crypt('doctor1', gen_salt('bf')), FALSE),
(7, 'doctor2', 'Doctor Two', 'doctor2', crypt('doctor2', gen_salt('bf')), FALSE),
(8, 'doctor3', 'Doctor Three', 'doctor3', crypt('doctor3', gen_salt('bf')), FALSE),

(9, 'manager1', 'Manager One', 'manager1', crypt('manager1', gen_salt('bf')), FALSE),
(10, 'manager2', 'Manager Two', 'manager2', crypt('manager2', gen_salt('bf')), FALSE),
(11, 'manager3', 'Manager Three', 'manager3', crypt('manager3', gen_salt('bf')), FALSE),

(12, 'user1', 'User One', 'user1', crypt('user1', gen_salt('bf')), FALSE),
(13, 'user2', 'User Two', 'user2', crypt('user2', gen_salt('bf')), FALSE),
(14, 'user3', 'User Three', 'user3', crypt('user3', gen_salt('bf')), FALSE)
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO public.user_roles(role_id, user_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4)
ON CONFLICT (role_id, user_id) DO NOTHING;


INSERT INTO public.user_roles(role_id, user_id) VALUES
(3, 6),  -- doctor1
(3, 7),  -- doctor2
(3, 8),  -- doctor3
(4, 9),  -- manager1
(4, 10), -- manager2
(4, 11), -- manager3
(1, 12), -- user1
(1, 13), -- user2
(1, 14)  -- user3
ON CONFLICT (role_id, user_id) DO NOTHING;

SELECT last_value FROM users_user_id_seq;

SELECT setval('users_user_id_seq', (SELECT MAX(user_id) FROM users));

