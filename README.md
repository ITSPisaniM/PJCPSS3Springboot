# Documentazione Project Work Gruppo 3 CPSS

## Indice
1) Descrizione
2) Organizzazione team work
3) Distribuzione
4) Visualizzazione
5) Utilizzo
6) Costruito con
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
    > TOT_QUANTITA = Q1 + Q2 + Q3 + ...
    > TOT_RICAVI = Q1 x PU1 + Q2 x PU2 + Q3 x PU3 + ...
- Quantità totali vendute e totale ricavi per items e per settimana (indipendentemente dalle categorie) GRAFICO A TORTA.
    > TOT_QUANTITA = (Q1 of Item1) + (Q2 of Item2) + (Q3 of Item3) + ...
    > TOT_RICAVI = (Q1 x PU1 of of Item1) + (Q2 x PU2 of of Item2) + (Q3 x PU3 of of Item3) + ...
- Quantità totali vendute e totale ricavi per categoria e per settimana GRAFICO A COLONNE.
    > TOT_QUANTITA = (Q1 of Category1) + (Q2 of Category2) + (Q3 of Category3) + ...
    > TOT_RICAVI = (Q1 x PU1 of ItemS of Category1) + (Q2 x PU2 of ItemS of Category2) + (Q3 x PU3 of ItemS of Category3) + ...

***
### 2) Organizzazione team work
* Il gruppo di lavoro che si è adoperato per sviluppare l'applicazione "Gestione Ordini", ha adottato la metodologia AGILE, con tecnica SCRUM.<br>
Questa organizzazione lavorativa permette di suddividere il progetto in macro-obbiettivi chiamati anche: **Milestone**<br>
Secondariamente le Milestone sono composte da goals a breve durata, solitammente non superiore a 2 giorni, chiamate **Issues** che accomunano le diverse **Tasks**.<br>
La metodologia Agile favorisce anche solo un breve periodo di sviluppi, la possibilità di presentare un MVP (Minimum Value Product), al committente.

***
### 3) Distribuzione
* L'applicazione è stata distribuita usufruendo dei servizi Azure.<br>
E' possibile contattarla a questo [indirizzo](https://lively-sky-07b13eb03.azurestaticapps.net/).<br>
Le credenziali di accesso sono:
    * Username: admin
    * Password: admin
    
***
### 4) Visualizzazione

