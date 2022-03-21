INSERT INTO public.user_orders
(id_user, id_stock, stock_symbol, stock_name, volume, volume_remaining, price, "type", status, created_on, updated_on)
VALUES(1, 1, 'BEEF', 'MINERVA', 5, 0, 10.000, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.user_orders
(id_user, id_stock, stock_symbol, stock_name, volume, volume_remaining, price, "type", status, created_on, updated_on)
VALUES(2, 2, 'EMBR', 'EMBRAER', 2, 0, 1.000, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.user_orders
(id_user, id_stock, stock_symbol, stock_name, volume, volume_remaining, price, "type", status, created_on, updated_on)
VALUES(3, 3, 'DESK', 'DESKTOP', 10, 0, 500, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.user_orders
(id_user, id_stock, stock_symbol, stock_name, volume, volume_remaining, price, "type", status, created_on, updated_on)
VALUES(4, 4, 'MOSI', 'MOSAICO', 10, 0, 2.000, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
