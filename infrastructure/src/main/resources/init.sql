INSERT INTO test.customer (id, address, name, phone_number) VALUES (1, '123 Main Street', 'John Doe', '123-456-7890');

INSERT INTO test.restaurant (id, name) VALUES (1, '마녀주방');
INSERT INTO test.restaurant (id, name) VALUES (2, '이치란 라멘');

INSERT INTO test.menu (id, name, price, restaurant_id) VALUES (1, 'pasta', 12000, 1);
INSERT INTO test.menu (id, name, price, restaurant_id) VALUES (2, '리조또', 11000, 1);
INSERT INTO test.menu (id, name, price, restaurant_id) VALUES (3, '돈코츠 라멘', 9000, 2);
INSERT INTO test.menu (id, name, price, restaurant_id) VALUES (4, '카라쿠치 라멘', 10000, 2);
INSERT INTO test.menu (id, name, price, restaurant_id) VALUES (5, '사케', 15000, 2);
