INSERT INTO Utente (CF, NomeUtente, Password, Nome, Cognome, Sesso, DataNascita, Amministratore)
VALUES
    ('DRKPTR70M26A345S', 'Dark P.', 'Conformed', 'Peater', 'Dark', 'M', '1970-08-26', 0),
    ('RSSMRA85P12F205Z', 'Rossi M.', 'SecurePass', 'Mario', 'Rossi', 'M', '1985-09-12', 0),
    ('BNCLRA90T15C789Y', 'Bianca L.', 'Pass1234', 'Laura', 'Bianchi', 'F', '1990-12-15', 1);

INSERT INTO carrello (ID_carrello, CF_utente)
VALUES
    (1, 'DRKPTR70M26A345S'),
    (2, 'RSSMRA85P12F205Z'),
    (3, 'BNCLRA90T15C789Y');

INSERT INTO prodotto (ID_prodotto, Nome, MediaValutazione, Taglia, Descrizione, Categoria)
VALUES
    (1, 'Maglia del cazzo', 5, 'XL', 'è una maglia con un cazzo', 'Rock'),
    (2, 'Giacca di pelle', 4.5, 'L', 'Giacca nera in vera pelle', 'Rock'),
    (3, 'Stivali borchiati', 4.8, '42', 'Stivali con borchie metalliche', 'Goth');

INSERT INTO metodoPagamento (CF_Utente, NumeroCarta, DataScadenza, CVV, Tipo, Default_pagamento)
VALUES
    ('DRKPTR70M26A345S', '4539148803436467', '2025-05-01', 777, 'Prepagata', 0),
    ('RSSMRA85P12F205Z', '5312345678901234', '2026-07-15', 123, 'Credito', 1),
    ('BNCLRA90T15C789Y', '3714496353984310', '2027-09-23', 456, 'Debito', 0);

INSERT INTO ordine (ID_ordine, Data_ordine, Prezzo_tot, ID_carrello)
VALUES
    (1, '2025-03-31', 2300, 1),
    (2, '2025-04-01', 1500, 2),
    (3, '2025-04-02', 800, 3);

INSERT INTO indirizzo (CF_Utente, ID_indirizzo, città, Provincia, CAP, Via, Civico, Indirizzo2, Note, Fatturazione)
VALUES
    ('RSSMRA85P12F205Z', 2, 'Roma', 'RM', 00185, 'Via dei Fori Imperiali', '10', 'Interno 3', 'Suonare Rossi', 1),
    ('BNCLRA90T15C789Y', 3, 'Milano', 'MI', 20121, 'Corso Buenos Aires', '5', 'Scala B', 'Lasciare alla portineria', 0);

INSERT INTO indirizzo (CF_Utente, ID_indirizzo, città, Provincia, CAP, Via, Civico, Scala, Indirizzo2, Note, Fatturazione)
VALUES
    ('DRKPTR70M26A345S', 1, 'L''Aquila', 'AQ', 65342, 'Via vattelappesca', '2', 'A', 'nessuno', 'nessuna', 0);