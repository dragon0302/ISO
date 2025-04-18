<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sig-up</title>
        <style>
            /* Stile generale della pagina */
            body {
                font-family: Arial, sans-serif;
                background-color: #374A67; /* Sfondo nero */
                color: white; /* Testo bianco per contrasto */
                display: flex;
                justify-content: center;
                align-items: flex-start; /* Modificato per allineare il contenuto in alto */
                min-height: 100vh;
                margin: 0;
                padding-top: 70px; /* Spazio tra il contenuto e la barra superiore */
                padding-bottom: 70px; /* Spazio tra il contenuto e la barra inferiore */
                flex-direction: column;
            }

            /* Stile errore */
            .field-error {
                color: red;
                background-color: white;
                padding: 5px 10px;
                border-radius: 4px;
                font-size: 13px;
                margin-top: 5px;
            }

            /* Stile della barra superiore con logo e barra di ricerca */
            .header-bar {
                width: 100%;
                background-color: #444; /* Colore di sfondo per il header */
                padding: 15px 0;
                display: flex;
                align-items: center;
                justify-content: space-between;
                position: fixed; /* Fissiamo la barra superiore */
                top: 0;
                left: 0;
                padding: 10px 20px;
                z-index: 1000; /* Mantiene la barra sopra il contenuto */
            }

            /* Stile del logo */
            .logo-container img {
                width: 100px;
                height: auto;
            }

            /* Stile della barra di ricerca */
            .search-bar input[type="text"] {
                padding: 8px;
                font-size: 14px;
                border: 1px solid #ccc;
                border-radius: 4px;
                width: 200px;
            }

            .search-bar input[type="submit"] {
                padding: 8px;
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
                border-radius: 4px;
            }

            .search-bar input[type="submit"]:hover {
                background-color: #45a049;
            }

            /* Contenitore centrale che include immagini laterali e il form di inserimento */
            .content-wrapper {
                display: flex;
                align-items: flex-start;
                justify-content: center;
                gap: 50px; /* Distanza tra le immagini laterali e il form di inserimento */
                margin-top: 100px; /* Distanza tra la barra superiore fissa e il contenuto */
                width: 90%;
                padding: 0 20px; /* Spazio ai lati del contenuto */
            }

            /* Stile delle immagini laterali */
            .side-image {
                width: 150px;
                height: auto;
                position: sticky;
                top: 50px; /* L'immagine resterà a 20px dal top quando si scorre */
            }

            /* Stile del contenitore del form */
            .form-container {
                background-color: #333;
                padding: 30px;
                border-radius: 5px;
                box-shadow: 0 4px 8px rgba(0.1, 0.1, 0.1, 0.2);
                width: 100%;
                max-width: 400px; /* Max width per il form */
                z-index: 1; /* Garantisce che il form sia sopra le immagini laterali */
            }

            .form-container h2 {
                text-align: center;
                margin-bottom: 30px;
            }

            /* Stile dei campi del form */
            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                display: block;
                margin-bottom: 15px;
            }

            .form-group input {
                width: 95%;
                padding: 8px;
                font-size: 14px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            /* Stile del pulsante di invio */
            .form-group input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                cursor: pointer;
            }

            .form-group input[type="submit"]:hover {
                background-color: #45a049;
            }

            /* Stile del footer */
            .footer-bar {
                width: 100%;
                background-color: #222; /* Colore di sfondo diverso per il footer */
                padding: 15px 0;
                text-align: center;
                position: fixed; /* Fissa il footer in basso */
                bottom: 0;
                left: 0;
                z-index: 1000; /* Mantiene il footer sopra il contenuto */
            }

            .footer-bar a {
                color: white;
                margin: 0 15px;
                text-decoration: none;
            }

            /* Media query per dispositivi mobili */
            @media (max-width: 768px) {
                /* Barra superiore: logo e barra di ricerca */
                .header-bar {
                    flex-direction: column;
                    align-items: flex-start;
                    padding: 15px;
                }

                .logo-container img {
                    width: 80px; /* Ridurre la dimensione del logo su dispositivi più piccoli
                }

                .search-bar input[type="text"] {
                    width: 100%; /* La barra di ricerca occuperà tutta la larghezza */
                    margin-top: 10px;
                }

                .search-bar input[type="submit"] {
                    width: 100%; /* Il pulsante di ricerca occupa tutta la larghezza */
                    margin-top: 5px;
                }

                /* Layout delle immagini laterali e form */
                .content-wrapper {
                    flex-direction: column;
                    gap: 20px;
                    margin-top: 20px;
                }

                .side-image {
                    width: 100px; /* Ridurre la dimensione delle immagini laterali su disposit
                    position: relative; /* Impedisce alle immagini di essere sticky sui dispos
                }

                .form-container {
                    width: 80%; /* Rendere il form più largo su schermi piccoli */
                    padding: 20px;
                }
            }
        </style>
