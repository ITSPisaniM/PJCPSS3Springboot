# Documentazione Project Work Gruppo 3 CPSS

## Indice
1) Descrizione
2) Come iniziare
3) Distribuzione
4) Utilizzo
5) Costruito con
6) TODO
7) Autori

***

### 1) Descrizione

- Una Azienda acquista degli item (TItems) da vari fornitori (TFornitori)  che poi vende esclusivamente tramite Amazon
- Scopo dell’applicazione è aggiornare la giacenza degli item  in base ai dati delle vendite fornite da una simulazione della  Amazon Selling Partner API e in base agli acquisti fatti dai fornitori
- Accesso tramite Username/password (senza registrazione) (TUsers): solo gli utenti autorizzati potranno utilizzare l’applicazione
- Creare una procedura di importazione dei dati da una api che simula la Amazon Selling Partner API
  - Importazione  in un database (TOrders) degli orders effettuando una opportuna chiamata GET alla api  (vi verrà fornito l’ endpoint corretto che simula una chiamata alla Amazon Selling Partner API reale).
  - Importazione (nella TOrderItems)  degli item legati ad ogni order importato nel punto precedente  effettuando una opportuna chiamata GET alla api  (vi verrà fornito l’ endpoint corretto che simula una chiamata alla Amazon Selling Partner API reale).
  - La procedura di importazione deve tenere traccia degli order  (e relativi orderItems ) già importati nel database al fine di evitare importazioni multiple
  - Implementazione dello scarico della giacenza degli item venduti su Amazon.
- Creare una procedura di caricamento manuale degli acquisti (TAcquisti e TAcquistiItems) e implementare il carico della giacenza di magazzino per ogni Acquisto.
- L’applicazione permetterà di visualizzare/modificare  tutti i dati disponibili nel database-
- Implementare una pagina “Analisi Vendite” che fornisce delle analisi numeriche e grafiche sulle vendite su Amazon (simulando una visualizzazione semplificata stile PowerBI o Qlik Sense permettendo all’utente  di effettuare delle analisi  in una settimana specifica o tutte le settimane).
  - Quantità totali vendute e totale ricavi per settimana (indipendentemente dagli items e dalle categorie) GRAFICO A LINEE.
    * TOT_QUANTITA = Q1 + Q2 + Q3 + ...
    * TOT_RICAVI = Q1 x PU1 + Q2 x PU2 + Q3 x PU3 + ...
  - Quantità totali vendute e totale ricavi per items e per settimana (indipendentemente dalle categorie) GRAFICO A TORTA.
    * TOT_QUANTITA = (Q1 of Item1) + (Q2 of Item2) + (Q3 of Item3) + ...
    * TOT_RICAVI = (Q1 x PU1 of of Item1) + (Q2 x PU2 of of Item2) + (Q3 x PU3 of of Item3) + ...
  - Quantità totali vendute e totale ricavi per categoria e per settimana GRAFICO A COLONNE.
    * TOT_QUANTITA = (Q1 of Category1) + (Q2 of Category2) + (Q3 of Category3) + ...
    * TOT_RICAVI = (Q1 x PU1 of ItemS of Category1) + (Q2 x PU2 of ItemS of Category2) + (Q3 x PU3 of ItemS of Category3) + ...


***
### 2) Come iniziare


***
### 3) Distribuzione


***
### 4) Utilizzo


***
### 5) Costruito con
L'applicazione "Gestione Entrate" è stata sviluppata seguendo il pattern architetturale MVC, (Modern View Controller)

***
### 6) TODO


***
### 7) Autori

> * **Capparelli Pietro** 

> * **Pisani Marco**

> * **Savian Lorenzo**

> * **Solivo Matteo**





















