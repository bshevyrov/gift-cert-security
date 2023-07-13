INSERT INTO gift_certificate
VALUES (1, 'First Client', 'Special promotion for first customer', 100, '2023-04-29 22:07:01'
       , '2023-04-29 22:07:01', 66),
       (2, 'Initial promotion campaign -1', 'Promotion for new customers.', 20, '2023-04-28 22:10:17'
       , '2023-04-28 22:10:17', 7),
       (3, 'Initial promotion campaign -2', 'Promotion for new customers.', 20, '2023-04-28 22:10:17'
       , '2023-04-28 22:10:17', 7),
       (4, 'Initial promotion campaign -3', 'Promotion for new customers.', 20, '2023-04-28 22:10:17'
       , '2023-04-28 22:10:17', 7),
       (5, 'Initial promotion campaign -4', 'Promotion for new customers.', 20, '2023-04-28 22:10:17'
       , '2023-04-28 22:10:17', 7),
       (6, 'Initial promotion campaign -5', 'Promotion for new customers.', 20, '2023-04-28 22:10:17'
       , '2023-04-28 22:10:17', 7),
       (7, 'Initial promotion campaign -6', 'Promotion for new customers.', 20, '2023-04-28 22:10:17'
       , '2023-04-28 22:10:17', 7),
       (8, 'Initial promotion campaign -7', 'Promotion for new customers.', 20, '2023-04-28 22:10:17'
       , '2023-04-28 22:10:17', 7),
       (9, 'Initial promotion campaign -8', 'Promotion for new customers.', 20, '2023-04-28 22:10:17'
       , '2023-04-28 22:10:17', 7),
       (10, 'Initial promotion campaign -9', 'Promotion for new customers.', 20, '2023-04-28 22:10:17'
       , '2023-04-28 22:10:17', 7),
       (11, 'Initial promotion campaign -10', 'Promotion for new customers.', 20, '2023-04-28 22:10:17'
       , '2023-04-28 22:10:17', 7);
INSERT INTO tag
VALUES (1, 'birthday'),
       (2, 'vip'),
       (3, 'promo'),
       (4, 'partner');
INSERT INTO gift_certificate_has_tag
VALUES (1, 2),
       (1, 3),
       (2, 3),
       (3, 3),
       (4, 3),
       (5, 3),
       (6, 3),
       (7, 3),
       (8, 3),
       (9, 3),
       (10, 3);
