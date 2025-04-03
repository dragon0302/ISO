<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 03/04/2025
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        /* Stile generale della pagina */
        body {
            font-family: Arial, sans-serif;
            background-color: #000000; /* Sfondo nero */
            color: white; /* Testo bianco per contrasto */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 1px;
            flex-direction: column;
        }

        /* Stile della barra superiore con logo e barra di ricerca */
        .header-bar {
            width: 100%;
            background-color: #444; /* Colore di sfondo per il header */
            padding: 15px 0;
            display: flex;
            align-items: center;
            justify-content: space-between;
            position: absolute;
            top: 0;
            left: 0;
            padding: 10px 20px;
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

        /* Contenitore centrale che include immagini laterali e il login */
        .content-wrapper {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 100px; /* Distanza tra le immagini laterali e il form di login */
            margin-top: 100px;
        }

        /* Stile delle immagini laterali */
        .side-image {
            width: 150px;
            height: auto;
        }

        /* Stile del contenitore del login */
        .login-container {
            background-color: #333;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0.1, 0.1, 0.1, 0.2);
            width: 300px;
        }

        .login-container h2 {
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
            width: 100%;
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

        /* Stile del messaggio di errore */
        .error-message {
            color: red;
            font-size: 14px;
            text-align: center;
        }

        /* Stile del footer */
        .footer-bar {
            width: 100%;
            background-color: #222; /* Colore di sfondo diverso per il footer */
            padding: 15px 0;
            text-align: center;
            position: absolute;
            bottom: 0;
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
                width: 80px; /* Ridurre la dimensione del logo su dispositivi più piccoli */
            }

            .search-bar input[type="text"] {
                width: 100%; /* La barra di ricerca occuperà tutta la larghezza */
                margin-top: 10px;
            }

            .search-bar input[type="submit"] {
                width: 100%; /* Il pulsante di ricerca occupa tutta la larghezza */
                margin-top: 5px;
            }

            /* Layout delle immagini laterali e login */
            .content-wrapper {
                flex-direction: column;
                gap: 20px;
                margin-top: 20px;
            }

            .side-image {
                width: 100px; /* Ridurre la dimensione delle immagini laterali su dispositivi mobili */
            }

            .login-container {
                width: 80%; /* Rendere il login più largo su schermi piccoli */
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

<!-- Contenitore centrale con immagini laterali e login -->
<div class="content-wrapper">
    <img src="left-image.png" alt="Immagine Sinistra" class="side-image">

    <div class="login-container">
        <h2>Accedi</h2>
        <form action="login.php" method="POST">
            <div class="form-group">
                <label for="username">Nome Utente</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <input type="submit" value="Accedi">
            </div>
        </form>
        <div class="error-message">
            <!-- Qui verranno visualizzati eventuali messaggi di errore -->
        </div>
    </div>

    <img src="right-image.png" alt="Immagine Destra" class="side-image">
</div>

<!-- Footer con stile di barra inferiore -->
<div class="footer-bar">
    <a href="About_Us.jsp">About Us</a>
    <a href="contattaci.jsp">Contattaci</a>
    <a href="Termini_e_condizioni.jsp">Termini e condizioni</a>
    <a href="Assistenza.jsp">Assistenza</a>
</div>

</body>
</html>




