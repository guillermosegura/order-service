INSERT INTO TC_Customer
(cd_id, nb_firstname, nb_lastname, nb_secondlastname, nb_rfc)
VALUES (1, 'Juan', 'Perez', 'Lopez', 'PELJ900510AB1');

INSERT INTO TC_Customer
(cd_id, nb_firstname, nb_lastname, nb_secondlastname, nb_rfc)
VALUES (2, 'Alma', 'Ruiz', 'Lopez', 'RULA');

INSERT INTO TC_Customer
(cd_id, nb_firstname, nb_lastname, nb_secondlastname, nb_rfc)
VALUES (3, 'Juan', 'Perez', 'Lopez', 'PELJ900510AB1');

INSERT INTO TC_Customer
(cd_id, nb_firstname, nb_lastname, nb_secondlastname, nb_rfc)
VALUES (4, 'Juan', 'Perez', 'Lopez', 'PELJ900510AB1');

INSERT INTO TC_Customer
(cd_id, nb_firstname, nb_lastname, nb_secondlastname, nb_rfc)
VALUES (5, 'Juan', 'Perez', 'Lopez', 'PELJ900510AB1');

INSERT INTO TC_Customer
(cd_id, nb_firstname, nb_lastname, nb_secondlastname, nb_rfc)
VALUES (6, 'Juan', 'Perez', 'Lopez', 'PELJ900510AB1');

INSERT INTO TC_Customer
(cd_id, nb_firstname, nb_lastname, nb_secondlastname, nb_rfc)
VALUES (7, 'Juan', 'Perez', 'Lopez', 'PELJ900510AB1');

INSERT INTO TC_Customer
(cd_id, nb_firstname, nb_lastname, nb_secondlastname, nb_rfc)
VALUES (8, 'Juan', 'Perez', 'Lopez', 'PELJ900510AB1');

INSERT INTO TC_Customer
(cd_id, nb_firstname, nb_lastname, nb_secondlastname, nb_rfc)
VALUES (9, 'Juan', 'Perez', 'Lopez', 'PELJ900510AB1');

INSERT INTO TC_Customer
(cd_id, nb_firstname, nb_lastname, nb_secondlastname, nb_rfc)
VALUES (10, 'Juan', 'Perez', 'Lopez', 'PELJ900510AB1');


INSERT INTO TC_ITEM
(cd_id, nb_sku, tx_description, im_price)
VALUES (1, 'A0001', 'Articulo 1', 10.0); 

INSERT INTO TC_ITEM
(cd_id, nb_sku, tx_description, im_price)
VALUES (2, 'A0002', 'Articulo 2', 10.0); 

INSERT INTO TC_ITEM
(cd_id, nb_sku, tx_description, im_price)
VALUES (3, 'A0003', 'Articulo 3', 10.0); 

INSERT INTO TC_ITEM
(cd_id, nb_sku, tx_description, im_price)
VALUES (4, 'A0004', 'Articulo 4', 10.0); 


INSERT INTO TE_Order
(cd_id, cd_customer, dt_order, im_subtotal, im_tax, im_total)
VALUES (1, 1, '2022-11-20 12:00:00', 100.0, 16.0, 116.0);

INSERT INTO TE_Order
(cd_id, cd_customer, dt_order, im_subtotal, im_tax, im_total)
VALUES (2, 1, '2022-11-20 12:00:00', 200.0, 32.0, 232.0);

INSERT INTO TE_Order
(cd_id, cd_customer, dt_order, im_subtotal, im_tax, im_total)
VALUES (3, 1, '2022-11-20 12:00:00', 300.0, 48.0, 348.0);

INSERT INTO TE_OrderDetail
(cd_id, cd_order, cd_item, nu_quantity, im_price, im_subtotal)
VALUES (1, 1, 1, 10, 10.0, 100.0);

INSERT INTO TE_OrderDetail
(cd_id, cd_order, cd_item, nu_quantity, im_price, im_subtotal)
VALUES (2, 2, 1, 10, 10.0, 100.0);

INSERT INTO TE_OrderDetail
(cd_id, cd_order, cd_item, nu_quantity, im_price, im_subtotal)
VALUES (3, 2, 2, 10, 10.0, 100.0);

INSERT INTO TE_OrderDetail
(cd_id, cd_order, cd_item, nu_quantity, im_price, im_subtotal)
VALUES (4, 3, 1, 10, 10.0, 100.0);

INSERT INTO TE_OrderDetail
(cd_id, cd_order, cd_item, nu_quantity, im_price, im_subtotal)
VALUES (5, 3, 2, 10, 10.0, 100.0);

INSERT INTO TE_OrderDetail
(cd_id, cd_order, cd_item, nu_quantity, im_price, im_subtotal)
VALUES (6, 3, 3, 10, 10.0, 100.0);