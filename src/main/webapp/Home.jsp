<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 02/04/2025
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DataManagement.Prodotto" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Ricerca & Filtri</title>

    <!-- STILI CSS PER LA STRUTTURA E IL DESIGN -->
    <style>
        /* Rimuove margini di default e imposta font */
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

        /* Contenitore principale dell'header */
        header {
            background-color: #f4f4f4;
            padding: 10px 20px;
            border-bottom: 1px solid #ccc;
        }

        /* Riga superiore con logo + barra di ricerca + bottoni */
        .top-header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            flex-wrap: wrap;
        }

        /* Contenitore del logo (parte sinistra) */
        .logo-container {
            flex: 1;
        }

        /* Stile del logo */
        .logo-container img {
            height: 60px;
        }

        /* Contenitore barra di ricerca + login + signup (parte destra) */
        .search-actions {
            flex: 3;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            gap: 10px;
        }

        /* Posizionamento barra di ricerca */
        .search-container {
            position: relative;
        }

        /* Input della barra di ricerca */
        .search-container input {
            padding: 8px;
            width: 250px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        /* Suggerimenti dinamici (dropdown) */
        .suggestions {
            position: absolute;
            top: 36px;
            left: 0;
            right: 0;
            background-color: white;
            border: 1px solid #ccc;
            z-index: 10;
        }

        /* Singolo suggerimento */
        .suggestions div {
            padding: 5px;
            cursor: pointer;
        }

        /* Hover su suggerimento */
        .suggestions div:hover {
            background-color: #eee;
        }

        /* Stili base dei bottoni */
        .btn {
            padding: 8px 12px;
            border-radius: 4px;
            font-size: 14px;
            text-decoration: none;
            cursor: pointer;
        }

        /* Bottone Log-in: bordo nero, trasparente */
        .login-btn {
            border: 1px solid #000;
            background-color: transparent;
            color: #000;
        }

        /* Bottone Sign-up: sfondo nero, testo bianco */
        .singup-btn {
            background-color: #000;
            color: #fff;
            border: none;
        }

        /* Barra filtri sotto la riga principale */
        .filter-bar {
            display: flex;
            gap: 20px;
            margin-top: 10px;
            flex-wrap: wrap;
        }

        /* Contenitore di ogni filtro */
        .filter {
            position: relative;
        }

        /* Link filtro principale */
        .filter > a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }

        /* Dropdown che appare al passaggio sopra */
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

        /* Voci nel menu a tendina */
        .dropdown a {
            display: block;
            padding: 5px 10px;
            text-decoration: none;
            color: #333;
        }

        /* Hover sulle voci del dropdown */
        .dropdown a:hover {
            background-color: #eee;
        }

        /* Visualizza il dropdown quando si passa sopra il filtro */
        .filter:hover .dropdown {
            display: block;
        }
    </style>
</head>
    <body>
        <header>
            <div class="logo">Logo</div>
            <nav>
                <input type="text" placeholder="Cerca..." class="search-bar">
                <a href="#">Log-in</a>
                <a href="#">Carrello</a>
            </nav>
        </header>

        <main>
                <aside class="hide-sidebar">
                    <h3>Categorie dei prodotti</h3>
                    <ul>
                        <li> Categoria 1 </li>
                        <li> Categoria 2 </li>
                        <li> Categoria 3 </li>
                    </ul>
                </aside>

            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Prezzo (€)</th>
                <th>Descrizione</th>
            </tr>

            <section class="content">
                <div class="banner">Banner</div>

                <h2>Novità</h2>

                <div class="product-slider">
                    <%

                        List<Prodotto> prodottiNovita = (List<Prodotto>) request.getAttribute("prodottiNovita");
                        if (prodottiNovita != null) {
                            for (Prodotto p : prodottiNovita) {
                    %>
                    <div class="product">
                        <h3><%= p.getNome() %></h3>
                        <p>Prezzo: €<%= p.getPrezzo() %></p>
                        <p><%= p.getDescrizione() %></p>
                        <a href="prodotto.jsp?id=<%= p.getId_prodotto() %>">Dettagli</a>

                        <div class="right-section">
                            <a class="btn Aggiungi">Aggiungi al carrello</a>
                        </div>

                    </div>
                    <%
                            }
                        } else {
                    %>
                    <p>Nessun prodotto trovato.</p>
                    <%
                        }
                    %>
                </div>

                <h2>Prodotti più acquistati</h2>
                <div class="product-slider">
                    <%
                        List<Prodotto> prodottiPopolari = (List<Prodotto>) request.getAttribute("prodottiPiuAqqistati");
                        if (prodottiPopolari != null) {
                            for (Prodotto p : prodottiPopolari) {
                    %>
                    <div class="product">
                        <h3><%= p.getNome() %></h3>
                        <p>Prezzo: €<%= p.getPrezzo() %></p>
                        <p><%= p.getDescrizione() %></p>
                        <a href="prodotto.jsp?id=<%= p.getId_prodotto() %>">Dettagli</a>

                        <div class="right-section">
                            <a class="btn Aggiungi">Aggiungi al carrello</a>
                        </div>

                    </div>

                    <%
                        }
                    } else {
                    %>
                    <p>Nessun prodotto trovato.</p>
                    <%
                        }
                    %>
                </div>


            </section>
        </main>

        <footer>
            <a href="#">About Us</a>
            <a href="#">Contattaci</a>
            <a href="#">Termini e condizioni</a>
            <a href="#">Assistenza</a>
        </footer>

    </body>
</html>