</head>
    <body>

    <!-- Barra superiore con logo e barra di ricerca -->
        <div class="header-bar">
            <div class="logo-container">
                <a href="home.jsp">
                    <img src="logo.png" alt="Logo">
                </a>
            </div>
            <div class="search-bar">
                <form action="search.jsp" method="GET">
                    <input type="text" name="query" placeholder="Cerca...">
                    <input type="submit" value="Cerca">
                </form>
            </div>
        </div>

        <!-- Contenitore centrale con immagini laterali e form di inserimento -->
        <div class="content-wrapper">
            <img src="left-image.png" alt="Immagine Sinistra" class="side-image">

        <div class="form-container">
            <h2>Catalogo - Inserimento Utente</h2>
            <form action="Sign-up" method="POST">
                <input type="hidden" name="action" placeholder="insert">
                <div class="form-group">
                    <label for="CodiceFiscale">Codice Fiscale <span style="color:red;">*</span></label>
                    <input name="CodiceFiscale" id="CodiceFiscale" maxlength="50" required placeholder="Inserire il proprio codice fiscale">
                    <%
                        String Errore_CF = (String) request.getAttribute("Errore_CF");
                        if (Errore_CF != null) {
                    %>
                    <div class="field-error"><%= Errore_CF %></div>
                    <%
                        }
                    %>
                </div>
                <div class="form-group">
                    <label for="NomeUtente">Nome Utente <span style="color:red;">*</span></label>
                    <input name="NomeUtente" id="NomeUtente" maxlength="50" required placeholder="Inserire il proprio nome utente">
                </div>

                <%
                    String Errore_NU = (String) request.getAttribute("Errore_NU");
                    if (Errore_NU != null) {
                %>
                <div class="field-error"><%= Errore_NU %></div>
                <%
                    }
                %>

                <div class="form-group">
                    <label for="Password">Password <span style="color:red;">*</span></label>
                    <input name="Password" id="Password" maxlength="255" required placeholder="Inserire la password">
                </div>
                <div class="form-group">
                    <label for="Nome">Nome <span style="color:red;">*</span></label>
                    <input name="Nome" id="Nome" maxlength="50" required placeholder="Inserire il proprio nome">
                </div>
                <div class="form-group">
                    <label for="Cognome">Cognome <span style="color:red;">*</span></label>
                    <input name="Cognome" id="Cognome" maxlength="50" required placeholder="Inserire il proprio cognome">
                </div>
                <div class="form-group">
                    <label>Sesso: <span style="color:red;">*</span></label>
                    <label>M<input type="radio" name="sesso" value="M" required></label>
                    <label>F<input type="radio" name="sesso" value="F" required></label>
                </div>
                <div class="form-group">
                    <label for="DataNascita">Data di Nascita <span style="color:red;">*</span></label>
                    <input type="date" name="DataNascita" id="DataNascita" required placeholder="Inserire la data di nascita">
                </div>
                <div class="form-group">
                    <label for="E-mail">E-mail <span style="color:red;">*</span> </label>
                    <input name="E-mail" id="E-mail" maxlength="40" required placeholder="Inserire la propria e-mail">
                </div>
                <%
                    String Errore_DN = (String) request.getAttribute("Errore_DN");
                    if (Errore_DN != null) {
                %>
                <div class="field-error"><%= Errore_DN %></div>
                <%
                    }
                %>

                <div class="form-group">
                    <input type="submit" value="Aggiungi ">
                </div>
            </form>
        </div>

            <img src="right-image.png" alt="Immagine Destra" class="side-image">
        </div>

        <!-- Footer con stile di barra inferiore -->
        <div class="footer-bar">
            <a href="AboutUs.jsp">About Us</a>
            <a href="Contattaci.jsp">Contattaci</a>
            <a href="Termini_e_condizioni.jsp">Termini e condizioni</a>
            <a href="Assistenza.jsp">Assistenza</a>
        </div>

    </body>
</html>
