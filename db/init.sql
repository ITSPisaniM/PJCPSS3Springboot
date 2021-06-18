
-- create a new database called 'gestioneordini;'
cReate database gestioneordini;
\connect gestioneordini;


-- public.tacquisti definition

-- Drop table

-- DROP TABLE public.tacquisti;

CREATE TABLE public.tacquisti (
	acquistoid serial NOT NULL,
	fornitoreid int4 NULL,
	datafattura date NULL,
	numerofattura int4 NULL,
	caricoeffettuato int4 NULL,
	CONSTRAINT tacquisti_pk PRIMARY KEY (acquistoid)
);

-- public.tarticoliacquistati definition

-- Drop table

-- DROP TABLE public.tarticoliacquistati;

CREATE TABLE public.tarticoliacquistati (
	articoloacquistoid serial NOT NULL,
	acquistoid int4 NULL,
	itemid int4 NULL,
	quantitaacquistata int4 NULL,
	prezzounitarioacquisto numeric NULL,
	CONSTRAINT tsrticoliscquistati_pk PRIMARY KEY (articoloacquistoid)
);

-- public.tarticoliordinati definition

-- Drop table

-- DROP TABLE public.tarticoliordinati;

CREATE TABLE public.tarticoliordinati (
	articoloordinatoid serial NOT NULL,
	asin varchar NULL,
	amazonorderid int4 NULL,
	itempriceamount numeric NULL,
	itempricecurrencycode numeric NULL,
	orderitemid int4 NULL,
	pointsgrantedpointsmonetaryvalueamount numeric NULL,
	pointsgrantedpointsmonetaryvaluecurrencycode numeric NULL,
	pointsgrantedpointsnumber numeric NULL,
	promotionids int4 NULL,
	quantityordered int4 NULL,
	quantityshipped int4 NULL,
	shippingpriceamount numeric NULL,
	shippingpricecurrencycode numeric NULL,
	title varchar NULL,
	CONSTRAINT tarticoliscquistati_pk PRIMARY KEY (articoloordinatoid)
);


-- public.tfornitori definition

-- Drop table

-- DROP TABLE public.tfornitori;

CREATE TABLE public.tfornitori (
	fornitoreid serial NOT NULL,
	nome varchar NOT NULL,
	CONSTRAINT tfornitori_pk PRIMARY KEY (fornitoreid)
);


-- public.titems definition

-- Drop table

-- DROP TABLE public.titems;

CREATE TABLE public.titems (
	itemid serial NOT NULL,
	asin varchar NULL,
	titolo varchar NULL,
	categoria varchar NULL,
	prezzo numeric NULL,
	giacenza int4 NULL,
	brand varchar NULL,
	CONSTRAINT titems_pk PRIMARY KEY (itemid)
);


-- public.torders definition

-- Drop table

-- DROP TABLE public.torders;

CREATE TABLE public.torders (
	amazonorderid serial NOT NULL,
	buyeremail varchar NULL,
	buyername varchar NULL,
	companylegalname varchar NULL,
	earliestshipdate date NULL,
	fulfillmentchannel int4 NULL,
	isbusinessorder bool NULL,
	isglobalexpressenabled bool NULL,
	ispremiumorder bool NULL,
	isprime bool NULL,
	issoldbyab bool NULL,
	lastupdatedate date NULL,
	latestshipdate date NULL,
	marketplaceid varchar NULL,
	numberofitemsshipped int4 NULL,
	numberofitemsunshipped int4 NULL,
	orderstatus varchar NULL,
	ordertype varchar NULL,
	paymentmethod varchar NULL,
	paymentmethoddetails varchar NULL,
	purchasedate date NULL,
	purchaseordernumber int4 NULL,
	shipmentservicelevelcategory int4 NULL,
	shippingaddresscity varchar NULL,
	shippingaddressline1 varchar NULL,
	shippingaddressname varchar NULL,
	shippingcitystateorregion varchar NULL,
	shippingstateorregionpostalcode varchar NULL,
	CONSTRAINT torders_pk PRIMARY KEY (amazonorderid)
);

-- public.tusers definition

-- Drop table

-- DROP TABLE public.tusers;

CREATE TABLE public.tusers (
	userid serial NOT NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	CONSTRAINT tusers_pk PRIMARY KEY (userid)
);

/******************************************************************************************
					INSERT
*******************************************************************************************/

INSERT INTO public.tacquisti ("fornitoreid", "datafattura", "numerofattura", "caricoeffettuato")
values (1, '09-06-2021', 1, 1);

INSERT INTO public.tarticoliacquistati ("acquistoid", "itemid", "quantitaacquistata", "prezzounitarioacquisto")
values (1, 1, 1, 25.9);

INSERT INTO public.tarticoliordinati ("asin", "amazonorderid", "itempriceamount", "itempricecurrencycode", "orderitemid", "pointsgrantedpointsmonetaryvalueamount", "pointsgrantedpointsmonetaryvaluecurrencycode", "pointsgrantedpointsnumber", "promotionids", "quantityordered", "quantityshipped", "shippingpriceamount", "shippingpricecurrencycode", "title")
values ('b07d9sb7XW', 1, 25.9, 1, 1, 0, 0,0, 0, 1, 1, 25.9, 1, 'minecraft');

INSERT INTO public.tfornitori (nome) VALUES ('Lorezo'), ('Pietro'), ('Marco'), ('Matteo');

INSERT INTO public.titems ("asin", "titolo", "categoria", "prezzo", "giacenza", "brand")
values ('B07D9SB7XW', 'Minecraft', 'Gioco', 25.9, 300, 'Mojang'),
('B07VK4QKBP', 'Manubrio', 'Sport', 199.9, 10, 'Homcom'),
('B08KSS6CLT', 'The alla pesca', 'Cibo', 1.8, 25, 'Lipton'),
('B08123PCJH', 'Aria fritta', 'Altro', 11, 12, 'Aria'),
('B07K495TYN', 'Unicorno', 'Gioco', 18.9, 25, 'Gioco spa');

INSERT INTO public.torders (buyeremail, buyername, companylegalname, earliestshipdate, fulfillmentchannel, isbusinessorder, isglobalexpressenabled, ispremiumorder, isprime, issoldbyab, lastupdatedate, latestshipdate, marketplaceid, numberofitemsshipped, numberofitemsunshipped, orderstatus, ordertype, paymentmethod, paymentmethoddetails, purchasedate, purchaseordernumber, shipmentservicelevelcategory, shippingaddresscity, shippingaddressline1, shippingaddressname, shippingcitystateorregion, shippingstateorregionpostalcode) VALUES('', '', '', '2020-01-01', 0, false, false, false, false, false, '2020-01-01', '2020-01-01', '', 0, 0, '', '', '', '', '2020-01-01', 0, 0, '', '', '', '', '');

INSERT INTO public.tusers (username, password) VALUES('admin', 'admin');





