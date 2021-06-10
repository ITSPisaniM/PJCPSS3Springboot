/***********************************************************************************************************************
                                                                    TABLE
***********************************************************************************************************************/


-- Create a new database called 'gestioneordini;'
CREATE DATABASE gestioneordini;
\connect gestioneordini;

/*items*/
create table "TItems"
(
	"ItemID" serial not null
		constraint titems_pk
			primary key,
	"ASIN" varchar,
	"Titolo" varchar,
	"Categoria" varchar,
	"Prezzo" decimal,
	"Giacenza" int,
	"Brand" varchar
);

/*fornitori*/
create table "TFornitori"
(
	"FornitoreID" serial not null
		constraint tfornitori_pk
			primary key,
	"Nome" varchar not null
);



/*users*/
create table "TUsers"
(
	"UserID" serial not null
		constraint tusers_pk
			primary key,
	"Username" varchar not null,
	"Password" varchar not null
);

/*Articoli Ordinati*/
create table "TArticoliOrdinati"
(
	"ArticoloOrdinatoID" serial not null
		constraint tarticoliscquistati_pk
			primary key,
	"ASIN" varchar,
	"AmazonOrderId" int,
"ItemPriceAmount" decimal,
"ItemPriceCurrencyCode" decimal,
"OrderItemId" int,
"PointsGrantedPointsMonetaryValueAmount" decimal,
"PointsGrantedPointsMonetaryValueCurrencyCode" decimal,
"PointsGrantedPointsNumber" decimal,
"PromotionIds" int,
"QuantityOrdered" int,
"QuantityShipped" int,
"ShippingPriceAmount" decimal,
"ShippingPriceCurrencyCode" decimal,
"Title" varchar
);

/*Acquisti*/
create table "TAcquisti"
(
    "AcquistoID"       serial not null
        constraint tacquisti_pk
            primary key,
    "FornitoreID"      integer,
    "DataFattura"      date,
    "NumeroFattura"    integer,
    "CaricoEffettuato" integer
);

/*Articoli Acquistati*/
create table "TArticoliAcquistati"
(
    "ArticoloAcquistoID"     serial not null
        constraint tsrticoliscquistati_pk
            primary key,
    "AcquistoID"             integer,
    "ITemID"                 integer,
    "QuantitaAcquistata"     integer,
    "PrezzoUnitarioAcquisto" decimal
);
/*ordini*/
create table "TOrders"
(
    "AmazonOrderId"                   serial not null
        constraint torders_pk
            primary key,
    "BuyerEmail"                      varchar,
    "BuyerName"                       varchar,
    "CompanyLegalName"                varchar,
    "EarliestShipDate"                date,
    "FulfillmentChannel"              integer,
    "IsBusinessOrder"                 boolean,
    "IsGlobalExpressEnabled"          boolean,
    "IsPremiumOrder"                  boolean,
    "IsPrime"                         boolean,
    "IsSoldByAB"                      boolean,
    "LastUpdateDate"                  date,
    "LatestShipDate"                  date,
    "MarketplaceId"                   varchar,
    "NumberOfItemsShipped"            integer,
    "NumberOfItemsUnshipped"          integer,
    "OrderStatus"                     varchar,
    "OrderType"                       varchar,
    "PaymentMethod"                   varchar,
    "PaymentMethodDetails"            varchar,
    "PurchaseDate"                    date,
    "PurchaseOrderNumber"             integer,
    "ShipmentServiceLevelCategory"    integer,
    "ShippingAddressCity"             varchar,
    "ShippingAddressLine1"            varchar,
    "ShippingAddressName"             varchar,
    "ShippingCityStateOrRegion"       varchar,
    "ShippingStateOrRegionPostalCode" varchar
);


/***********************************************************************************************************************
                                                                    INSERT
***********************************************************************************************************************/

/*items*/
insert into "TItems" ("asin", "Titolo", "Categoria", "Prezzo", "Giacenza", "Brand")
values ('B07D9SB7XW', 'Minecraft', 'Gioco', 25.9, 300, 'Mojang'),
('B07VK4QKBP', 'Manubrio', 'Sport', 199.9, 10, 'Homcom'),
('B08KSS6CLT', 'The alla pesca', 'Cibo', 1.8, 25, 'Lipton'),
('B08123PCJH', 'Aria fritta', 'Altro', 11, 12, 'Aria'),
('B07K495TYN', 'Unicorno', 'Gioco', 18.9, 25, 'Gioco spa');


/*users*/
insert into "TUsers" ("Username","Password")
values ('admin','admin');

/*fornitori*/
insert into  "TFornitori" ("Nome") values ('Lorenzo'), ('Matteo'), ('MArco');

/*TArticoliOrdinati*/
insert into "TArticoliOrdinati"("ASIN", "AmazonOrderId", "ItemPriceAmount", "ItemPriceCurrencyCode", "OrderItemId", "PointsGrantedPointsMonetaryValueAmount", "PointsGrantedPointsMonetaryValueCurrencyCode", "PointsGrantedPointsNumber", "PromotionIds", "QuantityOrdered", "QuantityShipped", "ShippingPriceAmount", "ShippingPriceCurrencyCode", "Title")
values ('B07D9SB7XW', 1, 25.9, 1, 1, 0, 0,0, 0, 1, 1, 25.9, 1, 'Minecraft');

/*TAcquisti*/
insert into "TAcquisti" ("FornitoreID", "DataFattura", "NumeroFattura", "CaricoEffettuato")
values (1, '09-06-2021', 1, 1);

/*TArticoliAcquistati*/
insert into "TArticoliAcquistati" ("AcquistoID", "ITemID", "QuantitaAcquistata", "PrezzoUnitarioAcquisto")
values (1, 1, 1, 25.9);

/*TOrders*/
insert into "TOrders" ("BuyerEmail", "BuyerName", "CompanyLegalName", "EarliestShipDate", "FulfillmentChannel",
                       "IsBusinessOrder", "IsGlobalExpressEnabled", "IsPremiumOrder", "IsPrime", "IsSoldByAB", "LastUpdateDate",
                       "LatestShipDate", "MarketplaceId", "NumberOfItemsShipped", "NumberOfItemsUnshipped", "OrderStatus",
                       "OrderType", "PaymentMethod", "PaymentMethodDetails", "PurchaseDate", "PurchaseOrderNumber",
                       "ShipmentServiceLevelCategory", "ShippingAddressCity", "ShippingAddressLine1", "ShippingAddressName",
                       "ShippingCityStateOrRegion", "ShippingStateOrRegionPostalCode")
values ('buyer@gmail.com', 'buyername', 'company', '09-06-2021', 0, false, false, false, true, false, '09-06-2021', '09-06-2021', 'marketplaceID1', 1, 1, 1, 'type',
        'credit card', 'number: 345216055451213 cvv:123', '09-06-2021', 0, 1, 'address city', 'address line1', 'address name',
        'italy', '30016');



