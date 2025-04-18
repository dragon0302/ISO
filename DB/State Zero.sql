INSERT INTO Utente (CF, NomeUtente, Password,Salt, Nome, Cognome ,Email, Sesso, DataNascita, Amministratore)
VALUES
    ('DRKPTR70M26A345S', 'Dark P.', '7Hj4Fz3pXk2vPq9RtYwG8nO1mK5sAe2BbVcD6fJhIiUlNpLoQxZyX7C0W9Ed4Ma' , 'LxQ9vL2k8tR7wY1fZbN+cA==', 'Peater', 'Dark',  'c@r.v' ,'M', '1970-08-26', 0),
    ('RSSMRA85P12F205Z', 'Rossi M.', '5TgY8uHj1mK9oLpXwE4sN2bVcQ7zR6fJhGtD3kPnAqSrMvWxZy0CdF4Ii9Ue2BaL','pG3hMqW5sVtBvN7kYr2x+Dw==', 'Mario', 'Rossi', 't@r.v' ,'M', '1985-09-12', 0),
    ('BNCLRA90T15C789Y', 'Bianca L.', '4NkO9pL2mJ8hG1fD7sE5qW3xT0yV6bU2cA4zX7iHrKtYwMvP5lQ6nB9gFdSe1Ij', '9fR2tK8yXp1wL3vQ7mZ4sDg==', 'Laura', 'Bianchi', 's@r.v', 'F', '1990-12-15', 1);

INSERT INTO carrello (CF_utente)
VALUES
    ( 'DRKPTR70M26A345S'),
    ( 'RSSMRA85P12F205Z'),
    ( 'BNCLRA90T15C789Y');

INSERT INTO prodotto (Nome, MediaValutazione, Taglia, Descrizione, Categoria, Prezzo)
VALUES
    ( 'Maglia del cazzo', 5, 'XL', 'è una maglia con un cazzo', 'Rock', 12.5),
    ( 'Giacca di pelle', 4.5, 'L', 'Giacca nera in vera pelle', 'Rock', 90),
    ( 'Stivali borchiati', 4.8, '42', 'Stivali con borchie metalliche', 'Goth', 72.72);

INSERT INTO metodoPagamento (CF_Utente, NumeroCarta, DataScadenza, CVV, Tipo, Default_pagamento)
VALUES
    ('DRKPTR70M26A345S', '4539148803436467', '2025-05-01', 777, 'Prepagata', 0),
    ('RSSMRA85P12F205Z', '5312345678901234', '2026-07-15', 123, 'Credito', 1),
    ('BNCLRA90T15C789Y', '3714496353984310', '2027-09-23', 456, 'Debito', 0);

INSERT INTO ordine (Data_ordine, Prezzo_tot, ID_carrello)
VALUES
    ( '2025-03-31', 2300, 1),
    ( '2025-04-01', 1500, 2),
    ( '2025-04-02', 800, 3);

INSERT INTO indirizzo (CF_Utente, città, Provincia, CAP, Via, Civico, Indirizzo2, Note, Fatturazione)
VALUES
    ('RSSMRA85P12F205Z', 'Roma', 'RM', '00185', 'Via dei Fori Imperiali', '10', 'Interno 3', 'Suonare Rossi', 1),
    ('BNCLRA90T15C789Y', 'Milano', 'MI', '20121', 'Corso Buenos Aires', '5', 'Scala B', 'Lasciare alla portineria', 0);

INSERT INTO indirizzo (CF_Utente, città, Provincia, CAP, Via, Civico, Scala, Indirizzo2, Note, Fatturazione)
VALUES
    ('DRKPTR70M26A345S', 'L''Aquila', 'AQ', 65342, 'Via vattelappesca', '2', 'A', 'nessuno', 'nessuna', 0);
INSERT INTO acquisto( CodiceSconto, Quantità, ID_Carello, ID_Prodotto)
VALUES
    (1,2,1,1),
    (0,10,2,2);

INSERT INTO acquisto( Quantità, ID_Carello, ID_Prodotto)
VALUES
    (1,3,3);