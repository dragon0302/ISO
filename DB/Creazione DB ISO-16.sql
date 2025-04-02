drop schema  ISO16;
create schema ISO16;

CREATE TABLE Utente (
                        CF CHAR(16) PRIMARY KEY NOT NULL,
                        NomeUtente VARCHAR(50) NOT NULL,
                        Password VARCHAR(255) NOT NULL,
                        Nome VARCHAR(50) NOT NULL,
                        Cognome VARCHAR(50) NOT NULL,
                        Sesso CHAR(1) NOT NULL CHECK (Sesso IN ('M', 'F')),
                        DataNascita DATE NOT NULL,
                        Amministratore TINYINT(1) DEFAULT 0 CHECK (Amministratore IN (0,1))
);

CREATE TABLE carrello(
                         ID_carrello int  primary key not null check ( ID_carrello > 0 ),
                         CF_utente CHAR(16),
                         foreign key (CF_utente) references Utente (CF)
);

CREATE TABLE prodotto(
                         ID_prodotto INTEGER PRIMARY KEY NOT NULL check ( ID_prodotto > 0 ),
                         Nome CHAR(20) NOT NULL,
                         MediaValutazione DOUBLE NOT NULL check ( MediaValutazione >= 0 && MediaValutazione <= 10 ),
                         Taglia CHAR(3) NOT NULL,
                         Descrizione VARCHAR(500) NOT NULL,
                         Categoria VARCHAR(20) NOT NULL,
                         Prezzo DOUBLE NOT NULL
);

CREATE TABLE metodoPagamento(
    NumeroCarta DOUBLE PRIMARY KEY NOT NULL,
    DataScadenza date NOT NULL,
    CVV INT NOT NULL check ( CVV >= 100 && CVV < 1000 ),
    Tipo CHAR(20) NOT NULL,
    Default_pagamento BOOLEAN NOT NULL,
    CF_utente CHAR(16),
    foreign key (CF_utente) references Utente (CF)
);

CREATE TABLE ordine(
                       ID_ordine integer  primary key NOT NULL check ( ID_ordine > 0 ),
                       Data_ordine DATE not null,
                       Prezzo_tot float not null check ( Prezzo_tot > 0 ),
                       Lista_prodotti varchar(500),
                       ID_carrello int,
                       foreign key (ID_carrello) references carrello (ID_carrello)
);

CREATE TABLE indirizzo (
                           ID_indirizzo INT PRIMARY KEY NOT NULL check ( ID_indirizzo > 0 ),
                           città VARCHAR(50) NOT NULL,
                           Provincia VARCHAR(50) NOT NULL,
                           CAP CHAR(5) NOT NULL,
                           Via VARCHAR(100) NOT NULL,
                           Civico int NOT NULL check ( Civico > 0 ),
                           Scala char(1),
                           Indirizzo2 VARCHAR(100) NULL,
                           Note VARCHAR(255) NULL,
                           Fatturazione TINYINT(1) DEFAULT 0 NOT NULL,
                           CF_utente CHAR(16),
                           foreign key (CF_utente) references Utente (CF)
);