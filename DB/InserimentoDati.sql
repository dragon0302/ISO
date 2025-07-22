INSERT INTO utente (CF, NomeUtente, Password, Nome, Cognome, Mail, Sesso, DataNascita, Amministratore)
VALUES (?,?,?,?,?,?,?,?,?);

INSERT INTO carrello (ID_carrello, CF_utente)
VALUES (?,?);

INSERT INTO prodotto (ID_prodotto, Nome, Taglia, Descrizione, Categoria)
VALUES (?,?,?,?,?,?);

INSERT INTO ordine (ID_ordine, Data_ordine, Prezzo_tot, ID_carrello)
VALUES (?,?,?,?);

INSERT INTO indirizzo (CF_Utente, ID_indirizzo, città, Provincia, CAP, Via, Civico, Scala, Indirizzo2, Note, Fatturazione)
VALUES (?,?,?,?,?,?,?,?,?,?,?);

INSERT INTO acquisto (ID_acquisto, CodiceSconto, Quantita, ID_Carello, ID_Prodotto)
VALUES (?,?,?,?,?);