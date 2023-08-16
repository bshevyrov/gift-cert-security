INSERT INTO gift_certificate(id, created_date, name, description, price, duration)
VALUES (1, '2023-04-29 22:07:01', 'First Client', 'Special promotion for first customerEntity', 100, 66),
       (2, '2023-04-29 22:17:01', 'Initial promotion campaign -1', 'Promotion for new customers.', 20, 7),
       (3, '2023-04-29 21:07:01', 'Initial promotion campaign -2', 'Promotion for new customers.', 20, 7);
INSERT INTO tag(id, created_date,  name)
VALUES (10, '2023-04-29 22:07:01', 'birthday'),
       (2, '2023-04-27 22:07:01', 'vip'),
       (3, '2023-04-26 22:07:01', 'promo'),
       (4, '2023-04-2 22:07:01', 'partner');
INSERT INTO gift_certificate_tag
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
INSERT INTO customer(id, created_date, name)
VALUES (1, now(), 'Alex'),
       (2, now(), 'Vlad'),
       (3, now(), 'Bohdan'),
       (4, now(), 'Denis');

INSERT INTO `order`(id, created_date, customer_id, cost)
VALUES (1, now(), 2, 99),
       (2, now(), 3, 97),
       (3, now(), 3, 96),
       (5, now(), 2, 98),
       (4, now(), 4, 95);

INSERT INTO order_item(id, created_date, gift_certificate_id, order_id, quantity)
VALUES (1, now(), 2, 1, 1),
       (2, now(), 3, 1, 2),
       (3, now(), 3, 2, 1),
       (4, now(), 2, 3, 3),
       (5, now(), 1, 4, 2);







