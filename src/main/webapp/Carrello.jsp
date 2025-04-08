<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Ricerca & Filtri</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

        header {
            background-color: #f4f4f4;
            padding: 10px 20px;
            border-bottom: 1px solid #ccc;
        }

        /* Container principale per la barra superiore */
        .top-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 20px;
            flex-wrap: wrap;
        }

        /* Parte 1 - Logo a sinistra */
        .logo-container {
            flex: 1;
        }

        .logo-container img {
            height: 60px;
        }

        /* Parte 2 - Barra di ricerca centrata e filtri sotto */
        .center-section {
            flex: 2;
            text-align: center;
            width: 100%;
        }

        .search-container {
            position: relative;
            width: 60%;
            margin: 0 auto;
        }

        .search-container input {
            padding: 8px;
            width: 100%;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .suggestions {
            position: absolute;
            top: 36px;
            left: 0;
            right: 0;
            background-color: white;
            border: 1px solid #ccc;
            z-index: 10;
        }

        .suggestions div {
            padding: 5px;
            cursor: pointer;
        }

        .suggestions div:hover {
            background-color: #eee;
        }

        /* Filtri sotto la barra di ricerca */
        .filter-bar {
            display: flex;
            gap: 20px;
            justify-content: center;
            margin-top: 10px;
            border-top: 1px solid #ccc; /* Linea separatrice */
            padding-top: 10px;
        }

        .filter {
            position: relative;
            padding: 0 10px;
            border-right: 1px solid #ccc;
        }

        .filter:last-child {
            border-right: none;
        }

        .filter > a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }

        /* Dropdown dei filtri */
        .dropdown {
            display: none;
            position: absolute;
            top: 25px;
            left: 0;
            background-color: white;
            border: 1px solid #ccc;
            min-width: 120px;
            z-index: 100;
        }

        .dropdown a {
            display: block;
            padding: 5px 10px;
            text-decoration: none;
            color: #333;
        }

        .dropdown a:hover {
            background-color: #eee;
        }

        .filter:hover .dropdown {
            display: block;
        }

        /* Parte 3 - Bottoni a destra */
        .right-section {
            display: flex;
            gap: 10px;
            align-items: center;
        }

        .btn {
            padding: 8px 12px;
            border-radius: 4px;
            font-size: 14px;
            text-decoration: none;
            cursor: pointer;
        }

        .login-btn {
            border: 1px solid #000;
            background-color: transparent;
            color: #000;
        }

        .signup-btn {
            background-color: #000;
            color: #fff;
            border: none;
        }

    </style>
</head>
<body>

<header>
    <div class="top-header">
        <!-- Parte 1 - Logo a sinistra -->
        <div class="logo-container">
            <a href="Home.jsp">
                <img src="logo.png" alt="Logo">
            </a>
        </div>

        <!-- Parte 2 - Barra di ricerca centrata e filtri sotto -->
        <div class="center-section">
            <!-- Barra di ricerca -->
            <div class="search-container">
                <input type="text" id="searchInput" placeholder="Cerca prodotti..." onkeyup="showSuggestions(this.value)">
                <div class="suggestions" id="suggestionBox"></div>
            </div>

            <!-- Filtri sotto la barra di ricerca -->
            <div class="filter-bar">
                <div class="filter">
                    <a href="#">ROCK</a>
                    <div class="dropdown">
                        <a href="#">AC/DC</a>
                        <a href="#">Aerosmith</a>
                        <a href="#">Led Zeppelin</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#">POP</a>
                    <div class="dropdown">
                        <a href="#">Adele</a>
                        <a href="#">Beyoncé</a>
                        <a href="#">Madonna</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#">JAZZ</a>
                    <div class="dropdown">
                        <a href="#">Coltrane</a>
                        <a href="#">Davis</a>
                        <a href="#">Fitzgerald</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#">METAL</a>
                    <div class="dropdown">
                        <a href="#">Iron Maiden</a>
                        <a href="#">Metallica</a>
                        <a href="#">Slayer</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#">RAP</a>
                    <div class="dropdown">
                        <a href="#">Drake</a>
                        <a href="#">Eminem</a>
                        <a href="#">Kanye</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#">Abbigliamento</a>
                    <div class="dropdown">
                        <a href="#">Cappelli</a>
                        <a href="#">Felpe</a>
                        <a href="#">T-shirt</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Parte 3 - Bottoni di Sign-up e Log-in a destra -->
        <div class="right-section">
            <a class="btn login-btn" href="Log_in.jsp">Log In</a>
            <a class="btn signup-btn" href="Sing-up.jsp">Sign Up</a>
        </div>
    </div>
</header>

<!-- JavaScript per suggerimenti live -->
<script>
    const products = [
        "AC/DC T-Shirt", "Adele CD", "Metallica Hoodie",
        "Queen Vinyl", "Eminem Cap", "Jazz Mug",
        "Taylor Swift Poster", "Iron Maiden Patch", "Led Zeppelin T-Shirt"
    ];

    function showSuggestions(value) {
        const suggestionBox = document.getElementById('suggestionBox');
        suggestionBox.innerHTML = '';
        if (value.length === 0) return;

        const filtered = products.filter(product =>
            product.toLowerCase().includes(value.toLowerCase())
        );

        filtered.forEach(product => {
            const div = document.createElement('div');
            div.textContent = product;
            div.onclick = () => {
                document.getElementById('searchInput').value = product;
                suggestionBox.innerHTML = '';
            };
            suggestionBox.appendChild(div);
        });
    }
</script>

</body>
</html>





