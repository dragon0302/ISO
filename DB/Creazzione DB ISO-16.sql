drop schema  ISO16;
create schema ISO16;

create table carello(
    ID_carello int  primary key not null
);

CREATE TABLE prodotto(
    ID INTEGER PRIMARY KEY NOT NULL,
    Nome CHAR(20) NOT NULL,
    MediaValutazione DOUBLE NOT NULL,
    Taglia CHAR(3) NOT NULL,
    Descrizione CHAR NOT NULL,
    Categoria CHAR NOT NULL
);

create TABLE metodoPagamento(
    NumeroCarta DOUBLE PRIMARY KEY NOT NULL,
    DataScadenza CHAR(5) NOT NULL,
    CVV DOUBLE NOT NULL,
    Tipo CHAR NOT NULL,
    Default_pagamento BOOLEAN NOT NULL
);

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

create table ordine(
    ID_ordine integer  primary key NOT NULL,
    Data_ordine DATE not null,
    Prezzo_tot float not null
);

CREATE TABLE indirizzo (
       ID_indirizzo INT PRIMARY KEY NOT NULL,
       città VARCHAR(50) NOT NULL,
       Provincia VARCHAR(50) NOT NULL,
       CAP VARCHAR(5) NOT NULL,
       Via VARCHAR(100) NOT NULL,
       Civico VARCHAR(10) NOT NULL,
       Indirizzo2 VARCHAR(100) NULL,
       Note VARCHAR(255) NULL,
       Fatturazione TINYINT(1) NOT NULL
);
