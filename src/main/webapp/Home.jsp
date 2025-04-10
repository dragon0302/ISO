<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 02/04/2025
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
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
<%@ page import="com.mysql.cj.Session" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>ISO-16</title>

    <!-- STILI CSS PER LA STRUTTURA E IL DESIGN -->
    <head>
        <style>
            /* Stile generale della pagina */
            body {
                font-family: Arial, sans-serif;
                background-color: #000000;
                color: white;
                margin: 0;
                padding-top: 100px;  /* spazio per il header */
                padding-bottom: 80px; /* spazio per il footer */
            }


            /* Stile della barra superiore con logo e barra di ricerca */
            header {
                width: 100%;
                background-color: #8B2635;
                padding: 10px 20px;
                display: flex;
                align-items: center;
                justify-content: space-between;
                position: fixed;
                top: 0;
                left: 0;
                border-bottom: 1px solid #000000;
                z-index: 100;
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
                color: #f4f4f4;
                font-weight: bold;
            }

            /* Dropdown dei filtri */
            .dropdown {
                display: none;
                position: absolute;
                top: 25px;
                left: 0;
                background-color: white;
                border: 1px solid #000000;
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

            /* Stile del footer */
            .footer-bar {
                width: 100%;
                background-color: #222;
                padding: 15px 0;
                text-align: center;
                position: fixed;
                bottom: 0;
                left: 0;
                z-index: 99;
            }

            .footer-bar a {
                color: white;
                margin: 0 15px;
                text-decoration: none;
            }

            /* Contenitore dello slider */
            .product-slider {
                position: relative;
                overflow: hidden;
                width: 100%;
                max-width: 1200px;
                margin: 20px auto;
            }

            /* Container scrollabile con flex */
            .product-slider {
                display: flex;
                transition: transform 0.5s ease;
                gap: 40px;
                scroll-behavior: smooth;
            }
                        /* Card prodotto quadrata */
                    .product {
                        flex: 0 0 250px;
                        aspect-ratio: 1 / 1;
                        background-color: #1a1a1a;
                        border-radius: 12px;
                        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.7);
                        display: flex;
                        flex-direction: column;
                        overflow: hidden;
                        transition: transform 0.3s;
                    }

            .product:hover {
                transform: scale(1.03);
            }

            .box {
                display: flex;
                flex-direction: column;
                height: 100%;
                padding: 12px;
                box-sizing: border-box;
            }

            .product-img {
                width: 100%;
                height: 50%;
                object-fit: cover;
                border-radius: 8px;
                margin-bottom: 10px;
            }

            .box h3 {
                color: white;
                font-size: 16px;
                margin: 0 0 5px;
            }

            .box p {
                color: #ccc;
                font-size: 13px;
                margin: 3px 0;
                flex-grow: 1;
            }

            .box a {
                text-decoration: none;
                background-color: #8B2635;
                color: white;
                padding: 6px;
                text-align: center;
                border-radius: 6px;
                font-weight: bold;
                font-size: 13px;
                margin-top: 6px;
            }

            /* Flex container for product slider */
            .product-slider {
                display: flex;
                flex-wrap: nowrap;
                overflow-x: auto;
                gap: 10px;
                padding: 10px 0;
                scroll-snap-type: x mandatory;
            }

            .user-menu {
                display: none;
                position: absolute;
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 10px;
            }

            .user-menu.show {
                display: block;
            }

            /* Media query per dispositivi mobili */
            @media (max-width: 768px) {
                /* Barra superiore: logo e barra di ricerca */
                .header-bar {
                    flex-direction: column;
                    align-items: flex-start;
                    padding: 15px;
                }

                .product {
                    flex: 0 0 180px; /* Ridurre la larghezza dei prodotti sui dispositivi mobili */
                    margin: 5px;
                }

                .product-slider {
                    padding: 10px;
                    overflow-x: scroll;
                }


                .filter-bar {
                    overflow-x: auto;
                    white-space: nowrap;
                }

                .filter {
                    display: inline-block;
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
            <%
                String role = (String) session.getAttribute("role");
                String username = (String) session.getAttribute("username");

                if (role == null) { %>
            <!-- Se l'utente non è loggato, mostra i bottoni di login e signup -->
            <a class="btn login-btn" href="Log_in.jsp">Log In</a>
            <a class="btn signup-btn" href="Sign-up.jsp">Sign Up</a>
            <% } else { %>
            <!-- Se l'utente è loggato, mostra il nome utente o il menu amministratore -->
            <% if (role.equals("admin")) { %>
            <!-- Amministratore: aggiungi i bottoni di gestione e il menu a tendina -->
            <span class="username" onclick="toggleUserMenu()"><%= username != null ? username.toUpperCase() : "" %></span>
            <div id="userMenu" class="user-menu">
                <ul>
                    <li><a href="profile.jsp">Profilo</a></li>
                    <li><a href="settings.jsp">Impostazioni</a></li>
                    <li><a href="cart.jsp">Carrello</a></li>
                    <li><a href="logout.jsp">Log-out</a></li>
                </ul>
            </div>
            <!-- Bottoni amministratore -->
            <a class="btn admin-btn" href="admin_dashboard.jsp">Dashboard</a>
            <a class="btn admin-btn" href="manage_products.jsp">Gestisci Prodotti</a>
            <a class="btn admin-btn" href="view_orders.jsp">Ordini</a>
            <button class="btn" onclick="openAddProductModal()">Aggiungi Prodotto</button>
            <button class="btn" onclick="openAddFilterModal()">Aggiungi Filtro</button>
            <button class="btn" onclick="openDateSurveyModal()">Indagine per Data</button>
            <button class="btn" onclick="openDeleteModal()">Elimina</button>
            <% } else { %>
            <!-- Utente non amministratore: solo il nome utente e il menu a tendina -->
            <span class="username" onclick="toggleUserMenu()"><%= username != null ? username.toUpperCase() : "" %></span>
            <div id="userMenu" class="user-menu">
                <ul>
                    <li><a href="profile.jsp">Profilo</a></li>
                    <li><a href="settings.jsp">Impostazioni</a></li>
                    <li><a href="cart.jsp">Carrello</a></li>
                    <li><a href="logout.jsp">Log-out</a></li>
                </ul>
            </div>
            <% } %>
            <% } %>
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
<script>
    function toggleUserMenu() {
        document.getElementById("userMenu").classList.toggle("show");
    }

    function closeModal(modalId) {
        document.getElementById(modalId).style.display = "none";
    }

    function openAddProductModal() {
        document.getElementById("addProductModal").style.display = "block";
    }

    function openAddFilterModal() {
        document.getElementById("addFilterModal").style.display = "block";
    }

    function openDateSurveyModal() {
        document.getElementById("dateSurveyModal").style.display = "block";
    }

    function openDeleteModal() {
        document.getElementById("deleteModal").style.display = "block";
    }
</script>
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
                <div class="box">
                    <h3><%= p.getNome() %></h3>
                    <p>Prezzo: €<%= p.getPrezzo() %></p>
                    <p><%= p.getDescrizione() %></p>
                    <a href="prodotto.jsp?id=<%= p.getId_prodotto() %>">Dettagli</a>
                    <div class="right-section">
                        <a class="btn Aggiungi">Aggiungi al carrello</a>
                    </div>
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
                <div class="box"><h3 ><%= p.getNome() %></h3>
                    <p >Prezzo: €<%= p.getPrezzo() %></p>
                    <p ><%= p.getDescrizione() %></p>
                    <a href="prodotto.jsp?id=<%= p.getId_prodotto() %>">Dettagli</a>
                    <div class="right-section">
                        <a class="btn Aggiungi">Aggiungi al carrello</a>
                        <div class="product-slider">
                            <%
                                List<Prodotto> prodottiNovita = (List<Prodotto>) request.getAttribute("prodottiNovita");
                                if (prodottiNovita != null) {
                                    for (Prodotto p : prodottiNovita) {
                            %>
                            <div class="product">
                                <div class="box">
                                    <h3><%= p.getNome() %></h3>
                                    <p>Prezzo: €<%= p.getPrezzo() %></p>
                                    <p><%= p.getDescrizione() %></p>
                                    <a href="ProdottoS?id=<%= p.getId_prodotto() %>">Dettagli</a>
                                    <form action="Carrello" method="post">
                                        <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">
                                        <input type="hidden" name="SourcePage" value="Home">
                                        <button type="submit" class="btn-aggiungi">
                                            Aggiungi al carrello
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>


                    </div>
                    <h2>Prodotti più acquistati</h2>
                    <div class="product-slider">
                            <%
                        List<Prodotto> prodottiPopolari = (List<Prodotto>) request.getAttribute("prodottiPiuAqqistati");
                        if (prodottiPopolari != null) {
                            for (Prodotto p : prodottiPopolari) {
                    %>
                        <div class="product">
                            <div class="box"><h3 ><%= p.getNome() %></h3>
                                <p >Prezzo: €<%= p.getPrezzo() %></p>
                                <p ><%= p.getDescrizione() %></p>
                                <a href="prodotto.jsp?id=<%= p.getId_prodotto() %>">Dettagli</a>
                                <form action="Carrello" method="post">
                                    <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">
                                    <input type="hidden" name="SourcePage" value="Home">
                                    <button type="submit" class="btn-aggiungi">
                                        Aggiungi al carrello
                                    </button>
                                </form>
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

<div class="footer-bar">
    <a href="About_Us.jsp">About Us</a>
    <a href="contattaci.jsp">Contattaci</a>
    <a href="Termini_e_condizioni.jsp">Termini e condizioni</a>
    <a href="Assistenza.jsp">Assistenza</a>
</div>

</body>
</html>