##### Login
![login](https://user-images.githubusercontent.com/59924606/125048359-1f553880-e0a0-11eb-899f-6d95ec704833.jpg)

##### Ordini
![ordini](https://user-images.githubusercontent.com/59924606/125048404-2f6d1800-e0a0-11eb-99d5-b52a200d0778.jpg)

##### Dettaglio Ordini
![ordini dettaglio](https://user-images.githubusercontent.com/59924606/125048912-b0c4aa80-e0a0-11eb-9943-6bccb9c94b37.jpg)

##### Prodotti
![prodotti](https://user-images.githubusercontent.com/59924606/125048465-3d229d80-e0a0-11eb-8fab-6c63bc1828b8.jpg)

##### Dettaglio Prodotti
![dett prodotti](https://user-images.githubusercontent.com/59924606/125048983-c3d77a80-e0a0-11eb-9cff-0244f3988a3f.jpg)

##### Acquisti
![acquisti](https://user-images.githubusercontent.com/59924606/125048476-414ebb00-e0a0-11eb-93c2-2c24c3cd36ad.jpg)

##### Dettaglio Acquisti
![acquisti dettaglio](https://user-images.githubusercontent.com/59924606/125049016-cd60e280-e0a0-11eb-9ffe-35e73b36e18d.jpg)

##### Carrello
![carrello](https://user-images.githubusercontent.com/59924606/125048530-53c8f480-e0a0-11eb-9a8b-023ca1fb4b49.jpg)

##### Analisi
![analisi](https://user-images.githubusercontent.com/59924606/125048601-62afa700-e0a0-11eb-8dee-c0cfbf6b3184.jpg)



***
### 5) Utilizzo
L'applicazione è stata progettata per fornire sicurezza all'accesso e una confortevole permanenza.<br>
Essa fornisce svariati servizi, ma quello che vorremmo evidenziare in primo piano è l'autenticazione.

###### Autenticazione e JSON Web Token 
Ogni utente registrato, per poter utilizzare l’applicazione, deve prima accedere attraverso username e password tramite l’apposita pagina di Log in. 

Le credenziali inserite vengono quindi inoltrate al servizio che: 
* Controlla che l’utente esista e che la password sia corretta 
* Genera un JWT con una scadenza di 30 minuti in cui inserisce i dati dell’utente stesso (come ad esempio l’username e il livello di autorizzazione in caso sia presente) 
* Inserisce nell’header della risposta il token appena generato 
* Risponde alla richiesta di accesso fornendo il token 

Una volta che l’utente risulta loggato, dispone quindi di un token dalla durata di 30 minuti. 
Ogni qual volta che una richiesta viene fatta (ad esempio per recuperare la lista degli ultimi acquisti) il token viene incapsulato nell’header della richiesta, il servizio a backend a quel punto: 
* Si assicura che il token sia valido e che questo non sia scaduto  
* Verifica che il token non sia prossimo alla scadenza, in quel caso, ne genera uno nuovo e lo inserisce all’interno dell’header di risposta 
* Inoltra la richiesta al service che si occupa di soddisfare la stessa 
* Restituisce il risultato 
Nel caso l’utente non effettuasse operazioni nell’arco dei 30 minuti di validità del token, questo scadrebbe invalidando la sessione.

<br>
L'applicazione dispone anche di altri servizi, suddivisi per Oggetto, quali:

#### Utenti
> https://projectworkpcss.azurewebsites.net/api/utente

> /login
> * Output --> Token di autenticazione univoco che garantisce l'accesso per una sessione di 30 min.

#### Ordini
> https://projectworkpcss.azurewebsites.net/api/ordini

> /ping
> * Output --> Stringa di avvenuto successo. Backend Raggiungibile.

> /insertAPI
> * Output --> Servizio che si occupa di ricevere gli ordini dei clienti e inserirli a DB.

> /page?page={0}&size={10}&sort={purchaseDate},{DESC}
> * Output --> Servizio che fornisce i dati al Client secondo metodo di paginazione.

> /list
> * Output --> Servizio che fornisce una lista di dati non paginata.

> /{id}
> * Output --> Servizio che recupera un record in base all'**id** fornito.

> ?page={0}&size={10}&sort={purchaseDate},{DESC}&amazonOrderId={B07D9SB7XW}&buyerEmail={aaa@gmail.com}&purchaseDate={2021-06-02}
> * Output -->  Servizio che restituisce dei record precisi in base ai dati che gli vengono forniti. Dotato di paginazione.

#### OrdersItems
> https://projectworkpcss.azurewebsites.net/api/ordiniProdotti

> /analytics?StartDate={2021-06-02}&ItemAsin={B07D9SB7XW}
> * Output --> Servizio che si occupa di fornire dati statistici secondo dei parametri forniti dal Client.

#### Prodotti
> https://projectworkpcss.azurewebsites.net/api/prodotti

> /ping
> * Output --> Stringa di avvenuto successo. Backend Raggiungibile.

> /page?page={0}&size={10}&sort={purchaseDate},{DESC}
> * Output --> Servizio che fornisce i dati al Client secondo metodo di paginazione.

> /list
> * Output --> Servizio che fornisce una lista di dati non paginata.

> /{id}
> * Output --> Servizio che recupera un record in base all'**id** fornito.

> /save
> * Output --> Servizio che si occupa di salvare un preciso record nel DB, passando dei dati.

> /count
> * Output --> Servizio che restituisce un numero. Conta quanti prodotti sono presenti nel DB.

#### Acquisti
> https://projectworkpcss.azurewebsites.net/api/acquisti

> /ping
> * Output --> Stringa di avvenuto successo. Backend Raggiungibile.

> /page?page={0}&size={10}&sort={purchaseDate},{DESC}
> * Output --> Servizio che fornisce i dati al Client secondo metodo di paginazione.

> /list
> * Output --> Servizio che fornisce una lista di dati non paginata.

> /{id}
> * Output --> Servizio che recupera un record in base all'**id** fornito.

> /save
> * Output --> Servizio che si occupa di salvare un preciso record nel DB, passando dei dati.

> /update/{id}
> * Output --> Servizio che si occupa di aggiornare un record preciso (con l'**id**) nel DB, passando un oggetto. 

> /delete/{id}
> * Output --> Servizio che si occupa di eliminare un record preciso (con l'**id**) nel DB.

***
### 6) Costruito con
L'applicazione "Gestione Entrate" è stata sviluppata seguendo il pattern architetturale MVC, (Modern View Controller).
L'architettura è così formata da 3 parti:
* Client Frontend
* Interfaccia Backend
* Database
<br>

I linguaggi che sono stati impiegati per la realizzazione del __Front-end__ sono:<br /><br />

LINGUAGGI:
* HTML
* TypeScript
* SCSS

FRAMEWORK:
* Angular (framework di Google)

LIBRERIE:
* Tailwind
* Angular Material
* Chart.js

Mentre i linguaggi con cui è stato implementato il **Back-end** sono Java e il framework Spring Boot.<br />
Una caratteristica molto importante del framework Spring Boot, è la presenza delle così dette, Annotation, che diminuiscono la verbosità del codice e fanno risparmiare tempo agli sviluppatori, integrando numerose funzioni, molto utili alla stesura del codice.<br />
Oltre a Java e Spring Boot, è stata impiegato in massiccio modo l'interfaccia JPA del framework Spring Boot.<br />
Questa interfaccia, si occupa di svolgere numerosi metodi per estrarre dati dal DB, senza che l'utente programmatore si debba preoccupare di scrivere la query di interrogazione.
<br />

Il **Database** ospita PostgreSQL.<br>
In una fase iniziale dello sviluppo, il team ha preso la decisione di creare uno script Docker per predisporre il database in locale.<br>
Sviluppi successivi e stanziamento di risorse hanno permesso l'utilizzo di un database comune, sfruttando il software open source Jenkins in una macchina remota.

***
### 7) Autori

> * **Capparelli Pietro** Backend Developer Architecture Specialist

> * **Pisani Marco** Frontend Develop UI Designer

> * **Savian Lorenzo** Frontend Developer UX Designer

> * **Solivo Matteo** Backend Developer Project Manager (Scrum Master)
