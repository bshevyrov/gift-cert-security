INSERT INTO gift_certificate
VALUES (1, '2023-04-29 22:07:01', NULL, NULL, 'First Client', 'Special promotion for first customerEntity', 100, 66),
       (2, '2023-04-29 22:17:01', NULL, NULL, 'Initial promotion campaign -1', 'Promotion for new customers.', 20, 7),
       (3, '2023-04-29 21:07:01', NULL, NULL, 'Initial promotion campaign -2', 'Promotion for new customers.', 20, 7);
INSERT INTO tag
VALUES (10, NULL, NULL, NULL, 'birthday'),
       (2, NULL, NULL, NULL, 'vip'),
       (3, NULL, NULL, NULL, 'promo'),
       (4, NULL, NULL, NULL, 'partner');
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
INSERT INTO customer
VALUES (2, NULL, NULL, NULL, 'Alex'),
       (3, NULL, NULL, NULL, 'Vlad'),
       (4, NULL, NULL, NULL, 'Bohdan'),
       (5, NULL, NULL, NULL, 'Denis');

INSERT INTO `order`
VALUES (1, NULL, NULL, NULL, 2, 99),
       (5, NULL, NULL, NULL, 2, 98),
       (2, NULL, NULL, NULL, 3, 97),
       (3, NULL, NULL, NULL, 3, 96),
       (4, NULL, NULL, NULL, 4, 95);

INSERT INTO order_item
VALUES (1, NULL, NULL, NULL, 2, 1, 1),
       (2, NULL, NULL, NULL, 3, 1, 2),
       (3, NULL, NULL, NULL, 3, 2, 1),
       (4, NULL, NULL, NULL, 2, 3, 3),
       (5, NULL, NULL, NULL, 6, 4, 2);







