-- create a new database called 'gestioneordini;'
CREATE database gestioneordini;

\connect gestioneordini;

-- Orders: Oggetti acquistati da amazon  dai nostri clienti
CREATE TABLE public.torders (
	amazon_order_id varchar NOT NULL,
	purchase_date timestamp without time zone NULL,
	last_update_date timestamp without time zone NULL,
	order_status varchar NULL,
	fulfillment_channel varchar NULL,
	number_of_items_shipped integer NULL,
	number_of_items_unshipped integer NULL,
	payment_method varchar NULL,
	payment_method_details varchar NULL,
	marketplace_id varchar NULL,
	shipment_service_level_category varchar NULL,
	order_type varchar NULL,
	earliest_ship_date timestamp without time zone NULL,
	latest_ship_date timestamp without time zone NULL,
	is_business_order bool NULL,
	is_prime bool NULL,
	is_global_express_enabled bool NULL,
	is_premium_order bool NULL,
	is_sold_by_ab bool NULL,
	company_legal_name varchar NULL,
	buyer_email varchar NULL,
	buyer_name varchar NULL,
	purchase_order_number varchar NULL,
	shipping_address_name varchar NULL,
	shipping_address_line1 varchar NULL,
	shipping_address_city varchar NULL,
	shipping_city_state_or_region varchar NULL,
	shipping_state_or_region_postal_code varchar NULL,
	CONSTRAINT torders_pk PRIMARY KEY (amazon_order_id)
);

-- Items: Oggetti nel nostro stock
CREATE TABLE public.titems (
	asin varchar NOT NULL,
	title varchar NULL,
	category varchar NULL,
	price decimal(9,2) NULL,
	stock integer NULL,
	brand varchar NULL,
	CONSTRAINT titems_pk PRIMARY KEY (asin)
);

-- Orders Items: tabella N:N tra Orders e Items, un order può avere più item e viceversa
CREATE TABLE public.tordersitems (
	order_item_id varchar NOT NULL,
	amazon_order_id varchar NULL,
	asin varchar NULL,
	title varchar NULL,
	quantity_ordered integer NULL,
	quantity_shipped integer NULL,
	points_granted_points_number decimal(9,2) NULL,
	points_granted_points_monetary_value_currency_code varchar NULL,
	points_granted_points_monetary_value_amount decimal(9,2) NULL,
	item_price_currency_code varchar NULL,
	item_price_amount decimal(9,2) NULL,
	shipping_price_currency_code varchar NULL,
	shipping_price_amount decimal(9,2) NULL,
	promotion_ids varchar NULL,
	CONSTRAINT tordersitems_pk PRIMARY KEY (order_item_id),
	CONSTRAINT torders_fk FOREIGN KEY (amazon_order_id) REFERENCES public.torders(amazon_order_id),
	CONSTRAINT titems_fk FOREIGN KEY (asin) REFERENCES public.titems(asin)
);

-- Suppliers: persone da qui compriamo gli oggeti per fare il restock
CREATE TABLE public.tsuppliers (
	supplier_id serial NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT tsuppliers_pk PRIMARY KEY (supplier_id)
);

-- Purchases: Acquisti per il restock
CREATE TABLE public.tpurchases (
	purchase_id serial NOT NULL,
	supplier_id integer NULL,
	bill_date date NULL,
	bill_number integer NULL,
	CONSTRAINT tacquisti_pk PRIMARY KEY (purchase_id),
	CONSTRAINT tsuppliers_fk FOREIGN KEY (supplier_id) REFERENCES public.tsuppliers(supplier_id)
);

-- Purchases - Items: tabella N:N tra Purchases e Items, un acquisto può avere più items e viceversa
CREATE TABLE public.tpurchasesitems (
	purchases_items_id serial NOT NULL,
	purchase_id integer NULL,
	asin varchar NULL,
	purchased_amount integer NULL,
	unit_price decimal(9,2) NULL,
	CONSTRAINT tpurchasesitems_pk PRIMARY KEY (purchases_items_id),
	CONSTRAINT titems_fk FOREIGN KEY (asin) REFERENCES public.titems(asin),
	CONSTRAINT tpurchases_fk FOREIGN KEY (purchase_id) REFERENCES public.tpurchases(purchase_id)
);

-- Users: Utenti interni per la nostra autenticazione
CREATE TABLE public.tusers (
	user_id serial NOT NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	CONSTRAINT tusers_pk PRIMARY KEY (user_id)
);

/******************************************************************************************
 INSERT
 *******************************************************************************************/
