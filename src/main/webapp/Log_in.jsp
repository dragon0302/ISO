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
            body {
                font-family: Arial, sans-serif;
                background-color: #000000; /* Sfondo nero */
                color: white; /* Testo bianco per contrasto */
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 1px;
            }

            .login-container {
                background-color: #333; /* Colore di sfondo del form più scuro per contrastare il nero */
                padding: 30px;
                border-radius: 5px;
                box-shadow: 0 4px 8px rgba(0.1, 0.1, 0.1, 0.2);
                width: 300px;
            }

            .login-container h2 {
                text-align: center;
                margin-bottom: 30px;
            }

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

            .form-group input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                cursor: pointer;
            }

            .form-group input[type="submit"]:hover {
                background-color: #45a049;
            }

            .error-message {
                color: red;
                font-size: 14px;
                text-align: center;
            }
        </style>
    </head>
    <body>

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

        <footer>
            <a href="#">About Us</a>
            <a href="#">Contattaci</a>
            <a href="#">Termini e condizioni</a>
            <a href="#">Assistenza</a>
        </footer>

    </body>
</html>