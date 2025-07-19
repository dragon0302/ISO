drop schema  ISO16;
create schema ISO16;

CREATE TABLE Utente (
    CF CHAR(16) PRIMARY KEY NOT NULL check ( char_length(CF) = 16 ),
    NomeUtente VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Salt VARCHAR(255) NOT NULL ,
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL ,
    Sesso CHAR(1) NOT NULL CHECK (Sesso IN ('M', 'F')),
    DataNascita DATE NOT NULL,
    Amministratore TINYINT(1) DEFAULT 0 CHECK (Amministratore IN (0,1))
);

CREATE TABLE carrello(
     ID_carrello int primary key not null auto_increment,
     Lista_prodotti varchar(500),
     CF_utente CHAR(16),
     foreign key (CF_utente) references Utente (CF) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE prodotto(
     ID_prodotto INTEGER PRIMARY KEY NOT NULL auto_increment,
     Nome VARCHAR(20) NOT NULL,
     Taglia VARCHAR(3) NOT NULL,
     Descrizione VARCHAR(500) NOT NULL,
     Categoria VARCHAR(300) NOT NULL,
     Prezzo DOUBLE NOT NULL,
     Iva Integer not null ,
     DataInserimento DATE not null default (curdate())
);

CREATE TABLE acquisto(
    ID_acquisto integer primary key not null auto_increment,
    CodiceSconto TINYINT(1) DEFAULT 0 CHECK (CodiceSconto IN (0,1)),
    Quantita INTEGER NOT NULL ,
    ID_Carello INTEGER NOT NULL ,
    ID_Prodotto INTEGER NOT NULL ,
    FOREIGN KEY (ID_Carello) references carrello(ID_carrello) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (ID_Prodotto) references prodotto(ID_prodotto) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE metodoPagamento(
    NumeroCarta VARCHAR(16) PRIMARY KEY NOT NULL,
    DataScadenza date NOT NULL,
    CVV INT NOT NULL check ( CVV >= 100 && CVV < 1000 ),
    Tipo VARCHAR(20) NOT NULL,
    Default_pagamento BOOLEAN NOT NULL,
    CF_utente CHAR(16),
    foreign key (CF_utente) references Utente (CF) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ordine(
   ID_ordine integer  primary key NOT NULL auto_increment,
   Data_ordine DATE not null,
   Prezzo_tot float not null check ( Prezzo_tot > 0 ),
   Lista_prodotti varchar(500),
   ID_carrello int,
   foreign key (ID_carrello) references carrello (ID_carrello) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE indirizzo (
   ID_indirizzo INT PRIMARY KEY NOT NULL auto_increment,
   città VARCHAR(50) NOT NULL,
   Provincia VARCHAR(50) NOT NULL,
   CAP CHAR(5) NOT NULL check ( char_length(CAP) = 5 ),
   Via VARCHAR(100) NOT NULL,
   Civico int NOT NULL check ( Civico > 0 ),
   Scala char(1) check ( char_length(Scala) = 1 ),
   Indirizzo2 VARCHAR(100) NULL,
   Note VARCHAR(255) NULL,
   Fatturazione TINYINT(1) DEFAULT 0 NOT NULL,
   CF_utente CHAR(16),
   foreign key (CF_utente) references Utente (CF) ON DELETE CASCADE ON UPDATE CASCADE
);