INSERT INTO
	public.torders(
		amazon_order_id,
		purchase_date,
		last_update_date,
		order_status,
		fulfillment_channel,
		number_of_items_shipped,
		number_of_items_unshipped,
		payment_method,
		payment_method_details,
		marketplace_id,
		shipment_service_level_category,
		order_type,
		earliest_ship_date,
		latest_ship_date,
		is_business_order,
		is_prime,
		is_global_express_enabled,
		is_premium_order,
		is_sold_by_ab,
		company_legal_name,
		buyer_email,
		buyer_name,
		purchase_order_number,
		shipping_address_name,
		shipping_address_line1,
		shipping_address_city,
		shipping_city_state_or_region,
		shipping_state_or_region_postal_code
	)
VALUES
	(
		'028-0000011-0000001',
		'2021-06-02 16:09:52.00',
		'2021-06-02 18:09:52.00',
		'Shipped',
		'AFN',
		1,
		0,
		'Other',
		'Standard',
		'APJ6JRA9NG5V4',
		'Expedited',
		'StandardOrder',
		'2021-06-02 16:09:52.00',
		'2021-06-02 16:09:52.00',
		'false',
		'true',
		'false',
		'false',
		'false',
		'-',
		'aaa@gmail.com',
		'tony renis',
		'-',
		'tony renis',
		'Via como 11',
		'Padova',
		'IT',
		35100
	),
	(
		'028-0000011-0000023',
		'2021-06-03 11:14:32.00',
		'2021-06-02 18:44:52.00',
		'Shipped',
		'AFN',
		1,
		0,
		'Other',
		'Standard',
		'APJ6JRA9NG5V4',
		'Standard',
		'StandardOrder',
		'2021-06-03 11:14:32.00',
		'2021-06-03 11:14:32.00',
		'true',
		'true',
		'false',
		'false',
		'true',
		'rossi spa',
		'bbb@gmail.com',
		'rossi spa',
		'-',
		'rossi spa',
		'Via Vecellio 37',
		'Roma',
		'IT',
		00100
	),
	(
		'028-0000011-0000028',
		'2021-06-04 18:19:42.00',
		'2021-06-04 19:19:52.00',
		'Shipped',
		'AFN',
		1,
		0,
		'Other',
		'Standard',
		'APJ6JRA9NG5V4',
		'Expedited',
		'StandardOrder',
		'2021-06-04 19:19:52.00',
		'2021-06-04 18:19:42.00',
		'false',
		'false',
		'false',
		'false',
		'false',
		'-',
		'ccc@gmail.com',
		'michele reali',
		'-',
		'michele reali',
		'Via po 18',
		'Rovigo',
		'IT',
		45100
	);

INSERT INTO
	public.titems(asin, title, category, price, stock, brand)
values
	(
		'B07D9SB7XW',
		'Minecraft',
		'Gioco',
		25.9,
		300,
		'Mojang'
	),
	(
		'B07VK4QKBP',
		'Manubrio',
		'Sport',
		199.9,
		10,
		'Homcom'
	),
	(
		'B08KSS6CLT',
		'The alla pesca',
		'Cibo',
		1.8,
		25,
		'Lipton'
	),
	(
		'B08123PCJH',
		'Aria fritta',
		'Altro',
		11,
		12,
		'Aria'
	),
	(
		'B07K495TYN',
		'Unicorno',
		'Gioco',
		18.9,
		25,
		'Gioco spa'
	);

INSERT INTO
	public.tsuppliers(name)
VALUES
	('Lorezo'),
	('Pietro'),
	('Marco'),
	('Matteo');

INSERT INTO
	public.tpurchases(supplier_id, bill_date, bill_number)
values
	(1, '09-06-2021', 1);

INSERT INTO
	public.tpurchasesitems(
		purchase_id,
		asin,
		purchased_amount,
		unit_price
	)
values
	(1, 'B07D9SB7XW', 1, 25.9);

INSERT INTO
	public.tordersitems(
		order_item_id,
		amazon_order_id,
		asin,
		title,
		quantity_ordered,
		quantity_shipped,
		points_granted_points_number,
		points_granted_points_monetary_value_currency_code,
		points_granted_points_monetary_value_amount,
		item_price_currency_code,
		item_price_amount,
		shipping_price_currency_code,
		shipping_price_amount,
		promotion_ids
	)
values
	(
		'0000567',
		'028-0000011-0000001',
		'B07D9SB7XW',
		'Minecraft - Nintendo Switch',
		1,
		1,
		5,
		'EUR',
		1.33,
		'EUR',
		25.99,
		'EUR',
		0,
		'DE CORE 2021-06-02'
	);

INSERT INTO
	public.tusers (username, password)
VALUES
('admin', 'admin');
