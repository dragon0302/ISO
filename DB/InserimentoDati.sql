INSERT INTO Utente (CF, NomeUtente, Password, Nome, Cognome, Sesso, DataNascita, Amministratore)
VALUES (?,?,?,?,?,?,?,?);

INSERT INTO carrello (ID_carrello, CF_utente)
VALUES (?,?);

INSERT INTO prodotto (ID_prodotto, Nome, MediaValutazione, Taglia, Descrizione, Categoria)
VALUES (?,?,?,?,?,?);

INSERT INTO metodoPagamento (CF_Utente, NumeroCarta, DataScadenza, CVV, Tipo, Default_pagamento)
VALUES (?,?,?,?,?,?);

INSERT INTO ordine (ID_ordine, Data_ordine, Prezzo_tot, ID_carrello)
VALUES (?,?,?,?);

INSERT INTO indirizzo (CF_Utente, ID_indirizzo, città, Provincia, CAP, Via, Civico, Indirizzo2, Note, Fatturazione)
VALUES (?,?,?,?,?,?,?,?,?,?);

INSERT INTO indirizzo (CF_Utente, ID_indirizzo, città, Provincia, CAP, Via, Civico, Scala, Indirizzo2, Note, Fatturazione)
VALUES (?,?,?,?,?,?,?,?,?,?,?);