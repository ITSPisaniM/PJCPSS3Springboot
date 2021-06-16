/*items*/
insert into "titems" ("asin", "titolo", "categoria", "prezzo", "giacenza", "brand")
values ('b07d9sb7XW', 'minecraft', 'gioco', 25.9, 300, 'mojang'),
('b07vK4qKbp', 'manubrio', 'sport', 199.9, 10, 'Homcom'),
('b08Kss6clt', 'the alla pesca', 'cibo', 1.8, 25, 'lipton'),
('b08123pcJH', 'aria fritta', 'altro', 11, 12, 'aria'),
('b07K495tYn', 'unicorno', 'gioco', 18.9, 25, 'gioco spa');


/*users*/
insert into "tusers" ("username","password")
values ('admin','admin');

/*fornitori*/
insert into  "tfornitori" ("nome") values ('lorenzo'), ('matteo'), ('marco');

/*tarticoliordinati*/
insert into "tarticoliordinati"("asin", "amazonorderid", "itempriceamount", "itempricecurrencycode", "orderitemid", "pointsgrantedpointsmonetaryvalueamount", "pointsgrantedpointsmonetaryvaluecurrencycode", "pointsgrantedpointsnumber", "promotionids", "quantityordered", "quantityshipped", "shippingpriceamount", "shippingpricecurrencycode", "title")
values ('b07d9sb7XW', 1, 25.9, 1, 1, 0, 0,0, 0, 1, 1, 25.9, 1, 'minecraft');

/*tacquisti*/
insert into "tacquisti" ("fornitoreid", "datafattura", "numerofattura", "caricoeffettuato")
values (1, '09-06-2021', 1, 1);

/*tarticoliacquistati*/
insert into "tarticoliacquistati" ("acquistoid", "itemid", "quantitaacquistata", "prezzounitarioacquisto")
values (1, 1, 1, 25.9);

/*torders*/
insert into "torders" ("buyeremail", "buyername", "companylegalname", "earliestshipdate", "fulfillmentchannel",
                       "isbusinessorder", "isglobalexpressenabled", "ispremiumorder", "isprime", "issoldbyab", "lastupdatedate",
                       "latestshipdate", "marketplaceid", "numberofitemsshipped", "numberofitemsunshipped", "orderstatus",
                       "ordertype", "paymentmethod", "paymentmethoddetails", "purchasedate", "purchaseordernumber",
                       "shipmentservicelevelcategory", "shippingaddresscity", "shippingaddressline1", "shippingaddressname",
                       "shippingcitystateorRegion", "shippingstateorRegionpostalcode")
values ('buyer@gmail.com', 'buyername', 'company', '09-06-2021', 0, false, false, false, true, false, '09-06-2021', '09-06-2021', 'marketplaceid1', 1, 1, 1, 'type',
        'credit card', 'number: 345216055451213 cvv:123', '09-06-2021', 0, 1, 'address city', 'address line1', 'address name',
        'italy', '30016');