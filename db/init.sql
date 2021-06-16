/***********************************************************************************************************************
                                                                    tabLe
***********************************************************************************************************************/


-- create a new database called 'gestioneordini;'
cReate database gestioneordini;
\connect gestioneordini;

/*items*/
create table "titems"
(
	"itemid" serial not null
		constraint titems_pk
			primary key,
	"asin" varchar,
	"titolo" varchar,
	"categoria" varchar,
	"prezzo" decimal,
	"giacenza" int,
	"brand" varchar
);

/*fornitori*/
create table "tfornitori"
(
	"fornitoreid" serial not null
		constraint tfornitori_pk
			primary key,
	"nome" varchar not null
);



/*users*/
create table "tusers"
(
	"userid" serial not null
		constraint tusers_pk
			primary key,
	"username" varchar not null,
	"password" varchar not null
);

/*articoli ordinati*/
create table "tarticoliordinati"
(
	"articoloordinatoid" serial not null
		constraint tarticoliscquistati_pk
			primary key,
	"asin" varchar,
	"amazonorderid" int,
"itempriceamount" decimal,
"itempricecurrencycode" decimal,
"orderitemid" int,
"pointsgrantedpointsMonetaryValueamount" decimal,
"pointsgrantedpointsMonetaryValuecurrencycode" decimal,
"pointsgrantedpointsnumber" decimal,
"promotionids" int,
"quantityordered" int,
"quantityshipped" int,
"shippingpriceamount" decimal,
"shippingpricecurrencycode" decimal,
"title" varchar
);

/*acquisti*/
create table "tacquisti"
(
    "acquistoid"       serial not null
        constraint tacquisti_pk
            primary key,
    "fornitoreid"      integer,
    "datafattura"      date,
    "numerofattura"    integer,
    "caricoeffettuato" integer
);

/*articoli acquistati*/
create table "tarticoliacquistati"
(
    "articoloacquistoid"     serial not null
        constraint tsrticoliscquistati_pk
            primary key,
    "acquistoid"             integer,
    "itemid"                 integer,
    "quantitaacquistata"     integer,
    "prezzounitarioacquisto" decimal
);
/*ordini*/
create table "torders"
(
    "amazonorderid"                   serial not null
        constraint torders_pk
            primary key,
    "buyeremail"                      varchar,
    "buyername"                       varchar,
    "companyLegalname"                varchar,
    "earliestshipdate"                date,
    "fulfillmentchannel"              integer,
    "isbusinessorder"                 boolean,
    "isglobalexpressenabled"          boolean,
    "ispremiumorder"                  boolean,
    "isprime"                         boolean,
    "issoldbyab"                      boolean,
    "Lastupdatedate"                  date,
    "Latestshipdate"                  date,
    "Marketplaceid"                   varchar,
    "numberofitemsshipped"            integer,
    "numberofitemsunshipped"          integer,
    "orderstatus"                     varchar,
    "ordertype"                       varchar,
    "paymentMethod"                   varchar,
    "paymentMethoddetails"            varchar,
    "purchasedate"                    date,
    "purchaseordernumber"             integer,
    "shipmentserviceLevelcategory"    integer,
    "shippingaddresscity"             varchar,
    "shippingaddressLine1"            varchar,
    "shippingaddressname"             varchar,
    "shippingcitystateorRegion"       varchar,
    "shippingstateorRegionpostalcode" varchar
);


/***********************************************************************************************************************
                                                                    inseRt

***********************************************************************************************************************/


/*items*/
insert into "titems" ("asin", "titolo", "categoria", "prezzo", "giacenza", "brand")
values ('B07D9SB7XW', 'Minecraft', 'Gioco', 25.9, 300, 'Mojang'),
('B07VK4QKBP', 'Manubrio', 'Sport', 199.9, 10, 'Homcom'),
('B08KSS6CLT', 'The alla pesca', 'Cibo', 1.8, 25, 'Lipton'),
('B08123PCJH', 'Aria fritta', 'Altro', 11, 12, 'Aria'),
('B07K495TYN', 'Unicorno', 'Gioco', 18.9, 25, 'Gioco spa');


/*users*/
insert into "tusers" ("username","password")
values ('admin','admin');

/*fornitori*/
insert into  "tfornitori" ("nome") values ('Lorenzo'), ('Matteo'), ('MArco');

/*TArticoliOrdinati*/
insert into "tarticoliordinati"("asin", "amazonorderid", "itempriceamount", "itempricecurrencycode", "orderitemid", "pointsgrantedpointsmonetaryvalueamount", "pointsgrantedpointsmonetaryvaluecurrencycode", "pointsgrantedpointsnumber", "promotionids", "quantityordered", "quantityshipped", "shippingpriceamount", "shippingpricecurrencycode", "title")
values ('B07D9SB7XW', 1, 25.9, 1, 1, 0, 0,0, 0, 1, 1, 25.9, 1, 'Minecraft');

/*TAcquisti*/
insert into "tacquisti" ("fornitoreid", "datafattura", "numerofattura", "caricoeffettuato")
values (1, '09-06-2021', 1, 1);

/*TArticoliAcquistati*/
insert into "tarticoliacquistati" ("acquistoid", "itemid", "quantitaacquistata", "prezzounitarioacquisto")
values (1, 1, 1, 25.9);

/*TOrders*/
insert into "torders" ("buyeremail", "buyername", "companylegalname", "earliestshipdate", "fulfillmentchannel",
                       "isbusinessorder", "isglobalexpressenabled", "ispremiumorder", "isprime", "issoldbyab", "lastupdatedate",
                       "latestshipdate", "marketplaceid", "numberofitemsshipped", "numberofitemsunshipped", "orderstatus",
                       "ordertype", "paymentmethod", "paymentmethoddetails", "purchasedate", "purchaseordernumber",
                       "shipmentservicelevelcategory", "shippingaddresscity", "shippingaddressline1", "shippingaddressname",
                       "shippingcitystateorRegion", "shippingstateorRegionpostalcode")
values ('buyer@gmail.com', 'buyername', 'company', '09-06-2021', 0, false, false, false, true, false, '09-06-2021', '09-06-2021', 'marketplaceID1', 1, 1, 1, 'type',
        'credit card', 'number: 345216055451213 cvv:123', '09-06-2021', 0, 1, 'address city', 'address line1', 'address name',
        'italy', '30016');