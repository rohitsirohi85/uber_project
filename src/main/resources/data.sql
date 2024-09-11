INSERT INTO app_user (name, email, password) VALUES
('rohit', 'rohit@gmail.com', '$2a$10$7Xo.4tOic9PjndDYgRmfoO7L7bBC5rKKmUnN2odZ1KLl/D5DAf3G6'), -- bcrypt hash of '123'
('sneha', 'sneha@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZo5i.U0EJ2sNKi/dWtpYl1KwLrG/nlD3cFBO'), -- bcrypt hash of '456'
('aman', 'aman@gmail.com', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36J3mPbE0kpWXw3/kJT68Li'), -- bcrypt hash of '789'
('riya', 'riya@gmail.com', '$2a$10$7WOviPvZ9sAb2I5DgHgESedYuwPGFYXljA2wdeQmAoOzPzHNLyCZO'), -- bcrypt hash of 'abc'
('karan', 'karan@gmail.com', '$2a$10$KbQi/z.ywZnAMdGcfN3R/.ePP5UMhTOHk8gpbULdH6seuo26k1PG6'), -- bcrypt hash of 'def'
('pooja', 'pooja@gmail.com', '$2a$10$KbQi/z.ywZnAMdGcfN3R/.ePP5UMhTOHk8gpbULdH6seuo26k1PG6'), -- bcrypt hash of 'ghi'
('aditya', 'aditya@gmail.com', '$2a$10$KbQi/z.ywZnAMdGcfN3R/.ePP5UMhTOHk8gpbULdH6seuo26k1PG6'), -- bcrypt hash of 'jkl'
('neha', 'neha@gmail.com', '$2a$10$KbQi/z.ywZnAMdGcfN3R/.ePP5UMhTOHk8gpbULdH6seuo26k1PG6'), -- bcrypt hash of 'mno'
('rahul', 'rahul@gmail.com', '$2a$10$KbQi/z.ywZnAMdGcfN3R/.ePP5UMhTOHk8gpbULdH6seuo26k1PG6'), -- bcrypt hash of 'pqr'
('sakshi', 'sakshi@gmail.com', '$2a$10$KbQi/z.ywZnAMdGcfN3R/.ePP5UMhTOHk8gpbULdH6seuo26k1PG6'), -- bcrypt hash of 'stu'
('vinay', 'vinay@gmail.com', '$2a$10$KbQi/z.ywZnAMdGcfN3R/.ePP5UMhTOHk8gpbULdH6seuo26k1PG6'), -- bcrypt hash of 'vwx'
('nidhi', 'nidhi@gmail.com', '$2a$10$KbQi/z.ywZnAMdGcfN3R/.ePP5UMhTOHk8gpbULdH6seuo26k1PG6'), -- bcrypt hash of 'yz1'
('raj', 'raj@gmail.com', '$2a$10$KbQi/z.ywZnAMdGcfN3R/.ePP5UMhTOHk8gpbULdH6seuo26k1PG6');   -- bcrypt hash of '234'

INSERT INTO user_roles (user_id, roles) VALUES
(1, 'RIDER'),
(2, 'RIDER'),
(2, 'DRIVER'),
(3, 'RIDER'),
(3, 'DRIVER'),
(4, 'RIDER'),
(4, 'DRIVER'),
(5, 'RIDER'),
(5, 'DRIVER'),
(6, 'RIDER'),
(6, 'DRIVER'),
(7, 'RIDER'),
(7, 'DRIVER'),
(8, 'RIDER'),
(8, 'DRIVER'),
(9, 'RIDER'),
(9, 'DRIVER'),
(10, 'RIDER'),
(10, 'DRIVER'),
(11, 'RIDER'),
(11, 'DRIVER'),
(12, 'RIDER'),
(12, 'DRIVER'),
(13, 'RIDER'),
(13, 'DRIVER');

INSERT INTO rider (user_id,rating) VALUES
(1,4.9);

INSERT INTO driver (user_id, rating, available, current_location) VALUES
(2, 4.7, true, ST_GeomFromText('POINT(77.6737 27.4924)', 4326)), -- Shri Krishna Janmasthan Temple
(3, 4.5, true, ST_GeomFromText('POINT(77.6784 27.5035)', 4326)), -- Dwarkadhish Temple
(4, 4.8, true, ST_GeomFromText('POINT(77.6710 27.4950)', 4326)), -- Vishram Ghat
(5, 4.2, true, ST_GeomFromText('POINT(77.6843 27.5121)', 4326)), -- Kans Qila
(6, 4.9, true, ST_GeomFromText('POINT(77.6817 27.5116)', 4326)), -- Mathura Museum
(7, 4.6, true, ST_GeomFromText('POINT(77.6882 27.4954)', 4326)), -- Radha Kund
(8, 4.3, true, ST_GeomFromText('POINT(77.6965 27.4972)', 4326)), -- Govardhan Hill
(9, 4.4, true, ST_GeomFromText('POINT(77.6952 27.4957)', 4326)), -- Kusum Sarovar
(10, 4.7, true, ST_GeomFromText('POINT(77.6794 27.5015)', 4326)), -- ISKCON Vrindavan
(11, 4.1, true, ST_GeomFromText('POINT(77.6821 27.4959)', 4326)), -- Prem Mandir
(12, 4.8, true, ST_GeomFromText('POINT(77.6839 27.4933)', 4326)), -- Gita Mandir
(13, 4.5, true, ST_GeomFromText('POINT(77.6755 27.4892)', 4326)); -- Raman Reti

INSERT INTO wallet (user_id,balance) VALUES
(1,100),
(2,500);

