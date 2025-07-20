INSERT INTO Utente (CF, NomeUtente, Password,Salt, Nome, Cognome ,Email, Sesso, DataNascita, Amministratore)
VALUES
    ('DRKPTR70M26A345S', 'Nicola', 'cYX5/07EyQgA8Ah63IN2/dmpsnMt4e8QturmmZ4/RPM=' , 'Q2584p0qUxGwarulk9VK5w==', 'Peater', 'Dark',  'c@r.v' ,'M', '1970-08-26', 0),
    ('RSSMRA85P12F205Z', 'Alessandro', 'SbcL99MLP6oiZT3GuaxW0gNF1Ntx7hmnpl+2hMso74Y=','31zUUg1WfxESIYvFeOyfGg==', 'Mario', 'Rossi', 't@r.v' ,'M', '1985-09-12', 0),
    ('BNCLRA90T15C789Y', 'Alessia', 'CXC/y4lVzBmAv9tlQ8k74Q4K4vv8AqKvtp5N2FMgW/w=', 'j9lbC8Xm0ejsrhSmDX8vmw==', 'Laura', 'Bianchi', 's@r.v', 'F', '1990-12-15', 1);

INSERT INTO carrello (CF_utente)
VALUES
    ( 'DRKPTR70M26A345S'),
    ( 'RSSMRA85P12F205Z'),
    ( 'BNCLRA90T15C789Y');

INSERT INTO prodotto (Nome, Taglia, Descrizione, Categoria, Prezzo, iva)
VALUES
    ( 'Maglia dei Nirvana', 'XL', 'è una maglia dei Nirvana serve davvero altro', 'Rock', 12.5, 22),
    ( 'Giacca di pelle', 'L', 'Giacca nera in vera pelle', 'Rock', 90, 22),
    ( 'Stivali borchiati', '42', 'Stivali con borchie metalliche', 'Goth', 72.72, 22);

INSERT INTO indirizzo (CF_Utente, città, Provincia, CAP, Via, Civico, Indirizzo2, Note, Fatturazione)
VALUES
    ('RSSMRA85P12F205Z', 'Roma', 'RM', '00185', 'Via dei Fori Imperiali', '10', 'Interno 3', 'Suonare Rossi', 1),
    ('BNCLRA90T15C789Y', 'Milano', 'MI', '20121', 'Corso Buenos Aires', '5', 'Scala B', 'Lasciare alla portineria', 0);

INSERT INTO indirizzo (CF_Utente, città, Provincia, CAP, Via, Civico, Scala, Indirizzo2, Note, Fatturazione)
VALUES
    ('DRKPTR70M26A345S', 'L''Aquila', 'AQ', 65342, 'Via vattelappesca', '2', 'A', 'nessuno', 'nessuna', 0);


INSERT INTO ordine (Data_ordine, Prezzo_tot, ID_carrello,ID_indirizzo,NumeroCarta)
VALUES
    ( '2025-03-31', 2300, 1,1,4539148803436467),
    ( '2025-04-01', 1500, 2,2,5312345678901234),
    ( '2025-04-02', 800, 3,3,5312345678901234);

INSERT INTO acquisto( CodiceSconto, Quantita, ID_Carello, ID_Prodotto)
VALUES
    (1,2,1,1),
    (0,10,2,2);

INSERT INTO acquisto( Quantita, ID_Carello, ID_Prodotto)
VALUES
    (1,3,3);